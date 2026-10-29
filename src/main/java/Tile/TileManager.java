package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


public class TileManager{

    GamePanel gp;
    public int[][] map;

    public HashMap<Integer,Tile> tiles = new HashMap<>();


    public TileManager(GamePanel gp){
        this.gp = gp;
        this.map = new int[gp.max_world_row][gp.max_world_col];
        setTiles();

    }

    private void setTiles(){
        // tiles grass
        final String grass = "grass.png";
        get_tiles(0,grass); // grass index 0
        tiles.get(0).collision = false;

        final String water = "water.png";
        get_tiles(1,water);
        tiles.get(1).collision = true;

        final String stone = "stone.png";
        get_tiles(2,stone);
        tiles.get(2).collision = true;

        final String pineTree = "tree.png"; //overlay
        get_tiles(3,pineTree);
        tiles.get(3).collision = true;

        final String blueMushroom = "blue_mushroom.png"; //overlay
        get_tiles(4,blueMushroom);
        tiles.get(4).collision = true;

        final String mudPath = "path/path_mud_1.png";
        get_tiles(5,mudPath);
        tiles.get(5).collision = false;

        final String mirana_sand_1 = "mirana-desert/mirana-biome-1-sand.png";
        get_tiles(357,mirana_sand_1);
        tiles.get(357).collision = false;

        if(tiles.get(357).tile_image == null) System.out.println("image not found");

        //temp
        tiles.put(257, tiles.get(5));
        tiles.put(252, tiles.get(0));

    }


    public Tile getTilesbyMap(int[][] map,int i , int j){
        int index = map[i][j];
        if (tiles.containsKey(index)) return tiles.get(index);
        return null;
    }


    private void get_tiles(int idx, String FileName){

        String path = "/Tiles/"+FileName;
        if (!tiles.containsKey(idx)) tiles.put(idx, new Tile());
        var stream = getClass().getResourceAsStream(path);
        if (stream == null) System.out.println("Tile : " + path +" Not Found");
        try {
            assert stream != null;
            tiles.get(idx).tile_image = gp.util.scale(ImageIO.read(stream), gp.tile_size, gp.tile_size);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void background(Graphics2D g2d){
        try{
            int world_col = 0;
            int world_row = 0;


            while(world_col < gp.currentBiome.BiomeWidth && world_row < gp.currentBiome.BiomeHeight){

                int idx = gp.currentBiome.map[world_row][world_col];

                BufferedImage image = tiles.get(idx).tile_image;

                if(image == null) continue;

                int worldX = world_col * gp.tile_size;
                int worldY = world_row * gp.tile_size;

                int ScreenX = worldX - gp.player.World_x + gp.player.ScreenX;
                int ScreenY = worldY - gp.player.World_y + gp.player.ScreenY;

                if (worldX + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                        worldX - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                        worldY + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                        worldY - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

                    g2d.drawImage(image,ScreenX,ScreenY,null);}
                world_col++;

                if(world_col == gp.currentBiome.BiomeWidth){
                    world_row++;
                    world_col = 0;

                }
            }

        } catch (Exception e) {
            System.out.println("Exception while drawing  Backround image");
            e.printStackTrace();
        }
    }

    private void overlay(Graphics2D g2d){
        if(!gp.currentBiome.isOverlayPresent) return;
        try{
            int world_col = 0;
            int world_row = 0;

            while(world_col < gp.currentBiome.BiomeWidth && world_row < gp.currentBiome.BiomeHeight) {
                int idx = gp.currentBiome.overlay[world_row][world_col];
                if (idx != 0 && tiles.get(idx) != null && tiles.get(idx).tile_image != null) {
                    BufferedImage image = tiles.get(idx).tile_image;

                    int worldX = world_col * gp.tile_size;
                    int worldY = world_row * gp.tile_size;

                    int ScreenX = worldX - gp.player.World_x + gp.player.ScreenX;
                    int ScreenY = worldY - gp.player.World_y + gp.player.ScreenY;

                    if (worldX + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                            worldX - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                            worldY + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                            worldY - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

                        g2d.drawImage(image, ScreenX, ScreenY,null);
                    }
                }
                world_col++;

                if (world_col == gp.currentBiome.BiomeWidth) {
                    world_row++;
                    world_col = 0;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2d){
        background(g2d);
        overlay(g2d);

    }
}
