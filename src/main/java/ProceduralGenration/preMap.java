package ProceduralGenration;

import java.util.ArrayList;
import java.util.Random;

import Biome.Biome;
import Main.Noise;

enum tile {

    GRASS(0),
    WATER(1),
    GRASS_VARIENT(2),
    SPECIAL_BLOCK(-1),
    FLOWER_BLOCK(5),
    FLOWER_BLOCK_1(357),
    PINE_TREE(3);

    public final int code;

    private tile(int code){
        this.code = code;
    }
}

public class preMap extends Biome {
    long seed;
    Noise noise ;
    Random random = new Random();
    ArrayList<int[]> block_placeable = new ArrayList<>();

    public preMap(){
        name = "RANDOM GENERATION TEST";
        BiomeHeight = 600;
        BiomeWidth = 600;
        map = new int[BiomeHeight][BiomeWidth];
        overlay = new int[BiomeHeight][BiomeHeight];
        seed = 7856462;
        noise = new Noise(BiomeWidth,BiomeHeight,seed);
        isOverlayPresent = true;
        this.generateMap();
        this.plantTrees();
    }

    private void generateMap(){
        for (int x = 0; x < BiomeHeight; x++) {
            for (int y = 0; y < BiomeWidth; y++) {

                float val = noise.getNoise(x, y, 0.1f);
                float round = Math.round(val * 10.0f) / 10.0f;
                map[x][y] = assignTile(round).code;

                if (map[x][y] == tile.GRASS.code){
                    block_placeable.add(new int[]{x,y});
                }
            }
        }
    }

    private void plantTrees(){
        int limit = (int) Math.ceil(block_placeable.size() * 0.2);
        for (int i  = 0; i < limit ; i++){

            int randIndex = random.nextInt(block_placeable.size());
            int x = block_placeable.get(randIndex)[0];
            int y = block_placeable.get(randIndex)[1];

            if (overlay[x][y] != 0) continue;
            overlay[x][y] = tile.PINE_TREE.code;

        }

    }

    private tile assignTile(float val){

        if (val >= 0.0 && val <= 0.3) return tile.FLOWER_BLOCK_1;
        else if (val < 0.5) return tile.GRASS;
        else if(val < 0.6) return tile.GRASS;
        else if(val <= 0.7) return tile.WATER;
        else if(val <= 1.0) return tile.FLOWER_BLOCK;
        return tile.SPECIAL_BLOCK;
    }


}
