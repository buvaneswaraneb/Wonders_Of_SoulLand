package Objects.Tools.Axe;

import java.awt.Graphics2D;

import Entity.Entity;
import Entity.Player;
import Main.GamePanel;
import Objects.Weapon;

public class Pine_StoneAxe extends Weapon{
    GamePanel gp;
    Entity player;

    public Pine_StoneAxe(GamePanel gp, Player player){
        super(10, 30, 20, 8, 3);

        this.gp = gp;
        this.player = player;
    }

    @Override
    public void attak() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void draw(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
