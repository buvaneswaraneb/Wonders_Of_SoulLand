package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Dried_branch_2 extends SuperObject{
    GamePanel gp;
    public Dried_branch_2(){
        this.gp = gp;
        name = "dried_branch#002";
        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/Objects/dried_branch_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
