package Tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Main.GamePanel;


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

        final String Wall_Tiles_133 = "tesseract_tiles/133_Wall_Tiles.png";
        get_tiles(133,Wall_Tiles_133);
        tiles.get(133).collision = true;
    
 
        final String Wall_Tiles_134 = "tesseract_tiles/134_Wall_Tiles.png";
        get_tiles(134,Wall_Tiles_134);
        tiles.get(134).collision = true;
    
 
        final String Wall_Tiles_135 = "tesseract_tiles/135_Wall_Tiles.png";
        get_tiles(135,Wall_Tiles_135);
        tiles.get(135).collision = true;
    
 
        final String Wall_Tiles_136 = "tesseract_tiles/136_Wall_Tiles.png";
        get_tiles(136,Wall_Tiles_136);
        tiles.get(136).collision = true;
    
 
        final String Wall_Tiles_8 = "tesseract_tiles/08_Wall_Tiles.png";
        get_tiles(8,Wall_Tiles_8);
        tiles.get(8).collision = true;
    
 
        final String Wall_Tiles_10 = "tesseract_tiles/10_Wall_Tiles.png";
        get_tiles(10,Wall_Tiles_10);
        tiles.get(10).collision = true;
    
 
        final String Wall_Tiles_208 = "tesseract_tiles/208_Wall_Tiles.png";
        get_tiles(208,Wall_Tiles_208);
        tiles.get(208).collision = true;
    
 
        final String Wall_Tiles_209 = "tesseract_tiles/209_Wall_Tiles.png";
        get_tiles(209,Wall_Tiles_209);
        tiles.get(209).collision = true;
    
 
        final String Wall_Tiles_210 = "tesseract_tiles/210_Wall_Tiles.png";
        get_tiles(210,Wall_Tiles_210);
        tiles.get(210).collision = true;
    
 
        final String Wall_Tiles_83 = "tesseract_tiles/83_Wall_Tiles.png";
        get_tiles(83,Wall_Tiles_83);
        tiles.get(83).collision = true;
    
 
        final String Wall_Tiles_211 = "tesseract_tiles/211_Wall_Tiles.png";
        get_tiles(211,Wall_Tiles_211);
        tiles.get(211).collision = true;
    
 
        final String Wall_Tiles_84 = "tesseract_tiles/84_Wall_Tiles.png";
        get_tiles(84,Wall_Tiles_84);
        tiles.get(84).collision = true;
    
 
        final String Wall_Tiles_86 = "tesseract_tiles/86_Wall_Tiles.png";
        get_tiles(86,Wall_Tiles_86);
        tiles.get(86).collision = true;
    
 
        final String Wall_Tiles_158 = "tesseract_tiles/158_Wall_Tiles.png";
        get_tiles(158,Wall_Tiles_158);
        tiles.get(158).collision = true;
    
 
        final String Wall_Tiles_159 = "tesseract_tiles/159_Wall_Tiles.png";
        get_tiles(159,Wall_Tiles_159);
        tiles.get(159).collision = true;
    
 
        final String Wall_Tiles_160 = "tesseract_tiles/160_Wall_Tiles.png";
        get_tiles(160,Wall_Tiles_160);
        tiles.get(160).collision = true;
    
 
        final String Wall_Tiles_161 = "tesseract_tiles/161_Wall_Tiles.png";
        get_tiles(161,Wall_Tiles_161);
        tiles.get(161).collision = true;
    
 
        final String Wall_Tiles_33 = "tesseract_tiles/33_Wall_Tiles.png";
        get_tiles(33,Wall_Tiles_33);
        tiles.get(33).collision = true;
    
 
        final String Wall_Tiles_35 = "tesseract_tiles/35_Wall_Tiles.png";
        get_tiles(35,Wall_Tiles_35);
        tiles.get(35).collision = true;
    
 
        final String Wall_Tiles_36 = "tesseract_tiles/36_Wall_Tiles.png";
        get_tiles(36,Wall_Tiles_36);
        tiles.get(36).collision = true;
    
 
        final String Wall_Tiles_233 = "tesseract_tiles/233_Wall_Tiles.png";
        get_tiles(233,Wall_Tiles_233);
        tiles.get(233).collision = true;
    
 
        final String Wall_Tiles_234 = "tesseract_tiles/234_Wall_Tiles.png";
        get_tiles(234,Wall_Tiles_234);
        tiles.get(234).collision = true;
    
 
        final String Wall_Tiles_235 = "tesseract_tiles/235_Wall_Tiles.png";
        get_tiles(235,Wall_Tiles_235);
        tiles.get(235).collision = true;
    
 
        final String Wall_Tiles_108 = "tesseract_tiles/108_Wall_Tiles.png";
        get_tiles(108,Wall_Tiles_108);
        tiles.get(108).collision = true;
    
 
        final String Wall_Tiles_236 = "tesseract_tiles/236_Wall_Tiles.png";
        get_tiles(236,Wall_Tiles_236);
        tiles.get(236).collision = true;
    
 
        final String Wall_Tiles_109 = "tesseract_tiles/109_Wall_Tiles.png";
        get_tiles(109,Wall_Tiles_109);
        tiles.get(109).collision = true;
    
 
        final String Wall_Tiles_110 = "tesseract_tiles/110_Wall_Tiles.png";
        get_tiles(110,Wall_Tiles_110);
        tiles.get(110).collision = true;
    
 
        final String Wall_Tiles_111 = "tesseract_tiles/111_Wall_Tiles.png";
        get_tiles(111,Wall_Tiles_111);
        tiles.get(111).collision = true;
    
 
        final String Wall_Tiles_183 = "tesseract_tiles/183_Wall_Tiles.png";
        get_tiles(183,Wall_Tiles_183);
        tiles.get(183).collision = true;
    
 
        final String Wall_Tiles_184 = "tesseract_tiles/184_Wall_Tiles.png";
        get_tiles(184,Wall_Tiles_184);
        tiles.get(184).collision = true;
    
 
        final String Wall_Tiles_185 = "tesseract_tiles/185_Wall_Tiles.png";
        get_tiles(185,Wall_Tiles_185);
        tiles.get(185).collision = true;
    
 
        final String Wall_Tiles_186 = "tesseract_tiles/186_Wall_Tiles.png";
        get_tiles(186,Wall_Tiles_186);
        tiles.get(186).collision = true;
    
 
        final String Wall_Tiles_58 = "tesseract_tiles/58_Wall_Tiles.png";
        get_tiles(58,Wall_Tiles_58);
        tiles.get(58).collision = true;
    
 
        final String Wall_Tiles_61 = "tesseract_tiles/61_Wall_Tiles.png";
        get_tiles(61,Wall_Tiles_61);
        tiles.get(61).collision = true;

        final String Floor_Tiles_257 = "tesseract_tiles/257_Floors_Tiles.png"; // path way
        get_tiles(257,Floor_Tiles_257);
        tiles.get(257).collision = false;

        final String Water_tiles_52 = "tesseract_tiles/52_Water_tiles.png"; // green
        get_tiles(52,Water_tiles_52);
        tiles.get(52).collision = false;


        tiles.put(-1,new Tile());
        tiles.get(-1).collision = false;


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
