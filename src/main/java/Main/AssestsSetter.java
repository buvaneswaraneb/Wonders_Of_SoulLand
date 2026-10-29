package Main;

import Biome.Biome;
import Entity.Entity;
import Entity.Traveller;
import Entity.Slime;
import GridManager.GridNode;
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
        setMiranaForestObjects();
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

        setAssertEntity(dukeForest,new Traveller(gp),25,20);

        setAssertEntity(dukeForest,new Slime(gp),25,24,24,24);
        setAssertEntity(dukeForest,new Slime(gp),25,27);;
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


    protected void setMiranaForestObjects(){

        Biome miranaForest = gp.biomeManager.getBiome("B002");

        setAssertEntity(miranaForest,new Slime(gp),25,24,gp.tile_size,gp.tile_size);

    }

    private void setAssertEntity(Biome biome ,Entity entity, int x, int y){

        if (biome.npcIndex >= biome.npc.length) throw new ArrayIndexOutOfBoundsException();

        entity.World_x = x * gp.tile_size;
        entity.World_y = y * gp.tile_size;

        // grid
        entity.grid = new GridNode(x,y);

        // sold area
        entity.solidArea.height = gp.tile_size;
        entity.solidArea.width = gp.tile_size;

        biome.npc[biome.npcIndex] = entity;

        biome.npcIndex++;
    }


    private void setAssertEntity(Biome biome ,Entity entity, int x, int y, int height , int width){

        if (biome.npcIndex >= biome.npc.length) throw new ArrayIndexOutOfBoundsException();

        entity.World_x = x * gp.tile_size;
        entity.World_y = y * gp.tile_size;

        // grid
        entity.grid = new GridNode(x,y);

        // sold area
        entity.solidArea.height = height;
        entity.solidArea.width = width;

        biome.npc[biome.npcIndex] = entity;

        biome.npcIndex++;
    }
}
