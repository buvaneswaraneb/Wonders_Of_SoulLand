package Objects;

import java.awt.Graphics2D;

public abstract class Weapon {
    public int x;
    public int y;

    public final int height;
    public final int width;
    public final int durability;
    public final int damage;
    public final int coolDown;

    public Weapon(int width, int height, int durability ,int damage , int coolDown){
        this.height = height;
        this.width = width;
        this.durability = durability;
        this.damage = damage;
        this.coolDown = coolDown; // calculate by the count
    }

    public abstract void attak();
    public abstract void draw(Graphics2D g2d);



}
