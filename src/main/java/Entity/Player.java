package Entity;


import Main.GamePanel;
import Main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler key_H;
    int sprite_length = 6;
    public final int ScreenX;
    public final int ScreenY;

    public Player(GamePanel gp , KeyHandler key_handler){
        this.gp = gp;
        this.key_H = key_handler;
        setDefaultPlayerValues();
        getPlayerImage();

        ScreenX = gp.screen_width / 2 - ( gp.tile_size / 2 );
        ScreenY = gp.screen_height / 2 - ( gp.tile_size / 2 );

        solidArea = new Rectangle(); // inner solid area
        solidArea.x = 9;
        solidArea.y = 17;
        solidArea.height = 24;
        solidArea.width = 30;

    }

    private void setDefaultPlayerValues(){
        World_x = 23 * gp.tile_size;
        World_y = 22 * gp.tile_size;
        speed = 5;
        directions = "down";
    }

    public void update(){

        if ( !key_H.upPressed && !key_H.downPressed && !key_H.leftPressed && !key_H.rightPressed)  return;

        if (key_H.upPressed){
            directions  = "up";
        }
        if (key_H.downPressed){
            directions = "down";
        }
        if (key_H.leftPressed){
            directions = "left";
        }
        if (key_H.rightPressed){
            directions = "right";
        }


        collisionOn = false;
        gp.collisonEngine.Check_tile(this);

        if(!collisionOn){
            switch (directions){
                case "up":{
                    World_y -= speed;
                    break;
                }
                case "down":{
                    World_y += speed;
                    break;
                }
                case "left":{
                    World_x -= speed;
                    break;
                }
                case "right":{
                    World_x += speed;
                    break;
                }
            }
        }

        sprite_delay++;
        if(sprite_delay > 12){
            spite_number++;
            if (spite_number >= sprite_length) spite_number = 0;
            sprite_delay = 0;
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        Boolean mirror = false;
        switch (directions) {
            case "up": {
                image = up_walking.get(spite_number);
                break;
            }
            case "down": {
                image = down_walking.get(spite_number);
                break;
            }
            case "left": {
                image = right_walking.get(spite_number);
                g2d.drawImage(image,ScreenX, ScreenY,-gp.tile_size,gp.tile_size,null);
                return;
            }
            case "right": {
                image = right_walking.get(spite_number);
                break;
            }
        }
        g2d.drawImage(image,ScreenX, ScreenY,gp.tile_size,gp.tile_size,null);

    }

    private void getPlayerImage(){
        try{
            getSprites(6,"up/up_walking_",up_walking);
            getSprites(6,"down/down_walking_",down_walking);
            getSprites(6,"right/right_walking_",right_walking);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getSprites(int sprite_length, String sprite, ArrayList<BufferedImage> arr ) throws IOException{
       BufferedImage image;
       for(int i = 1 ; i <= sprite_length ; i++){
           String path = "/Player/" + sprite + i + ".png";
           var stream = getClass().getResourceAsStream(path);
           if (stream == null) System.out.println(sprite + i + " Sprite not found");
           image = ImageIO.read(stream);
           arr.add(image);
       }
    }

}

