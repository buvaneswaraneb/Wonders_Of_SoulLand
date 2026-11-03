import Main.Clock;
import Main.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class NoiseScreen extends JPanel implements Runnable {

    public int screen_width = 100;
    public int screen_height = 100;

    public  int scale = 8;

    public int display_height = screen_height * scale;
    public int display_width = screen_height * scale;

    public float noise_scale = 0.123f;
    private boolean test = true;

    public int[][] noiseArr;
    public KeyHandler_test keyHandler = new KeyHandler_test();
    public Noise noise = new Noise(screen_width,screen_height,78648753);
    Thread thread;

    // test
    int[] freq = new int[11];


    // settings
    int octaves = 4;
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
            _clock_thread(30);
        }
    }

    public void startNoiseThread(){
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        if (noiseArr == null){
            calculateNoise();
            test();
            return;
        }

        if(keyHandler.enterPressed){
            keyHandler.enterPressed = false;
            calculateFBMNoise();
        }

        Graphics2D g2d = (Graphics2D) g;

        for(int x = 0 ; x < screen_width ; x++){
            for(int y = 0 ; y < screen_height; y++){

                int gray = noiseArr[y][x];

                g2d.setColor(
                        new Color(gray, gray, gray)
                );

                g2d.fillRect(x*scale,y*scale, scale, scale);

            }
        }

        g2d.dispose();
    }

    public void update(){

    }

    public void test(){
        int sum = 0;
        System.out.printf("[");
        for(int i = 0; i < 11 ; i++){
            System.out.printf(" %d,",freq[i]);
            sum += freq[i];
        }
        System.out.println("]");
        System.out.println("Sum : " + sum);
    }


    private void calculateNoise(){
        noiseArr = new int[screen_height][screen_width];

        for(int x = 0 ; x < screen_width ; x++){
            for(int y = 0 ; y < screen_height; y++){

                float val = noise.getNoise(x * noise_scale,y * noise_scale);

                int gray = (int) (val * 255);

                // ttest

                int whole = (int) (val * 10);

                freq[whole]++;
                noiseArr[y][x] = gray;
            }
        }
    }

    private void calculateFBMNoise() {
        noiseArr = new int[screen_height][screen_width];

        for (int x = 0; x < screen_width; x++) {
            for (int y = 0; y < screen_height; y++) {

                float val = noise.getFBMNoise(x * noise_scale, y * noise_scale,octaves, persistence, lacunarity);

                int gray = (int) (val * 255);

                noiseArr[y][x] = gray;
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


}
