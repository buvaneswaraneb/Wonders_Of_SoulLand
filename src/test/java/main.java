import Biome.BiomeManager;
import Main.GamePanel;

import java.util.HashSet;


public class main {

    public static void main(String[] args){

        // test Biome Reader
        GamePanel gp = new GamePanel();
        BiomeManager bm = new BiomeManager(gp);
        bm.BiomeSetter();

        var test_biome = bm.getBiome("B001");

        int[][] test = bm.loadMap_CSV(test_biome, "Map/mirana-desert/mirana-basemap.csv");
        HashSet<Integer> set = new HashSet<>();

        for (int[] a : test){
            for(int b : a){
                set.add(b);
//                System.out.print(b + ", ");
            }
//            System.out.println();
        }
        System.out.println(set);







    }
}
