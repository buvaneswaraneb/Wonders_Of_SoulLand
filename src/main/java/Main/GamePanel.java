package Main;

import Objects.SuperObject;
import Tile.TileManager;
import Entity.Player;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    final int FPS = 60;

    final int pixel = 16; // pixel size 16 x 16
    final int scale = 3; // scale
    public final int tile_size = pixel*scale; // 48 x 48

    public final int max_pixel_row = 12;   // height
    public final int max_pixel_col = 16;   // width

    public final int screen_width = max_pixel_col * tile_size; // 768
    public final int screen_height = max_pixel_row * tile_size; // 576

    public final int max_world_row = 50; // World height by tiles
    public final int max_world_col = 50; // World width by tiles

    public final int world_height = max_world_col * tile_size;
    public final int world_width = max_world_row * tile_size;

    Thread gameThread;
    KeyHandler key_handler = new KeyHandler();

    //Entities
    public Player player = new Player(this,key_handler);
    TileManager tileManager = new TileManager(this);

    //collision
    public CollisonEngine collisonEngine = new CollisonEngine(this);

    //object
    public SuperObject[] Obj = new SuperObject[10]; // debug @ trail

    //AssestsSetter
    public AssestsSetter aSetter = new AssestsSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_handler);
        this.setFocusable(true);

    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        final double BILLION = 1_000_000_000;
        double drawInterval = BILLION / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        double timer = 0;
        double drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();  // updates the information
                repaint();  //redraws the pixel
                delta--;
                drawCount++;
            }

            if (timer >= BILLION){
                System.out.println("FPS : "+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void setUpGame(){
        aSetter.setObjects();
    }

    private void update(){
       // update player
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        // display Objects
        for(int i = 0; i < 10; i++){
            if(Obj[i] == null) continue;
            Obj[i].draw(this,g2d);
        }
        player.draw(g2d);
        g2d.dispose();
    }
}
