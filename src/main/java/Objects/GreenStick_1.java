package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenStick_1 extends SuperObject {
    GamePanel gp;
    public GreenStick_1(){
        this.gp = gp;
        name = "greenStick#001";
        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/Objects/stick_green_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
