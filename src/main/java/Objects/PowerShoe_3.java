package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PowerShoe_3 extends SuperObject{
    public PowerShoe_3() {
        name = "PowerShow#003";
        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/Objects/power_shoe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
