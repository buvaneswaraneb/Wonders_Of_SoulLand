package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Traveller extends Entity{
    GamePanel gp;
    int totalSprites = 2;
    int spriteDelay = 0;
    BufferedImage image;
    BufferedImage image_cover;

    public Traveller(GamePanel gp){
        super(gp);
        this.gp = gp;
        setDefault();
        directions = "left";

    }

    private void setDefault(){
        collisionOn = false;
        try {
            var Image = ImageIO.read(getClass().getResourceAsStream("/NPC/Traveller/character-idle.png"));
            left_walking.add(gp.util.scale(Image,gp.tile_size,gp.tile_size));

            Image = ImageIO.read(getClass().getResourceAsStream("/NPC/Traveller/character-idle1.png"));
            left_walking.add(gp.util.scale(Image,gp.tile_size,gp.tile_size));

            Image = ImageIO.read(getClass().getResourceAsStream("/NPC/Traveller/character-idle-cover.png"));
            right_walking.add(gp.util.scale(Image,gp.tile_size,gp.tile_size));

            Image = ImageIO.read(getClass().getResourceAsStream("/NPC/Traveller/character-idle-cover-1.png"));
            right_walking.add(gp.util.scale(Image,gp.tile_size,gp.tile_size));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(){

        collisionOn = false;
        gp.collisonEngine.checkPlayer(this);

        if (collisionOn) {
            System.out.println("the entity detected the player"); // still the npc isn't in motion npc doesnt interact with player
        }
        spriteDelay++;
        if(spriteDelay >= 30){
            spite_number++;
            spriteDelay = 0;
            if (spite_number >= totalSprites){
                spite_number = 0;
            }
        }

    }


    @Override
    public void draw(Graphics2D g2d){
        image = left_walking.get(spite_number);
        image_cover = right_walking.get(spite_number);


        int ScreenX = World_x - gp.player.World_x + gp.player.ScreenX;
        int ScreenY = World_y - gp.player.World_y + gp.player.ScreenY;

        if (World_x + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                World_x - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                World_y + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                World_y - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            g2d.drawImage(image, ScreenX, ScreenY, null);
            g2d.drawImage(image_cover, ScreenX+3, ScreenY-5, null);
        }

    }
}
