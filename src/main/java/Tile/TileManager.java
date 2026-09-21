package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager{

    GamePanel gp;
    public Tile[] tiles ;
    public int[][] map;
    public int[][] overlay_map;
    int map_size_row = 50;
    int map_size_col = 50;

    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tiles = new Tile[10];
        this.map = new int[gp.max_world_row][gp.max_world_col];
        setTiles();
        setMap();

        //debug
//        displayMatrix();

    }

    private void setTiles(){
        // tiles grass
        final String grass = "grass.png";
        get_tiles(0,grass); // grass index 0
        tiles[0].collision = false;

        final String water = "water.png";
        get_tiles(1,water);
        tiles[1].collision = true;

        final String stone = "stone.png";
        get_tiles(2,stone);
        tiles[2].collision = true;

        final String pineTree = "tree.png"; //overlay
        get_tiles(3,pineTree);
        tiles[3].collision = true;

        final String blueMushroom = "blue_mushroom.png"; //overlay
        get_tiles(4,blueMushroom);
        tiles[4].collision = false;

        final String mudPath = "/path/path_mud_1.png";
        get_tiles(5,mudPath);
        tiles[5].collision = false;

    }

    private void setMap(){

        String trail_path = "trail.txt";
        try {
            map = load_map(trail_path);
            overlay_map = load_map("trail_overlay.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void get_tiles(int idx, String FileName){

        String path = "/tiles/"+FileName;
        if (tiles[idx] == null) tiles[idx] = new Tile();

        var stream = getClass().getResourceAsStream(path);
        if (stream == null) System.out.println("Tile : " + path +" Not Found");
        try {
            tiles[idx].tile_image = ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[][] load_map(String FileName) throws IOException {
        String path_map = "/Map/"+FileName;
        int[][] local_map = new int[gp.max_world_row][gp.max_world_col];
        InputStream stream = getClass().getResourceAsStream(path_map);
        if (stream == null) System.out.println("");
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        for(int row = 0 ; row < gp.max_world_row; row++){
            String line = br.readLine();
            int col = 0;
            for(String digit : line.split(" ")){
                local_map[row][col] = Integer.parseInt(digit);
                col++;
            }
        }
        return local_map;
    }


    //debug

    private void displayMatrix(){
        for(int[] d : map){
            for(int i : d){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void run(){

    }


    private void background(Graphics2D g2d){
        try{
            int world_col = 0;
            int world_row = 0;


            while(world_col < gp.max_world_col && world_row < gp.max_world_row){

                BufferedImage image = tiles[map[world_row][world_col]].tile_image;

                int worldX = world_col * gp.tile_size;
                int worldY = world_row * gp.tile_size;

                int ScreenX = worldX - gp.player.World_x + gp.player.ScreenX;
                int ScreenY = worldY - gp.player.World_y + gp.player.ScreenY;

                if (worldX + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                        worldX - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                        worldY + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                        worldY - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

                    g2d.drawImage(image,ScreenX,ScreenY,gp.tile_size, gp.tile_size,null);}
                world_col++;

                if(world_col == gp.max_world_col){
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
        if (overlay_map == null) return;
        try{
            int world_col = 0;
            int world_row = 0;

            while(world_col < gp.max_world_col && world_row < gp.max_world_row) {
                int idx = overlay_map[world_row][world_col];
                if (idx != 0 && tiles[idx] != null && tiles[idx].tile_image != null) {
                    BufferedImage image = tiles[idx].tile_image;

                    int worldX = world_col * gp.tile_size;
                    int worldY = world_row * gp.tile_size;

                    int ScreenX = worldX - gp.player.World_x + gp.player.ScreenX;
                    int ScreenY = worldY - gp.player.World_y + gp.player.ScreenY;

                    if (worldX + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                            worldX - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                            worldY + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                            worldY - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

                        g2d.drawImage(image, ScreenX, ScreenY, gp.tile_size, gp.tile_size, null);
                    }
                }
                world_col++;

                if (world_col == gp.max_world_col) {
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
