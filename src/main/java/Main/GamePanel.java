package Main;

import Entity.Entity;
import Objects.SuperObject;
import Biome.Biome;
import Biome.BiomeManager;
import Tile.TileManager;
import Entity.Player;
import jdk.jshell.execution.Util;

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
    // game state
    public int gameState;
    public final int pauseState = 1;
    public final int playState = 2;
    public final int dialogueState = 3;
    public final int titleState = 4;

    //System
    Clock clock = new Clock();
    Thread gameThread;
    public KeyHandler key_handler = new KeyHandler(this);
    public Utility util = new Utility();
    // tiles
    TileManager tileManager = new TileManager(this);
    public BiomeManager biomeManager = new BiomeManager(this);
    public Biome currentBiome;

    public CollisonEngine collisonEngine = new CollisonEngine(this);
    public AssestsSetter aSetter = new AssestsSetter(this);
    public Sound sound = new Sound();
    public Sound music = new Sound();
    public UI ui = new UI(this);

    public SuperObject[] Obj = new SuperObject[10]; // debug @ trail @limit

    //Entities
    public Player player = new Player(this,key_handler);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_handler);
        this.setFocusable(true);

        //debug
        //debug();
    }


    public void setUpGame(){
        biomeManager.BiomeSetter();
        currentBiome = biomeManager.getBiome("B001");
        aSetter.setObjects();
        aSetter.setNpcs();
        playMusic(1);
        gameState = titleState;
    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null){
            update();
            repaint();
            clock._clock_thread(FPS);
        }
    }

    private void update(){

        if(gameState == titleState){
            if(key_handler.upPressed || key_handler.downPressed){
                playSpecialEffects(4);
            }
            return;
        }

        if(gameState == pauseState) {
            stopMusic();
            if(sound.audioPlaying) {
                music.audioPlaying = false;
            }
            return;
        }

        // player and npc updates
        player.update();
        for(Entity npc : currentBiome.npc){
            if(npc == null) continue;
            npc.update();
        }

        if (!music.audioPlaying){
            music.audioPlaying = true;
            music.play();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameState == 0 || gameState == titleState) {
            //current state title state
        }

        else {
            tileManager.draw(g2d);

            // display Objects
            for(int i = 0; i < currentBiome.object.length; i++){
                if(currentBiome.object[i] == null) continue;
                currentBiome.object[i].draw(this,g2d);
            }

            // draw NPC
            for(int i = 0; i < currentBiome.npc.length; i++){
                if(currentBiome.npc[i] == null) continue;
                currentBiome.npc[i].draw(g2d);
            }

            player.draw(g2d);
        }
        ui.draw(g2d);
        g2d.dispose();
    }



    //music
    public void playMusic(int i){
       music.setFile(i);
       music.play();
       music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSpecialEffects(int i){
        sound.setFile(i);
        sound.play();
    }

    private void debug(){
        player.debug = true;
        for(int i = 0; i < currentBiome.object.length; i++){
            if(currentBiome.object[i] == null) continue;
            currentBiome.object[i].debug = true;
        }
    }


}
