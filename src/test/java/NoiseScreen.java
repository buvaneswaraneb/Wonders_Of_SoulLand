import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class NoiseScreen extends JPanel implements Runnable {

    public int screen_width = 200;
    public int screen_height = 100;

    public  int scale = 6;

    public int display_height = screen_height * scale;
    public int display_width = screen_width * scale;

    private boolean test = true;

    public int[][] noiseArr;
    public int[][] noiseArrWithFBM;
    public Color[][] colorArr  =  new Color[screen_width][screen_height];
    public Color[][] colorArWithFBM  =  new Color[screen_width][screen_height];
    public boolean useColor;
    public boolean voiceNoise = false;

    private int[][] useArr;
    private Color[][] useColorArr;
    public boolean calculateFBM = false;
    public float noise_scale = 0.1f;

    String text = "Value Noise Without fBm – Single-Octave Noise";


    public KeyHandler_test keyHandler = new KeyHandler_test();
    public Noise noise = new Noise(screen_width,screen_height,7856463);
    Thread thread;

    // test
    int[] freq = new int[11];

    // settings
    int octaves = 2;
    float persistence = 0.5f;
    float lacunarity = 2.0f;

    public NoiseScreen(){
        this.setPreferredSize(new Dimension(display_width,display_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        while (thread != null){
            update();
            repaint();
            _clock_thread(10);
        }
    }

    public void startNoiseThread(){
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        if (noiseArr == null){
            useColor = false;
            calculateNoise();
            useArr = noiseArr;
            useColorArr = colorArr;
            return;
        }

        if(keyHandler.rightPressed){
            ChangeScreen();
        }

        if (keyHandler.Cpressed){
            keyHandler.Cpressed = false;

            if (useColor) useColor = false;
            else useColor = true;
        }

        if(keyHandler.enterPressed){
            keyHandler.enterPressed = false;
            if (!calculateFBM)  calculateFBMNoise();

            if(voiceNoise){
                text = "Value Noise Without fBm – Single-Octave Noise";
                useArr = noiseArr;
                useColorArr = colorArr;
                voiceNoise = false;
            }else{
                text = "Value Noise With fBm – Fractal Noise";
                useArr = noiseArrWithFBM;
                useColorArr = colorArWithFBM;
                voiceNoise = true;
            }
        }

        Graphics2D g2d = (Graphics2D) g;

        for(int x = 0 ; x < screen_width ; x++){
            for(int y = 0 ; y < screen_height; y++){

                int gray = useArr[y][x];
                if (!useColor){

                g2d.setColor(
                        new Color(gray, gray, gray)
                );}
                else {
                    g2d.setColor(useColorArr[y][x]);
                }
                g2d.fillRect(x*scale,y*scale, scale, scale);
            }
        }

        drawText(g2d);

        g2d.dispose();
    }

    public void update(){

    }


    public void drawText(Graphics2D g2d){
        g2d.setFont(g2d.getFont().deriveFont(40f));
        int x = getXforCenteredText(text,g2d);
        int y = screen_height / 2;

        g2d.setColor(new Color(0,0,0,200));
        g2d.fillRect(x-10, 10, display_width - x*2 + 20  ,50);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text,x,y);
    }


    public int getXforCenteredText(String txt,Graphics2D g2d){
        int length = (int)g2d.getFontMetrics().getStringBounds(txt,g2d).getWidth();
        int x  = (display_width/2) - length/2;
        return x;
    }

    public void test(){
        int sum = 0;
        freq = new int[11];
        System.out.printf("[");
        for(int i = 0; i <= 10 ; i++){
            System.out.printf(" %d,",freq[i]);
            sum += freq[i];
        }
        System.out.println("]");
        System.out.println("Sum : " + sum);
    }


    private void calculateNoise(){
        noiseArr = new int[screen_height][screen_width];
        colorArr = new Color[screen_height][screen_width];

        for(int x = 0 ; x < screen_width ; x++){
            for(int y = 0 ; y < screen_height; y++){

                float val = noise.getNoise(x * noise_scale,y * noise_scale);
                float round = Math.round(val * 10.0f) / 10.0f;
                Color color = getColor(round);


                if (val == 1.0f){
                    System.out.println("1 found");
                }

                int gray = (int) (val * 255);
                // ttes
                int whole = (int) (val * 10);
                freq[whole]++;
                noiseArr[y][x] = gray;
                colorArr[y][x] = color;
            }
        }
    }

    private void calculateFBMNoise() {
        calculateFBM = true;
        noiseArrWithFBM = new int[screen_height][screen_width];
        colorArWithFBM = new Color[screen_height][screen_width];

        for (int x = 0; x < screen_width; x++) {
            for (int y = 0; y < screen_height; y++) {

                float val = noise.getFBMNoise(x * noise_scale, y * noise_scale,octaves, persistence, lacunarity);
                float round = Math.round(val * 10.0f) / 10.0f;
                Color color = getColor(round);

                int gray = (int) (val * 255);
                int whole = (int) (val * 10);
                freq[whole]++;

                noiseArrWithFBM[y][x] = gray;
                colorArWithFBM[y][x] = color;

            }
        }
    }


        public void _clock_thread(int FPS) {
        final double BILLION = 1_000_000_000;
        double drawInterval = BILLION / FPS;
        double nextInterval = System.nanoTime() + drawInterval;

        try {
            double remainingTime = nextInterval - System.nanoTime();
            remainingTime = remainingTime / 1_000_000; // Converts the Nano Seconds to Milliseconds

            if (remainingTime < 0) remainingTime = 0;

            Thread.sleep((long) remainingTime);
            
            nextInterval += drawInterval;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Color getColor(float val){

        Color base = new Color(3, 78, 6); // plain
        Color flower1 = new Color(236, 115, 115);
        Color flower2 = new Color(225, 96, 225);
        Color flower3 = new Color(109, 236, 236);
        Color grass1 = new Color(0, 227, 114);
        Color grass2 = new Color(132, 255, 103);

        if (val >= 0.0 && val <= 0.3) return flower2;
        else if (val < 0.5) return grass1;
        else if(val < 0.6) return grass2;
        else if(val <= 0.7) return flower3;
        else if(val <= 1.0) return flower1;
        return base;
    }

    public void ChangeScreen(){
        thread = null;
        // Create new panel
        TestPannel testPannel = new TestPannel();

        JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);

        window.getContentPane().removeAll();

        window.add(testPannel);

        window.pack();
        window.revalidate();
        window.repaint();
        window.setTitle("Test window");

        testPannel.requestFocusInWindow();

        testPannel.setup();
        testPannel.startGameThread();

    }


}
