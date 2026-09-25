package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Dried_branch_2 extends SuperObject{
    public Dried_branch_2(){
      tile_height = 48;
      tile_width = 48;
        name = "dried_branch#002";
        try{
            var img = ImageIO.read(getClass().getResourceAsStream("/Objects/dried_branch_2.png"));
            Image = util.scale(img,tile_width,tile_height);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
