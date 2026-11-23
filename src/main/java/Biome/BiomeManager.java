package Biome;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

import Entity.Entity;
import Main.GamePanel;
import Main.Noise;
import Objects.SuperObject;
import ProceduralGenration.preMap;

public class BiomeManager {
    private HashMap<String,Biome> biomehashMap = new HashMap<>();
    GamePanel gp;

    public BiomeManager(GamePanel gp){
        this.gp = gp;
    }

    /**
     * <h1>! only for testing</h1>
     */
    public BiomeManager(){

    }

    /**
     * Use only to get biome after initialization
     * this does not run the grid initializer result in null pointer exception
     * use useBiome(key) api to change biome at run time
     * @param key
     * @return
     */
    public Biome getBiome(String key) {
        return biomehashMap.get(key);
    }

    /**
     * Gets Biome , and reinitialise the grid
     * use at run time biome change <br>
     * Note: this function is tightly coupled with GamePanel <br>
     * <br>
     * <strong>Its Directly Changes the GamePanel CurrentBiome</strong>
     * @param key
     * @return
     */
    public void useBiome(String key){
        Biome b = getBiome(key);

        // use the previous biome gridNode for the player
        gp.currentBiome = b;
        gp.gridManager.initializeGrid();

    }


    public void  BiomeSetter(){

        // duke forest
        setDukeForest();

        // mirana Desert
        setMiranaDesert();

        // tesseract
        setTesseract();

        setProceduralGeneration();


    }

    /*
    ==================== BIOME SETTERS ====================
     */


    /**
     *         <h1>Duke Forest : The Tutorial Biome</h1> <br>
     *
     *         <h3>Lore : </h3><br>
     *             the main Character arrives after the sudden Mana Storm, <br>
     *             the mc wakes up in this forest, <br>
     *             finds the Traveller and learns about the new world <br> <br>
     *
     *         <h3>Missions: </h3><br>
     *             1: Helps the Traveller by helping him/her with branches <br>
     */
    protected void setDukeForest(){

        String biomePath;
        String biomeOverlayPath;

        Biome dukeForest = new Biome();
        dukeForest.name = "Duke Forest IV";
        dukeForest.BiomeHeight = 50;
        dukeForest.BiomeWidth = 50;
        dukeForest.isOverlayPresent = true;

        // map
        biomePath = "/Map/trail.txt";
        dukeForest.map = loadMap_text(dukeForest,biomePath);
        biomeOverlayPath = "/Map/trail_overlay.txt";
        dukeForest.overlay = loadMap_text(dukeForest,biomeOverlayPath);

        // npcs
        dukeForest.npc = new Entity[10];// increase the number of npcs
        dukeForest.object = new SuperObject[10]; // increase the number of objects

        biomehashMap.put("B001",dukeForest);
    }


    protected void setTesseract(){
        Biome tesseract = new Biome();
        tesseract.BiomeWidth = 100;
        tesseract.BiomeHeight = 100;
        tesseract.name = "Tesseract";
        tesseract.isOverlayPresent = true;

        tesseract.map = loadMap_CSV(tesseract, "/Map/tesseract/prototype/tesseract_baseLayer.csv");
        System.out.println(tesseract.map.length);
        tesseract.overlay = loadMap_CSV(tesseract,"/Map/tesseract/prototype/tesseract_Overlay.csv");
        System.out.println(tesseract.overlay.length);

        Noise noise = new Noise(tesseract.BiomeWidth,tesseract.BiomeHeight,78648753);
        Random random = new Random();
        int grassTileSize = gp.tileManager.grassTiles.length;
        int flowersTileSize = gp.tileManager.flowerTiles.length;
        int musroomTileSize = gp.tileManager.mushroomTiles.length;

        float factor = 0.1f;
        for(int i = 0; i < tesseract.BiomeHeight; i++){
            for(int j = 0; j< tesseract.BiomeWidth ;j++){
                float val = noise.getNoise(i,j,factor);
                if (tesseract.overlay[i][j] != -1 ||  tesseract.map[i][j] != 52) continue;

                if (val >= 0.9 ) continue;
                else if (val >= 0.8  ) tesseract.overlay[i][j] = gp.tileManager.flowerTiles[random.nextInt(flowersTileSize)];
                else if (val >= 0.7  ) continue;
                else if (val >= 0.6  ) tesseract.overlay[i][j] = gp.tileManager.flowerTiles[random.nextInt(flowersTileSize)];
                else if (val >= 0.4  ) continue;
                else if (val >= 0.3  ) tesseract.overlay[i][j] = gp.tileManager.flowerTiles[random.nextInt(flowersTileSize)];
                else if (val >= 0.2  ) tesseract.overlay[i][j] = gp.tileManager.grassTiles[random.nextInt(grassTileSize)];
                else if (val >= 0.1  ) tesseract.overlay[i][j] =  gp.tileManager.flowerTiles[random.nextInt(flowersTileSize)];
                else if (val >= 0.0  ) tesseract.overlay[i][j] = 325;


            }
        }



        biomehashMap.put("B003",tesseract);


    }


    protected void setProceduralGeneration(){

        preMap Premap = new preMap();
        
        biomehashMap.put("B004",Premap);
    } 


    protected void setMiranaDesert(){

        /*
        The Great Desert of Mirana: The Village

        lore:
            Not yet figured it out
         */

        String biomePath;
        String biomeOverlayPath;

        Biome mirana = new Biome();
        mirana.name = "Great Desert of Mirana";
        mirana.BiomeHeight = 50;
        mirana.BiomeWidth = 50;

        mirana.isOverlayPresent = false;
        biomePath = "/Map/mirana-desert/mirana-basemap.csv";
        mirana.map = loadMap_CSV(mirana,biomePath);

        mirana.npc = new Entity[10];
        biomehashMap.put("B002",mirana);


}



























    /*
    ================== UTILS ====================
     */

    public int[][] loadMap_CSV(Biome b,String filelocation){
        int[][] map = new int[b.BiomeHeight][b.BiomeWidth];
        BufferedReader buffer;

        String path = filelocation.startsWith("/") ? filelocation : "/" + filelocation;
        try (InputStream stream = getClass().getResourceAsStream(path)){
            if(stream == null){
                throw new IllegalArgumentException("File not found at path: " + path);
            }
            buffer = new BufferedReader(new InputStreamReader(stream));

            for (int row = 0; row < b.BiomeHeight; row++) {

                String line = buffer.readLine();
                if (line == null) break;

                String[] token = line.split(",");

                for(int col = 0; col < Math.min(token.length,b.BiomeWidth); col++){
                    map[row][col] = Integer.parseInt(token[col].trim());
                }
            }
        } catch (Exception e){
            System.out.println(filelocation);
            e.printStackTrace();
        }
        return map;
    }


    public int[][] loadMap_text(Biome b,String filelocation){
        String path_map = filelocation.startsWith("/") ? filelocation : "/" + filelocation;
        int[][] local_map = new int[b.BiomeHeight][b.BiomeWidth];
        try{
        InputStream stream = getClass().getResourceAsStream(path_map);

        if (stream == null) System.out.println("");

        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        for(int row = 0 ; row < b.BiomeHeight; row++){

            String line = br.readLine();
            String[] token = line.split(" ");

            for(int col = 0; col < Math.min(token.length,b.BiomeWidth); col++){
                local_map[row][col] = Integer.parseInt(token[col].trim());
            }
        }

        }catch (Exception e){
            e.printStackTrace();
        }
        return local_map;
    }

}
