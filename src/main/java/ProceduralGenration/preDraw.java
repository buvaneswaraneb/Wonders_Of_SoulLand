package ProceduralGenration;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class preDraw{

    GamePanel gp;
    preMap map;

    public preDraw(GamePanel gp, preMap map) {
        this.gp = gp;
        this.map = map;
    }


    public void drawLoading (Graphics2D g2d){
        //draw 
        g2d.dispose();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gp.screen_width, gp.screen_height);

        //Display Text
        String message = "Map is Loading..";
        
        int x = getXforCenteredText(g2d, message);
        int y = gp.screen_width / 2 ;
        g2d.drawString(message, x, y);
    }

    public int getXforCenteredText(Graphics2D g2d, String txt){
        int length = (int) g2d.getFontMetrics().getStringBounds(txt,g2d).getWidth();
        int x  = (gp.screen_width/2) - length/2;
        return x;
    }

}