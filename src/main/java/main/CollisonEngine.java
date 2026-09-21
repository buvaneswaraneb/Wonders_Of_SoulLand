package main;

import entity.Entity;

public class CollisonEngine {
    GamePanel gp;
    public CollisonEngine(GamePanel gp){
        this.gp = gp;
    }

    public void Check_tile(Entity entity){

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

        int side1, side2;

        switch (entity.directions){
            case "up":{
                entity_top_row = (top_side_world_y - entity.speed) / gp.tile_size;
                side1 = gp.tileManager.map[entity_top_row][entity_right_col];
                side2 = gp.tileManager.map[entity_top_row][entity_left_col];
                if(gp.tileManager.tiles[side1].collision || gp.tileManager.tiles[side2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "down":{
                entity_bottom_row = (bottom_side_world_y + entity.speed) / gp.tile_size;
                side1 = gp.tileManager.map[entity_bottom_row][entity_left_col];
                side2 = gp.tileManager.map[entity_bottom_row][entity_right_col];
                if(gp.tileManager.tiles[side1].collision || gp.tileManager.tiles[side2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "left":{
                entity_left_col = (left_side_world_x - entity.speed) / gp.tile_size;
                side1 = gp.tileManager.map[entity_top_row][entity_left_col];
                side2 = gp.tileManager.map[entity_bottom_row][entity_left_col];
                if(gp.tileManager.tiles[side1].collision || gp.tileManager.tiles[side2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "right":{
                entity_right_col = (right_side_world_x + entity.speed) / gp.tile_size;
                side1 = gp.tileManager.map[entity_top_row][entity_right_col];
                side2 = gp.tileManager.map[entity_bottom_row][entity_right_col];
                if(gp.tileManager.tiles[side1].collision || gp.tileManager.tiles[side2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
        }




    }


}
