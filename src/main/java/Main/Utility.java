package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {

    public BufferedImage scale(BufferedImage original, int width , int height){
        // used to scale images efficiently
        BufferedImage scaledImage = new BufferedImage(height,width,original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original,0,0,width,height,null);
        g2d.dispose();
        return scaledImage;
    }
}
