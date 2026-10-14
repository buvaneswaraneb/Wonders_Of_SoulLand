package Main;

import Biome.Biome;
import Entity.Entity;
import Entity.Traveller;
import Entity.Slime;
import Objects.GreenStick_1;

public class AssestsSetter {
    GamePanel gp;
    public AssestsSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjects(){
        setDukeForestObjects();
    }

    public void setNpcs(){
        setDukeForestNPC();
    }


    /*
    =============== SETTERS ===================
     */


        /**
        *Duke Forest has limited of npc size, change the size manually at the BiomeManager class
        *current size is 10;
         */

    protected void setDukeForestNPC(){
        Biome dukeForest = gp.biomeManager.getBiome("B001");
        Entity traveller = new Traveller(gp);
        traveller.collisionOn = true;
        traveller.World_x = 25 * gp.tile_size;
        traveller.World_y = 20 * gp.tile_size;

        dukeForest.npc[0] = traveller;

        Entity slime = new Slime(gp);
        slime.World_x = 25 * gp.tile_size;
        slime.World_y =  24 * gp.tile_size;

        dukeForest.npc[1] = slime;

    }


    protected void setDukeForestObjects(){

        Biome dukeForest = gp.biomeManager.getBiome("B001");

        var object1 = new GreenStick_1();
        object1.worldX = 26 * gp.tile_size;
        object1.worldY = 20 * gp.tile_size;
        dukeForest.object[0] = object1;

        var object2 = new GreenStick_1();
        object2.worldX = 19 * gp.tile_size;
        object2.worldY = 5 * gp.tile_size;
        dukeForest.object[1] = object2;

        var object3 = new GreenStick_1();
        object3.worldX = 7 * gp.tile_size;
        object3.worldY = 10 * gp.tile_size;
        dukeForest.object[2] = object3;

        var object4 = new GreenStick_1();
        object4.worldX = 41 * gp.tile_size;
        object4.worldY = 21 * gp.tile_size;
        dukeForest.object[3] = object4;
    }
}
