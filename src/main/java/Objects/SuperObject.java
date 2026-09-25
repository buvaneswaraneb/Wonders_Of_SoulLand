package Objects;

import Main.GamePanel;
import Main.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage Image;
    public String name;
    public int worldX, worldY;
    public boolean collisionOn = false;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int soilDefaultAreaX = 0;
    public int getSoilDefaultAreaY = 0;
    public boolean debug = false;
    public Utility util = new Utility();
    public int tile_width,tile_height;

    public void draw(GamePanel gp, Graphics2D g2d) {
        int ScreenX = worldX - gp.player.World_x + gp.player.ScreenX;
        int ScreenY = worldY - gp.player.World_y + gp.player.ScreenY;

        if (worldX + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                worldX - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                worldY + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                worldY - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            g2d.drawImage(Image, ScreenX, ScreenY,null);
        }
    }

}