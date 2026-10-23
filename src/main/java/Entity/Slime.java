package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import Entity.Attacks.AttackCollison;
import Entity.Attacks.AttackTemplate;
import Entity.Attacks.SlugShot;
import Main.GamePanel;


public class Slime extends Entity {

    // view Field
    int viewDistanceX = 5 * gp.tile_size;
    int viewDistanceY = 5 * gp.tile_size;

    // debug path
    ArrayList<int[]> testRendering  = new ArrayList<>();
    Entity[] targets = new Entity[]{gp.player};

    //Attack -- @test
    ArrayList<AttackTemplate> ATT = new ArrayList<>();
    int attackIndex = 0;


    // solid area
    public Slime(GamePanel gp) {
        super(gp);
        setDefaultValues();
        setSprites();
    }



    // ============== Setters =================
    private  void setDefaultValues(){
        name = "slime";
        speed = 1;
        direction = "down";

        FOV.height = viewDistanceY;
        FOV.width = viewDistanceX;

        FOV.x = - (gp.tile_size * 2);
        FOV.y = - (gp.tile_size * 2 );

        solidDefaultFOVAreaX = FOV.x;
        solidDefaultFOVAreaY = FOV.y;

        solidArea.x = 25;
        solidArea.y = 30;
    }

    private  void setSprites(){
        try {
            gp.util.getSprites(4, "/Monster/BlueSlime/down/blue_slime_down_", down_walking, 48, 48);
            gp.util.getSprites(4,"/Monster/BlueSlime/left/blue_slime_left_",left_walking,48,48);
            gp.util.getSprites(4,"/Monster/BlueSlime/right/blue_slime_right_",right_walking,48,48);
            gp.util.getSprites(4,"/Monster/BlueSlime/up/blue_slime_up_",up_walking,48,48);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(){

        collisionOn = false;
        gp.collisonEngine.checkPlayer(this);

        onRange = false;
        int index = gp.collisonEngine.checkRange(this,targets);

        direction = gp.util.findDirections(this.grid, new int[]{gp.player.grid.x,gp.player.grid.y});

        System.out.println(direction);

        if(onRange){
            if (collisionOn) pathList.clear();

            else if (gp.player.World_x != lastX || gp.player.World_y != lastY){
                lastX = gp.player.World_x;
                lastY = gp.player.World_y;
                find(gp.player);
            }
        }else {
            pathList.clear();
        }


        // initiate attack

        if (gp.key_handler.enterPressed){

            gp.key_handler.enterPressed = false;

            AttackTemplate attack = new SlugShot(this,gp);
            attack.attack();

            ATT.add(attack);
            System.out.println(ATT);

        }

        // attack

        for (int i = 0; i < ATT.size() ; i++){
            ATT.get(i).update();

            if ( ATT.get(i).onContact || !gp.util.onScreen(ATT.get(i),gp)){
                ATT.remove(i);
            }
        }

        travelScheduler();
    }


    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        int ScreenX = ( World_x - gp.player.World_x ) + gp.player.ScreenX;
        int ScreenY = ( World_y - gp.player.World_y ) + gp.player.ScreenY;

        if (World_x + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                World_x - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                World_y + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                World_y - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            if (direction.equals("northWest") || direction.equals("northEast")) direction = "up";
            else if (direction.equals("southWest") || direction.equals("southEast")) direction = "down";
            else if (direction.equals("onDestination")) direction = "down";


            switch (direction) {
                case "up" -> {
                    image = up_walking.get(sprite_number);
                    break;
                }
                case "down" -> {
                    image = down_walking.get(sprite_number);
                    break;
                }
                case "left" -> {
                    image = left_walking.get(sprite_number);
                    break;
                }
                case "right" -> {
                    image = right_walking.get(sprite_number);
                    break;
                }
            }

            g2d.drawImage(image, ScreenX + offset_x, ScreenY + offset_y, null);

            if (gp.debug) {
                g2d.setColor(Color.red);
                g2d.drawRect(ScreenX + solidArea.x + offset_x, ScreenY + solidArea.y + offset_y, solidArea.width, solidArea.height);

                g2d.setColor(Color.BLUE);
                g2d.drawRect(ScreenX + FOV.x + offset_x, ScreenY + FOV.y + offset_y, FOV.width, FOV.height);
            }

        }

        if(debug){
        for (int[] i : pathList) {
            g2d.setColor(Color.gray);
            int uScreenX = (i[0] * gp.tile_size) - gp.player.World_x + gp.player.ScreenX;
            int uScreenY = (i[1] * gp.tile_size) - gp.player.World_y + gp.player.ScreenY;

            if ((i[0] * gp.tile_size) + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                    (i[0] * gp.tile_size) - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                    (i[1] * gp.tile_size) + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                    (i[1] * gp.tile_size) - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            g2d.fillOval(uScreenX,uScreenY,20,20);
            }
        }
        }

        // attack @testing
        for(AttackTemplate attack : ATT){
            if (attack == null) continue;
            attack.drawAttack(g2d);
        }
    }




    // ============= Tools =====================

    @Override
    public String dialogue() {
        gp.gameState = gp.playState;
        gp.player.collisionOn = true;
        return "";
    }

}
