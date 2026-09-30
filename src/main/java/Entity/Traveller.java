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
    String[] dialogues = new String[10];
    int dialogueIndex = 0;
    int counter = 0;


    public Traveller(GamePanel gp){
        super(gp);
        this.gp = gp;
        setDefault();
        setDialogues();
        directions = "left";


    }



    @Override
    public String dialogue() {
        if(gp.key_handler.enterPressed){
            counter++;
        }

        if (counter > 5){
            dialogueIndex++;
            counter = 0;
        }
        if (dialogueIndex > 4){
            gp.player.directions = "down";
            gp.gameState = gp.playState;
            dialogueIndex = 0;
        }
        return dialogues[dialogueIndex];
    }


    @Override
    public void update(){

//        collisionOn = false;
//        gp.collisonEngine.checkPlayer(this);
//
//        if (collisionOn) {
//            System.out.println("the entity detected the player"); // still the npc isn't in motion npc doesnt interact with player
//        }
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


    private void setDialogues(){
        dialogues[0] = "Hello Fellow Traveller !";
        dialogues[1] = "My Name is \"Niyan nah\" but call me Niya";
        dialogues[2] = "What your name Traveller ?";
        dialogues[3] = gp.player.name+" ? thats a good name :D";
        dialogues[4] = "Okay save a safe journey fellow traveller";
    }





}
