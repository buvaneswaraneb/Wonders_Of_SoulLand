package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenStick_1 extends SuperObject { ;
    public GreenStick_1(){
        name = "greenStick#001";
        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/Objects/stick_green_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
