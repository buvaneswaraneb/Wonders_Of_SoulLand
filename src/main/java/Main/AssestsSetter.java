package Main;

import Objects.GreenStick_1;
import Objects.GreenStick_2;

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

        gp.Obj[2] = new GreenStick_2();
        gp.Obj[2].worldX = 25 * gp.tile_size;
        gp.Obj[2].worldY = 20 * gp.tile_size;
    }

}
