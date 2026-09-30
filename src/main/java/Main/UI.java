package Main;

import Objects.GreenStick_1;
import Objects.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font load_font;
    Font pixel_font;
    BufferedImage wood_image;
    SuperObject wood;
    Graphics2D g2d;
    private String message = "";
    private boolean messageOn = false;
    private int messageCounter = 0;

    public String currentDialogue = " ";

    int x;
    int y;


    public UI(GamePanel gp){
        this.gp = gp;
        this.wood = new GreenStick_1();
        this.wood_image = wood.Image;
        try {
            InputStream is = getClass().getResourceAsStream("/Font/pixel_font.ttf");
            load_font = Font.createFont(Font.TRUETYPE_FONT,is);
            pixel_font = load_font.deriveFont(Font.PLAIN,20);
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
        g2d.setColor(Color.white);
        g2d.setFont(pixel_font);



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
    }


    public int getXforCenteredText(String txt){
        int length = (int)g2d.getFontMetrics().getStringBounds(txt,g2d).getWidth();
        int x  = gp.screen_width/2 - length/2;
        return x;
    }


    public void drawPauseScreen(Graphics2D g2d){
        String text = "Paused";
        g2d.setFont(g2d.getFont().deriveFont(80f));
        int x = getXforCenteredText(text);
        int y = gp.screen_height/2;
        g2d.drawString(text,x,y);
    }


    public void drawCoordinates(Graphics2D g2d){
        g2d.setFont(pixel_font);
        g2d.setColor(Color.white);

        x = gp.player.World_x;
        y = gp.player.World_y;

        g2d.drawString( "X: "+x+" Y: "+y ,gp.screen_width - gp.tile_size*3,40);
        g2d.drawString( "X: "+x/gp.tile_size+" Y: "+y/gp.tile_size ,gp.screen_width - gp.tile_size*3,60);
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


    private void drawPanel(Graphics2D g2d,int x, int y, int width , int height){
        Color black = new Color(0,0,0,220);
        g2d.setColor(black);
        g2d.fillRoundRect(x,y,width,height,35,35);

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x,y,width-3,height,35,35);
    }


}
