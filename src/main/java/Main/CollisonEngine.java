package Main;

import Entity.Entity;

import java.awt.*;

public class CollisonEngine {
    GamePanel gp;
    public CollisonEngine(GamePanel gp){
        this.gp = gp;
    }

    private boolean isSolid(int row , int col){
        // map
        int side1;
        try{
            side1 =  gp.currentBiome.map[row][col];
        } catch (Exception e) {
            System.out.printf("%d row , %d col \n",row,col);
            throw new RuntimeException(e);
        }
        boolean sitemap = gp.tileManager.tiles[side1].collision;
        // map 2
        if (!gp.currentBiome.isOverlayPresent) return  sitemap;
        int side2 = gp.currentBiome.overlay[row][col];
        boolean isOverlay = gp.tileManager.tiles[side2].collision;

        return sitemap || isOverlay;
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

        switch (entity.direction){
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

        for(int i = 0; gp.currentBiome.object != null && i < gp.currentBiome.object.length; i++){
            if (gp.currentBiome.object[i] == null) continue;

            // bring the entity solid area to the player
            entity.solidArea.x = entity.World_x + entity.solidArea.x;
            entity.solidArea.y = entity.World_y + entity.solidArea.y;

            // bring the objects solid area according the position of the object in the world map
            gp.currentBiome.object[i].solidArea.x =  gp.currentBiome.object[i].worldX +  gp.currentBiome.object[i].solidArea.x;
            gp.currentBiome.object[i].solidArea.y =  gp.currentBiome.object[i].worldY +  gp.currentBiome.object[i].solidArea.y;

            switch (entity.direction){
                case "up":{
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects( gp.currentBiome.object[i].solidArea)){
                        if ( gp.currentBiome.object[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.direction);
                    }
                    break;
                }
                case "down":{
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects( gp.currentBiome.object[i].solidArea)){
                        if ( gp.currentBiome.object[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.direction);
                    }
                    break;
                }
                case "left":{
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.currentBiome.object[i].solidArea)){
                        if ( gp.currentBiome.object[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.direction);
                    }
                    break;
                }
                case "right":{
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects( gp.currentBiome.object[i].solidArea)){
                        if ( gp.currentBiome.object[i].collisionOn) entity.collisionOn = true;
                        if (player) index = i;
                        System.out.println("collison on " + entity.direction);
                    }
                    break;
                }
            }
            // reset the solid area of the both player and the entity
            entity.solidArea.x = entity.solidDefaultAreaX;
            entity.solidArea.y = entity.solidDefaultAreaY;

            gp.currentBiome.object[i].solidArea.x =  gp.currentBiome.object[i].soilDefaultAreaX;
            gp.currentBiome.object[i].solidArea.y =  gp.currentBiome.object[i].getSoilDefaultAreaY;

        }

        return  index;
    }
    
    
    public int checkEntity(Entity entity, Entity[] targets){

        int index = Integer.MAX_VALUE;

        for(int i = 0; i < targets.length; i++){
            if (targets[i] == null) continue;

            // bring the entity solid area to the player
            entity.solidArea.x = entity.World_x + entity.solidArea.x;
            entity.solidArea.y = entity.World_y + entity.solidArea.y;

            // bring the objects solid area according the position of the object in the world map
            targets[i].solidArea.x = targets[i].World_x + targets[i].solidArea.x + targets[i].offset_x;
            targets[i].solidArea.y = targets[i].World_y + targets[i].solidArea.y + targets[i].offset_y;

            switch (entity.direction){
                case "up":{
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(targets[i].solidArea)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                }
                case "down":{
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(targets[i].solidArea)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                }
                case "left":{
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(targets[i].solidArea)){
                     entity.collisionOn = true;
                     index = i;
                    }
                    break;
                }
                case "right":{
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(targets[i].solidArea)){
                        entity.collisionOn = true;
                        index = i;
                    }
                    break;
                }
            }

            // reset the solid area of the both player and the entity
            entity.solidArea.x = entity.solidDefaultAreaX;
            entity.solidArea.y = entity.solidDefaultAreaY;

            targets[i].solidArea.x = targets[i].solidDefaultAreaX;
            targets[i].solidArea.y = targets[i].solidDefaultAreaY;
        }

        return  index;
    }
    
    public void checkPlayer(Entity entity){
            // bring the entity solid area to the player
            entity.solidArea.x = entity.World_x + entity.solidArea.x;
            entity.solidArea.y = entity.World_y + entity.solidArea.y;

            // bring the objects solid area according the position of the object in the world map
            gp.player.solidArea.x = gp.player.World_x + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.World_y + gp.player.solidArea.y;


            switch (entity.direction){
                case "up":{
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)){
                        entity.collisionOn = true;
                    }
                    break;
                }
                case "down":{
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)){
                        entity.collisionOn = true;
                    }
                    break;
                }
                case "left":{
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)){
                        entity.collisionOn = true;
                    }
                    break;
                }
                case "right":{
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)){
                        entity.collisionOn = true;
                    }
                    break;
                }
            }
            // reset the solid area of the both player and the entity
            entity.solidArea.x = entity.solidDefaultAreaX;
            entity.solidArea.y = entity.solidDefaultAreaY;

            gp.player.solidArea.x = gp.player.solidDefaultAreaX;
            gp.player.solidArea.y = gp.player.solidDefaultAreaY;
        }



        public int checkRange(Entity entity , Entity[] targets){

            if(entity == null || targets == null) return -1;

            int index = -1;

            for(int i = 0; i < targets.length; i++){
                if (targets[i] == null) continue;

                // bring the entity solid area to the player
                entity.FOV.x = entity.World_x + entity.FOV.x;
                entity.FOV.y = entity.World_y + entity.FOV.y;

                // bring the objects solid area according the position of the object in the world map
                targets[i].solidArea.x = targets[i].World_x + targets[i].solidArea.x + targets[i].offset_x;
                targets[i].solidArea.y = targets[i].World_y + targets[i].solidArea.y + targets[i].offset_y;

                switch (targets[i].direction){

                    case "up":{
                        targets[i].solidArea.y -= targets[i].speed;
                        if (entity.FOV.intersects(targets[i].solidArea)){
                            entity.onRange = true;
                            index = i;
                        }
                        break;
                    }
                    case "down":{
                        targets[i].solidArea.y += targets[i].speed;
                        if (entity.FOV.intersects(targets[i].solidArea)){
                            entity.onRange = true;
                            index = i;
                        }
                        break;
                    }
                    case "left":{
                        targets[i].solidArea.x -= targets[i].speed;
                        if (entity.FOV.intersects(targets[i].solidArea)){
                            entity.onRange = true;
                            index = i;
                        }
                        break;
                    }
                    case "right":{
                        targets[i].solidArea.x += targets[i].speed;
                        if (entity.FOV.intersects(targets[i].solidArea)){
                            entity.onRange = true;
                            index = i;
                        }
                        break;
                    }
                }

                // reset the solid area of the both player and the entity
                entity.FOV.x = entity.solidDefaultFOVAreaX;
                entity.FOV.y = entity.solidDefaultFOVAreaY;

                targets[i].solidArea.x = targets[i].solidDefaultAreaX;
                targets[i].solidArea.y = targets[i].solidDefaultAreaY;
            }
            return  index;
        }
}
