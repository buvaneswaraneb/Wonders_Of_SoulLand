import Biome.Biome;
import Biome.BiomeManager;

import java.util.HashSet;

public class testFunction {

    HashSet<Integer> set = new HashSet<>();

    public void test(){
        BiomeManager bm = new BiomeManager();

        Biome tb = new Biome();
        tb.BiomeHeight = 100;
        tb.BiomeWidth = 100;
        int[][] csv = bm.loadMap_CSV(tb,"Map/tesseract/prototype/tesseract_baseLayer.csv");

        for(int[] c: csv){
            for(int i : c){
                set.add(i);

                System.out.printf("%d , ", i);
            }

            System.out.println();
        }


        System.out.println("==================================================================");

        System.out.println(set);

        System.out.println(set.size());

    }
}
