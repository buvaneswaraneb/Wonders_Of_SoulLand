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

        g2d.setFont(pixel_font);
        g2d.setColor(Color.white);
        g2d.drawImage(wood_image,5,10,gp.tile_size,gp.tile_size,null);
        g2d.drawString(" X"+gp.player.woodScore , 40,40);

        x = gp.player.World_x;
        y = gp.player.World_y;

        g2d.drawString( "X: "+x+" Y: "+y ,gp.screen_width - gp.tile_size*3,40);
        g2d.drawString( "X: "+x/gp.tile_size+" Y: "+y/gp.tile_size ,gp.screen_width - gp.tile_size*3,60);

        if(messageOn == true){
            g2d.setFont(pixel_font.deriveFont(15f));
            g2d.drawString(message,10, 150);
            messageCounter++;

            if(messageCounter > 120){
                messageOn = false;
                messageCounter = 0;
            }
        }
    }
}
