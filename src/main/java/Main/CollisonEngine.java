package Main;

import Entity.Entity;

public class CollisonEngine {
    GamePanel gp;
    public CollisonEngine(GamePanel gp){
        this.gp = gp;
    }


    private boolean isSolid(int row , int col){
        // map
        int side1 =  gp.tileManager.map[row][col];
        boolean ismap = gp.tileManager.tiles[side1].collision;
        // map 2
        int side2 = gp.tileManager.overlay_map[row][col];
        boolean isOverlay = gp.tileManager.tiles[side2].collision;

        return ismap || isOverlay;
    }

    public void check_tile(Entity entity){

        // solid rectangle world coordinates
        int left_side_world_x = entity.World_x + entity.solidArea.x;
        int right_side_world_x  = entity.World_x + entity.solidArea.x + entity.solidArea.width - 1;
        int top_side_world_y  = entity.World_y + entity.solidArea.y;
        int bottom_side_world_y = entity.World_y + entity.solidArea.y + entity.solidArea.height - 1;

        // coordinates into rows and columns
        int entity_left_col = left_side_world_x / gp.tile_size;
        int entity_right_col  = right_side_world_x / gp.tile_size;
        int entity_top_row = top_side_world_y / gp.tile_size;
        int entity_bottom_row = bottom_side_world_y / gp.tile_size;

        switch (entity.directions){
            case "up":{
                entity_top_row = (top_side_world_y - entity.speed) / gp.tile_size;
                if(isSolid(entity_top_row,entity_right_col) || isSolid(entity_top_row,entity_left_col)){
                    entity.collisionOn = true;
                }
                break;
            }
            case "down":{
                entity_bottom_row = (bottom_side_world_y + entity.speed) / gp.tile_size;
                if(isSolid(entity_bottom_row,entity_left_col) || isSolid(entity_bottom_row,entity_right_col)){
                    entity.collisionOn = true;
                }
                break;
            }
            case "left":{
                entity_left_col = (left_side_world_x - entity.speed) / gp.tile_size;
                if(isSolid(entity_top_row,entity_left_col) || isSolid(entity_bottom_row,entity_left_col)){
                    entity.collisionOn = true;
                }
                break;
            }
            case "right":{
                entity_right_col = (right_side_world_x + entity.speed) / gp.tile_size;
                if(isSolid(entity_top_row,entity_right_col) || isSolid(entity_bottom_row,entity_right_col)){
                    entity.collisionOn = true;
                }
                break;
            }
        }
    }


    public int checkObject(Entity entity , Boolean player){

        int index = Integer.MAX_VALUE;

        for(int i = 0 ; i < gp.Obj.length; i++){
            if (gp.Obj[i] == null) continue;

            // bring the entity solid area to the player
            entity.solidArea.x = entity.World_x + entity.solidArea.x;
            entity.solidArea.y = entity.World_y + entity.solidArea.y;

            // bring the objects solid area according the position of the object in the world map
            gp.Obj[i].solidArea.x = gp.Obj[i].worldX + gp.Obj[i].solidArea.x;
            gp.Obj[i].solidArea.y = gp.Obj[i].worldY + gp.Obj[i].solidArea.y;


            switch (entity.directions){
                case "up":{
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                        if (gp.Obj[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.directions);
                    }
                    break;
                }
                case "down":{
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                        if (gp.Obj[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.directions);
                    }
                    break;
                }
                case "left":{
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                        if (gp.Obj[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.directions);
                    }
                    break;
                }
                case "right":{
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                        if (gp.Obj[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.directions);
                    }
                    break;
                }
            }

            // reset the solid area of the both player and the entity

            entity.solidArea.x = entity.solidDefaultAreaX;
            entity.solidArea.y = entity.solidDefaultAreaY;

            gp.Obj[i].solidArea.x = gp.Obj[i].soilDefaultAreaX;
            gp.Obj[i].solidArea.y = gp.Obj[i].getSoilDefaultAreaY;

        }

        return  index;
    }


}
