package Biome;

import Entity.Entity;
import Main.GamePanel;
import Objects.SuperObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BiomeManager {
    private HashMap<String,Biome> biomehashMap = new HashMap<>();
    GamePanel gp;

    public BiomeManager(GamePanel gp){
        this.gp = gp;
    }


    public Biome getBiome(String key) {
        return biomehashMap.get(key);
    }

    public void  BiomeSetter(){

        // duke forest
        setDukeForest();

        // mirana Desert
        setMiranaDesert();

    }

    /*
    ==================== BIOME SETTERS ====================
     */

    protected void setDukeForest(){
        /*
        Duke Forest : The Tutorial Biome,

        Lore :
            the main Character arrives after the sudden Mana Storm,
            the mc wakes up in this forest,
            finds the Traveller and learns about the new world

        Missions:
            1: Helps the Traveller by helping him/her with branches
         */
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
