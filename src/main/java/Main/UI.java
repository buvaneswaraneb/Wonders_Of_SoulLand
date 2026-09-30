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

        if(gp.gameState == gp.playState){
            drawCoordinates(g2d);
            //@implement
        }
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen(g2d);
            //@implement
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


}
