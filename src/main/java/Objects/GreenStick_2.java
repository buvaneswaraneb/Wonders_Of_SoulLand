package Objects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GreenStick_2 extends SuperObject{
    GamePanel gp;
    public GreenStick_2(){
        this.gp = gp;
        name = "greenStick#002";
        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/Objects/stick_green_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
