package Entity;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public int World_x , World_y;
    public int speed;
    public String directions;
    int sprite_delay = 0;
    int spite_number = 1;

    //animations
    ArrayList<BufferedImage> up_walking = new ArrayList<>();
    ArrayList<BufferedImage> down_walking = new ArrayList<>();
    ArrayList<BufferedImage> right_walking = new ArrayList<>();
    ArrayList<BufferedImage> left_walking = new ArrayList<>();

    // collision
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int solidDefaultAreaX , solidDefaultAreaY;


    public BufferedImage mirrorVertical(BufferedImage inputImage) {
        // Keep x-axis at 1 and scale y-axis by -1 (flip)
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        // Shift the image back down into view
        tx.translate(-inputImage.getWidth(),0);

        // Apply transformation operation
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(inputImage, null);
    }

}
