package Entity.Attacks;

import Entity.Entity;
import Main.GamePanel;

public class AttackCollison {
    GamePanel gp;
    AttackTemplate atk;

    public AttackCollison(GamePanel gp, AttackTemplate at){
        this.gp = gp;
        this.atk = at;
    }


    public void update (){

    }

    public boolean checkAttackCollision(Entity entity){

        boolean atkHit = false;

        // bring the player solid Area to the world
        entity.solidArea.x = entity.World_x + entity.solidArea.x;
        entity.solidArea.y = entity.World_y + entity.solidArea.y;

        // bring the attack solid area to the solid area
        atk.solidArea.x = atk.x + atk.solidArea.x;
        atk.solidArea.y = atk.y + atk.solidArea.y;

        switch (atk.direction){
            case "up" ->{
                atk.solidArea.y -= atk.speed;
                if (entity.solidArea.intersects(atk.solidArea)) atkHit = true;
            }
            case "down" ->{
                atk.solidArea.y += atk.speed;
                if (entity.solidArea.intersects(atk.solidArea)) atkHit = true;
            }
            case "left" ->{
                atk.solidArea.x -= atk.speed;
                if (entity.solidArea.intersects(atk.solidArea)) atkHit = true;
            }
            case "right" ->{
                atk.solidArea.x += atk.speed;
                if (entity.solidArea.intersects(atk.solidArea)) atkHit = true;
            }
        }

        // set the entity solid x back to the default x , y
        entity.solidArea.x = entity.solidDefaultAreaX;
        entity.solidArea.y = entity.solidDefaultAreaY;

        // set the attack back to its place
        atk.solidArea.x = atk.solidDefaultAreaX;
        atk.solidArea.y = atk.solidDefaultAreaY;

        return atkHit;
    }
}
