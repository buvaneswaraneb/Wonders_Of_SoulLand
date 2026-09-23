package Main;

import Objects.GreenStick_1;
import Objects.Dried_branch_2;
import Objects.PowerShoe_3;

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

        gp.Obj[2] = new Dried_branch_2();
        gp.Obj[2].worldX = 25 * gp.tile_size;
        gp.Obj[2].worldY = 20 * gp.tile_size;


        gp.Obj[3] = new PowerShoe_3();
        gp.Obj[3].worldX = 24 * gp.tile_size;
        gp.Obj[3].worldY = 20 * gp.tile_size;

        gp.Obj[4] = new GreenStick_1();
        gp.Obj[4].worldX = 7 * gp.tile_size;
        gp.Obj[4].worldY = 10 * gp.tile_size;

        gp.Obj[5] = new Dried_branch_2();
        gp.Obj[5].worldX = 36 * gp.tile_size;
        gp.Obj[5].worldY = 15 * gp.tile_size;


        gp.Obj[6] = new GreenStick_1();
        gp.Obj[6].worldX = 41 * gp.tile_size;
        gp.Obj[6].worldY = 21 * gp.tile_size;



    }

}
