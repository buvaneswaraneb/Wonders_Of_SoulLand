package Main;

import Biome.Biome;
import Entity.Traveller;
import Objects.GreenStick_1;
import Objects.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font pixel_font;
    BufferedImage wood_image;
    SuperObject wood;
    Graphics2D g2d;
    private String message = "";
    private boolean messageOn = false;

    public int currentMenu = 0;
    public final int menuTotal = 3;
    public String currentDialogue = " ";

    int x;
    int y;

    public UI(GamePanel gp){
        this.gp = gp;
        this.wood = new GreenStick_1();
        this.wood_image = wood.Image;
        try {
            InputStream is = getClass().getResourceAsStream("/Font/pixel_font.ttf");
            pixel_font = Font.createFont(Font.TRUETYPE_FONT,is);
            pixel_font = pixel_font.deriveFont(Font.PLAIN,20);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showMessage(String message){
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){
        this.g2d = g2d;

        g2d.setFont(pixel_font);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.white);


        // play state
        if(gp.gameState == gp.playState){
            drawCoordinates(g2d);
        }
        //pause state
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen(g2d);
        }
        //dialogue state
        else if (gp.gameState == gp.dialogueState){
            drawDialogueBox(g2d);
        }
        else if(gp.gameState == gp.titleState){
            drawTitleScreen(g2d);
        }
    }


    public int getXforCenteredText(String txt){
        int length = (int)g2d.getFontMetrics().getStringBounds(txt,g2d).getWidth();
        int x  = (gp.screen_width/2) - length/2;
        return x;
    }


    public void drawPauseScreen(Graphics2D g2d){
        String text = "Paused";
        System.out.println("paused");
        g2d.setFont(g2d.getFont().deriveFont(80f));
        int x = getXforCenteredText(text);
        int y = gp.screen_height/2;
        g2d.drawString(text,x,y);
    }


    public void drawDialogueBox(Graphics2D g2d){
        int x = gp.tile_size /2;
        int y = gp.screen_height - gp.tile_size * 3;
        int height = gp.tile_size*3;
        int width = gp.screen_width - gp.tile_size;

        drawPanel(g2d,x,y,width,height);

        g2d.setColor(Color.WHITE);
        g2d.drawString(currentDialogue,x+(int)(gp.tile_size/2),y+(gp.tile_size/2));
    }



    public void drawTitleScreen(Graphics2D g2d){
        String gameTitle = "Wonders of SoulLand";


        // drawing background color for the screen
        Color b1 = new Color(96, 108, 56);
        Color b2 = new Color(40, 54, 24);
        Color b3 = new Color(219, 179, 177);

        // to divide these chunks into three chunks and display on the screen
        int bg_chunk = gp.screen_width/3;

        // display first chunk
        g2d.setColor(b1);
        g2d.fillRect(0,0,bg_chunk,gp.screen_height);

        g2d.setColor(b2);
        g2d.fillRect(bg_chunk,0,bg_chunk,gp.screen_height);

        g2d.setColor(b3);
        g2d.fillRect((bg_chunk * 2),0,bg_chunk,gp.screen_height);

        g2d.setFont(g2d.getFont().deriveFont(60f));

        // displays the letter background
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(gameTitle);
        y = gp.tile_size*2;
        g2d.drawString(gameTitle,x+3,y+3);

        g2d.setColor(Color.WHITE);
        x = getXforCenteredText(gameTitle);
        y = gp.tile_size*2;
        g2d.drawString(gameTitle,x,y);

        // display menu
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(30f));

        // new game
        String newGame = "NEW GAME";

        if(currentMenu == 0) newGame = "[ NEW GAME ]";
        x = getXforCenteredText(newGame);
        y = y + (gp.tile_size * 4);
        g2d.drawString(newGame,x,y);


        // continue
        String loadGame = "LOAD GAME";
        if(currentMenu == 1) loadGame = "[ LOAD GAME ]";
        x = getXforCenteredText(loadGame);
        y = y + 60 ; // here 40 is the Font Size + 40 is space Buffer
        g2d.drawString(loadGame,x,y);

        // quit game
        String quit = "QUIT";
        if(currentMenu == 2) quit = "[ QUIT ]";
        x = getXforCenteredText(quit);
        y = y + 60 ; // here 40 is the Font Size + 40 is space Buffer
        g2d.drawString(quit,x,y);


        // draw players
        var image = gp.player.right_walking.get(1);
        g2d.drawImage(image,gp.screen_width - gp.tile_size * 5, gp.tile_size * 3 , gp.tile_size * 4,gp.tile_size*5,null);

        // draw bunny
        var bunnyTraveller = new Traveller(gp);
        var bunny = bunnyTraveller.left_walking.get(1);
        var bunnyOverlay = bunnyTraveller.right_walking.get(1);
        g2d.drawImage(bunny,9, gp.screen_height /2, gp.tile_size * 3, gp.tile_size * 4, null);
        g2d.drawImage(bunnyOverlay,20, (gp.screen_height/2)-9, gp.tile_size * 3, gp.tile_size * 4, null);

        if (gp.key_handler.enterPressed){
            if (currentMenu == 0){
                gp.gameState = gp.playState;
            }
        }

    }


    public void drawCoordinates(Graphics2D g2d){
        g2d.setFont(pixel_font);
        g2d.setColor(Color.white);

        x = gp.player.World_x;
        y = gp.player.World_y;

        g2d.drawString( "X: "+x+" Y: "+y ,gp.screen_width - gp.tile_size*3,40);
        g2d.drawString( "X: "+x/gp.tile_size+" Y: "+y/gp.tile_size ,gp.screen_width - gp.tile_size*3,60);
    }


    private void drawPanel(Graphics2D g2d,int x, int y, int width , int height){
        Color black = new Color(0,0,0,220);
        g2d.setColor(black);
        g2d.fillRoundRect(x,y,width,height,35,35);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x,y,width-3,height,35,35);
    }


}
