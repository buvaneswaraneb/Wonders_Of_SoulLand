package Entity;

import Main.GamePanel;

import javax.swing.*;
import java.io.IOException;

public class Slime extends Entity {
    int counter = 0;
    int globalCounter = 0;
    int index = 0;
    String[] path ;
    boolean skip = true;


    public Slime(GamePanel gp) {
        super(gp);
        setDefaultValues();
        setSprites();

        int[] playerCoords = new int[]{gp.player.World_y/gp.max_world_row, gp.player.World_x/gp.max_world_col};
        int[] EntityCords = new int[]{World_y/gp.max_world_row,World_x/gp.max_world_col};
        String pathString = gp.util.bfs(playerCoords,EntityCords,gp.currentBiome.map,gp);
        this.path = pathString.split("//s");

        for (String p : path){
            System.out.println(p);
        }

    }

    @Override
    public void update() {

        counter++;

        if (index >= path.length || skip){
            int[] playerCoords = new int[]{gp.player.World_y/gp.max_world_row, gp.player.World_x/gp.max_world_col};
            int[] EntityCords = new int[]{World_y/gp.max_world_row,World_x/gp.max_world_col};

            String pathString = gp.util.bfs(EntityCords,playerCoords,gp.currentBiome.map,gp).trim();

            if (pathString.equals("NO VALID PATH FOUND")) skip = true;
            else skip = false;
            path = pathString.split(" ");
            index = 0;
        }

        if (counter >= 12  && !skip){
            System.out.println(path.length);
            directions = path[index] == null ? directions : path[index];
            System.out.println(directions);
            index++;
        }
        if(skip) index = 0;


        if (!skip){
            // directions
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
            if (sprite_delay > 6){
                sprite_number++;
                if (sprite_number >= 4){
                    sprite_number = 0;
                }
                sprite_delay= 0;
            }

    }

    /*
    ================== PATH FINDING ======================
     */


    // ============== Setters =================
    protected void setDefaultValues(){
        speed = 4;
        directions = "down";
    }

    protected void setSprites(){
        try {
            gp.util.getSprites(4, "/Monster/BlueSlime/down/blue_slime_down_", down_walking, 48, 48);
            gp.util.getSprites(4,"/Monster/BlueSlime/left/blue_slime_left_",left_walking,48,48);
            gp.util.getSprites(4,"/Monster/BlueSlime/right/blue_slime_right_",right_walking,48,48);
            gp.util.getSprites(4,"/Monster/BlueSlime/up/blue_slime_up_",up_walking,48,48);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ============= Tools =====================

    @Override
    public String dialogue() {
        gp.gameState = gp.playState;
        gp.player.World_x -= gp.tile_size;
        return "";
    }
}
