package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenStick_1 extends SuperObject {

    public GreenStick_1(){
        tile_width = 48;
        tile_height = 48;
        name = "greenStick#001";
        try{
            var img = ImageIO.read(getClass().getResourceAsStream("/Objects/stick_green_1.png"));
            Image = util.scale(img,tile_width,tile_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
