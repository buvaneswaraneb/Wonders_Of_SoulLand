package Entity.Attacks;

import java.awt.*;

public abstract class AttackTemplate {
    public int x , y;
    public String direction;
    public int damage = 0;
    public Rectangle solidArea = new Rectangle(0,0,25,25);
    public int speed;
    public boolean onContact;

    public int solidDefaultAreaX,solidDefaultAreaY;
    public abstract void attack();

    public abstract void update();

    public abstract void drawAttack(Graphics2D g2d);


}
