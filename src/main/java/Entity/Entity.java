package Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import GridManager.GridNode;
import Main.GamePanel;

public abstract class Entity {

    public String name ;
    public String direction;

    public int World_x,World_y;
    public int defaultWorld_x,defaultWorld_y;
    public int lastX , lastY;

    public int speed;
    int sprite_delay = 0;
    int sprite_number = 1;
    int intervalCount = 0;

    GamePanel gp;

    public int offset_x = 0;
    public int offset_y = 0;

    public GridNode grid;
    public List<int[]> pathList = new LinkedList<>();


    public boolean debug = true;

    //animations
    public ArrayList<BufferedImage> up_walking = new ArrayList<>();
    public ArrayList<BufferedImage> down_walking = new ArrayList<>();
    public ArrayList<BufferedImage> right_walking = new ArrayList<>();
    public ArrayList<BufferedImage> left_walking = new ArrayList<>();

    // collision
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public boolean collisionOn = false;
    public int solidDefaultAreaX , solidDefaultAreaY;

    // Entity View Distance
    public Rectangle FOV = new Rectangle();
    public boolean onRange = false;
    public int solidDefaultFOVAreaX,solidDefaultFOVAreaY;



    // abstract methods
    public abstract String dialogue();
    public abstract void update();

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        int ScreenX = World_x - gp.player.World_x + gp.player.ScreenX;
        int ScreenY = World_y - gp.player.World_y + gp.player.ScreenY;

        if (World_x + gp.tile_size > gp.player.World_x - gp.player.ScreenX &&
                World_x - gp.tile_size < gp.player.World_x + gp.player.ScreenX &&
                World_y + gp.tile_size > gp.player.World_y - gp.player.ScreenY &&
                World_y - gp.tile_size < gp.player.World_y + gp.player.ScreenY) {

            switch (direction) {
                case "up" -> {
                    image = up_walking.get(sprite_number);
                    break;
                }
                case "down" -> {
                    image = down_walking.get(sprite_number);
                    break;
                }
                case "left" -> {
                    image = left_walking.get(sprite_number);
                    break;
                }
                case "right" ->{
                    image = right_walking.get(sprite_number);
                    break;
                }
            }

            g2d.drawImage(image, ScreenX, ScreenY, null);

            if (gp.debug){
                g2d.setColor(Color.red);
                g2d.drawRect(ScreenX + offset_x,ScreenY + offset_y ,solidArea.width,solidArea.height);
            }

        }
    }


    public void find(Entity entity){
        if (entity == null){
            if(gp.debug) System.out.println("DEBUG : The Target Entity");
            return;
        }

        if (entity.grid == null){
            if(gp.debug) System.out.println("DEBUG : The Target Grid Object Not null");
            return;
        }

        pathList.clear();

        List<int[]> path = gp.pathfinder.Astar(grid,entity.grid);

        if (path == null || path.isEmpty()) {
            if(gp.debug) System.out.println("DEBUG : No Valid Path was Found");
            return;
        }

        if(gp.debug) System.out.println("DEBUG Valid Path was Found");

        pathList.addAll(path);

        path.clear();

        }

        public void find(GridNode targetGrid){
            if (targetGrid == null){
                if (gp.debug) System.out.println("DEBUG: The Target Grid is null of Not initialized");
                return;
            }
            if(gp.currentBiome == null || gp.currentBiome.grids == null){
                if(gp.debug) System.out.println("DEBUG : gp.currentbiome or gp.currentbiome.grids is null");
                return;
            }

            GridNode origin = gp.currentBiome.grids[defaultWorld_x][defaultWorld_y];

            pathList.clear();

            List<int[]> path = gp.pathfinder.Astar(grid,origin);

            path.removeFirst();

            if (path.isEmpty()) {
                if(gp.debug) System.out.println("DEBUG : No Valid Path was Found");
                return;
            }

            if(gp.debug) System.out.println("DEBUG Valid Path was Found");

            pathList.addAll(path);

            path.clear();

        }

        // travel scheduler
        public void travelScheduler(){

        if (pathList == null || pathList.isEmpty()) {return;}

        int[] path = pathList.getFirst();

        if (path[0]*gp.tile_size == World_x && path[1]*gp.tile_size == World_y) pathList.removeFirst();

        if (pathList.isEmpty()) {return;}

        path = pathList.getFirst();

        direction = gp.util.findDirections(World_x,World_y,path[0]*gp.tile_size,path[1]*gp.tile_size);

        intervalCount++;

        if (intervalCount >= 30){
            World_x = pathList.getFirst()[0]*gp.tile_size;
            World_y = pathList.getFirst()[1]*gp.tile_size;

            intervalCount = 0;
        }



        switch (direction){
            case "up" ->{
                World_y += speed;
            }

            case "down" ->{
                World_y -= speed;
            }

            case "left" ->{
                World_x -= speed;
            }
            case "right" ->{
                World_x += speed;
            }

            case "northEast" -> {
                World_x += speed;
                World_y += speed;
            }
            case "northWest" ->{
                World_x -= speed;
                World_y += speed;
            }
            case "southEast" ->{
                World_x += speed;
                World_y -= speed;
            }
            case "southWest" ->{
                World_x -= speed;
                World_y -= speed;
            }
        }

    }


}
