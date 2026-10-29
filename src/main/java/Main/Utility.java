package Main;

import Entity.Attacks.AttackTemplate;
import GridManager.GridNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Utility {

    public BufferedImage scale(BufferedImage original, int width , int height){
        // used to scale images efficiently
        BufferedImage scaledImage = new BufferedImage(height,width,original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original,0,0,width,height,null);
        g2d.dispose();
        return scaledImage;
    }

    /**
     * Gets the Sprites fetches and sequentially scale the image.
     * only works with pngs and multiple sprites
     * <p> format
     * fileName_< index >.png
     * </p>
     * useage:
     *  get the path of the sprites from the src
     *   eg :  Monster/BlueSlime/down/blue_slime_down_
     *   leave the integers 1,2,3,4,5
     *   and path extension such as .png
     *
     */
    public void getSprites(int sprite_length, String path, ArrayList<BufferedImage> arr, int width , int height ) throws IOException {
        BufferedImage image;
        for(int i = 1 ; i <= sprite_length ; i++){
            //System.out.println(path + i + ".png");
            var stream = getClass().getResourceAsStream(path + i + ".png");
            if (stream == null) System.out.println("Character Image is not Loading");
            image = ImageIO.read(stream);
            image = scale(image,height,width);
            arr.add(image);
        }
    }

    public String bfs(int[] src , int[] des, int[][] adj,GamePanel gp){

        int[][] directions = {
                {0,1}, // right
                {1,0}, // down
                {-1,0}, // up
                {0,-1} // left
        };

        int row_len = adj.length;
        int col_len = adj[0].length;

        Queue<String[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[row_len][col_len];

        q.offer(new String[]{""+src[0], ""+src[1], " "});

        visited[src[0]][src[1]] = true;

        while(!q.isEmpty()){

            String[] curr = q.poll();

            int row = Integer.parseInt(curr[0]);
            int col = Integer.parseInt(curr[1]);
            String path = curr[2];

            if (row == des[0] && col == des[1]){
                return path;
            }

            for(int i = 0; i < directions.length ; i++){

                int[] d = directions[i];
                int nr = row + d[0];
                int nc = col + d[1];

                if ((nr > 0 && nr < adj.length) && (nc > 0 && nc < adj[0].length) && !visited[nr][nc] && !isCollisonTile(nr,nc,gp)){
                    visited[nr][nc] = true;
                    if (i == 0) q.offer(new String[]{""+nc, ""+nr, path + "right " });
                    else if (i == 1) q.offer(new String[]{""+nc, ""+nr, path + "down " });
                    else if (i == 2) q.offer(new String[]{""+nc, ""+nr, path + "up " });
                    else if (i == 3) q.offer(new String[]{""+nc, ""+nr, path + "left " });
                }

            }
        }
        return "NO VALID PATH FOUND";
    }

    public boolean isCollisonTile(int r , int c,GamePanel gp){
       int tileID = gp.currentBiome.map[c][r];
       return gp.tileManager.tiles.get(tileID).collision;
    }


    public String findDirections(GridNode curr, int[] target){

        int x = target[0] - curr.x;
        int y = target[1] - curr.y;


        if (x == 0 && y == 0) return "onDestination";

        if (x == 0) return y < 0 ? "up" : "down";
        if (y == 0) return x > 0 ? "right" : "left";

        if (x > 0 && y < 0) return "northEast";
        if (x < 0 && y < 0) return "northWest";
        if (x > 0 ) return "southEast";

        return  "southWest";
    }


    public String findDirections(
            int curr_worldX, int curr_worldY,
            int target_worldX, int target_worldY) {

        int x = target_worldX - curr_worldX;
        int y = target_worldY - curr_worldY;

        if (x == 0 && y == 0) return "onDestination";

        if (x == 0) return y < 0 ? "up" : "down";
        if (y == 0) return x > 0 ? "right" : "left";

        if (x > 0 && y < 0) return "northEast";
        if (x < 0 && y < 0) return "northWest";
        if (x > 0 ) return "southEast";

        return  "southWest";
    }

    public BufferedImage mirrorVertical(BufferedImage inputImage) {
        // Keep x-axis at 1 and scale y-axis by -1 (flip)
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        // Shift the image back down into view
        tx.translate(-inputImage.getWidth(),0);
        // Apply transformation operation
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(inputImage, null);
    }


    /**
     * To Check if the object distance inside the camera
     * or screen window, it accepts objects and returns boolean
     */
    public boolean onScreen(AttackTemplate atk, GamePanel gp){

        int x = atk.x ;
        int y = atk.y ;

        if (x + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                x - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                y + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                y - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            return true;

        }
        return false;
    }

}

