package Entity;
import Main.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    public int World_x , World_y;
    public int speed;
    public String directions;
    int sprite_delay = 0;
    int spite_number = 1;
    public String name ;
    public boolean debug = false;
    GamePanel gp;

    //animations
    ArrayList<BufferedImage> up_walking = new ArrayList<>();
    ArrayList<BufferedImage> down_walking = new ArrayList<>();
    ArrayList<BufferedImage> right_walking = new ArrayList<>();
    ArrayList<BufferedImage> left_walking = new ArrayList<>();

    // collision
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public boolean collisionOn = false;
    public int solidDefaultAreaX , solidDefaultAreaY;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }


    public void update(){

    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        int ScreenX = World_x - gp.player.World_x + gp.player.ScreenX;
        int ScreenY = World_y - gp.player.World_y + gp.player.ScreenY;

        if (World_x + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                World_x - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                World_y + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                World_y - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

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

            g2d.drawImage(image, ScreenX, ScreenY, null);

        }
    }


    public BufferedImage mirrorVertical(BufferedImage inputImage) {
        // Keep x-axis at 1 and scale y-axis by -1 (flip)
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        // Shift the image back down into view
        tx.translate(-inputImage.getWidth(),0);
        // Apply transformation operation
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(inputImage, null);
    }


    public abstract String dialogue();

}
