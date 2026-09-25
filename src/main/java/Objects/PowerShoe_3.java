package Objects;

import jdk.jshell.execution.Util;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PowerShoe_3 extends SuperObject{

    public PowerShoe_3() {
        tile_height = 48;
        tile_width = 48;
        name = "PowerShow#003";
        try{
            var img = ImageIO.read(getClass().getResourceAsStream("/Objects/power_shoe.png"));
            Image = util.scale(img,tile_width,tile_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
