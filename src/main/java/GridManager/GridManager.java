package GridManager;

import Biome.Biome;
import Entity.Entity;
import Entity.Player;
import Main.GamePanel;

import java.awt.*;

public class GridManager {
    GamePanel gp;

    public GridManager(GamePanel gp){
        this.gp = gp;
    }


    public void GridSetter(){
        Biome b = gp.currentBiome;
        // initialize the nodes
        b.grids = new GridNode[b.BiomeHeight][b.BiomeWidth];
        for(int y = 0 ; y < b.BiomeHeight ; y++){
            for(int x = 0; x < b.BiomeWidth ; x++){


                // x and y done
                b.grids[y][x] = new GridNode(x,y);

                // map.tile.overlay Tile Done
                b.grids[y][x].mapTile = gp.tileManager.getTilesbyMap(b.map,y,x);

                if (b.isOverlayPresent) b.grids[y][x].overlayTile = gp.tileManager.getTilesbyMap(b.overlay,y,x);

                // set collisions
                if (b.grids[y][x].mapTile.collision){
                    b.grids[y][x].walkable = false;
                }

                else if (b.isOverlayPresent &&  b.grids[y][x].overlayTile.collision){
                    b.grids[y][x].walkable = false;
                }

            }
        }
    }


    public void update(){
        managePlayer();
        manageEntities();

    }

    public void draw(Graphics2D g2d){

        g2d.setColor(Color.MAGENTA);

        int screenX = gp.player.ScreenX;
        int screenY = gp.player.ScreenY;
        g2d.fillRect(screenX , screenY ,gp.tile_size, gp.tile_size);

        if(gp.currentBiome == null) return;
        if(gp.currentBiome.npc == null) return;

    }

    private void managePlayer(){

        // calculate Relevant
        int playerX = gp.player.World_x/gp.currentBiome.BiomeWidth;
        int playerY = gp.player.World_y/gp.currentBiome.BiomeHeight;

        if (gp.player.grid == null){
            gp.player.grid = new GridNode(playerX,playerY);
            return;
        }
        if (gp.player.grid.x == playerX && gp.player.grid.y == playerY){
            return;
        }


        gp.player.grid.x = playerX;
        gp.player.grid.y = playerY;
    }

    void manageEntities(){
        if (gp.currentBiome == null) return;
        if (gp.currentBiome.npc == null) return;

        for(Entity e : gp.currentBiome.npc) {

            if (e == null) continue;

            int EntityX = e.World_x/gp.currentBiome.BiomeWidth;
            int EntityY = e.World_y/gp.currentBiome.BiomeWidth;

            if (e.grid == null) {
               e.grid = new GridNode(EntityX, EntityY);
                return;
            }
            e.grid.x = EntityX;
            e.grid.y = EntityY;
        }
    }




}
