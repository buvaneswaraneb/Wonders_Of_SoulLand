package Main;

import Entity.Traveller;
import Objects.GreenStick_1;

public class AssestsSetter {
    GamePanel gp;
    public AssestsSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjects(){
        gp.Obj[0] = new GreenStick_1();
        gp.Obj[0].worldX = 26 * gp.tile_size;
        gp.Obj[0].worldY = 20 * gp.tile_size;

        gp.Obj[1] = new GreenStick_1();
        gp.Obj[1].worldX = 19 * gp.tile_size;
        gp.Obj[1].worldY = 5 * gp.tile_size;

        gp.Obj[4] = new GreenStick_1();
        gp.Obj[4].worldX = 7 * gp.tile_size;
        gp.Obj[4].worldY = 10 * gp.tile_size;

        gp.Obj[6] = new GreenStick_1();
        gp.Obj[6].worldX = 41 * gp.tile_size;
        gp.Obj[6].worldY = 21 * gp.tile_size;

    }


    public void setNpcs(){
        gp.Npc[0] = new Traveller(gp);
        gp.Npc[0].World_x = 25 * gp.tile_size;
        gp.Npc[0].World_y = 20 * gp.tile_size;

    }
}
