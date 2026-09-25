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
    public int woodScore = 0;

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

        solidDefaultAreaX = solidArea.x;
        solidDefaultAreaY = solidArea.y;

        solidArea.height = 24;
        solidArea.width = 30;

    }

    private void setDefaultPlayerValues(){
        World_x = 23 * gp.tile_size;
        World_y = 22 * gp.tile_size;
        speed = 5;
        directions = "down";
        name = "Player";
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

        // check the collision between the tiles
        collisionOn = false;
        gp.collisonEngine.check_tile(this);

        // checking the collision between the objects
        int objIndex = gp.collisonEngine.checkObject(this,true);
        pickUpObj(objIndex);

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
                image = left_walking.get(spite_number);
                break;
            }
            case "right": {
                image = right_walking.get(spite_number);
                break;
            }
        }
        g2d.drawImage(image,ScreenX, ScreenY,null);
        if (debug) {
            g2d.setColor(Color.red);
            g2d.drawRect(ScreenX + solidArea.x, ScreenY + solidArea.y, solidArea.width, solidArea.height);
        }

    }

    private void getPlayerImage(){
        try{
            getSprites(6,"up/up_walking_",up_walking);
            getSprites(6,"down/down_walking_",down_walking);
            getSprites(6,"right/right_walking_",right_walking);

            for(BufferedImage image : right_walking){
                left_walking.add(mirrorVertical(image));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    private void pickUpObj(int obj_index){
        if (obj_index == Integer.MAX_VALUE) return;

        String objName = gp.Obj[obj_index].name;

        if(objName.equals("dried_branch#002") || objName.equals("greenStick#001")){
            gp.playSpecialEffects(2);
            gp.Obj[obj_index] = null;
            woodScore++;
            gp.ui.showMessage("You acquired "+ objName.substring(0,objName.length()-4)+ " X1");
        }

        if (objName.equals("PowerShow#003")) {
            gp.playSpecialEffects(0);
            gp.Obj[obj_index] = null;
            speed += 3;
        }

    }

    private void getSprites(int sprite_length, String sprite, ArrayList<BufferedImage> arr ) throws IOException{
       BufferedImage image;
       for(int i = 1 ; i <= sprite_length ; i++){
           String path = "/Player/" + sprite + i + ".png";
           var stream = getClass().getResourceAsStream(path);
           if (stream == null) System.out.println("Character Image is not Loading");
           image = ImageIO.read(stream);
           image = gp.util.scale(image,gp.tile_size,gp.tile_size);
           arr.add(image);
       }
    }

}

