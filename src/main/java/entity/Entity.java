package entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    public int World_x , World_y;
    public int speed;
    public String directions;
    int sprite_delay = 0;
    int spite_number = 1;

    //animations
    ArrayList<BufferedImage> up_walking = new ArrayList<>();
    ArrayList<BufferedImage> down_walking = new ArrayList<>();
    ArrayList<BufferedImage> right_walking = new ArrayList<>();
    //ArrayList<BufferedImage> left_walking = new ArrayList<>();

    // collision
    public Rectangle solidArea;
    public boolean collisionOn = false;

}
