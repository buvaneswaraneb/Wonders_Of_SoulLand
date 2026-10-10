package Biome;

import Entity.Entity;
import Objects.SuperObject;

public class Biome {
    /*
    Biome Blueprint

    **Required**
    initialize 2d arrays {map,overlay}
    initialize 1d arrays (npc,objects}
    with the custom size
     */

    public String name;
    public Boolean isOverlayPresent;
    public int BiomeHeight;
    public int BiomeWidth;

    public int[][] map = new int[1][1];
    public int[][] overlay = new int[1][1];

    // npc details
    public Entity[] npc = new Entity[1];

    // objects
    public SuperObject[] object = new SuperObject[1];






}
