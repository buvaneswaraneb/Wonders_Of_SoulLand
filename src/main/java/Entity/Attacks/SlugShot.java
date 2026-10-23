package Entity.Attacks;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class SlugShot extends AttackTemplate{

    Entity entity;
    GamePanel gp;
    AttackCollison atkcollison;

    public boolean attackOn;

    int attackCoolDown = 15;
    int attackCounter = 0;

    public SlugShot(Entity entity, GamePanel gp){
        this.entity = entity;
        this.gp = gp;
        this.atkcollison = new AttackCollison(gp,this);
        speed = 10;
        damage = 10;
    }

    @Override
    public void attack(){
        attackCoolDown++;
        createNewAttack();
        attackOn = true;
    }

    @Override
    public void update() {
        attackCounter++;
        atkcollison.update();

        onContact = atkcollison.checkAttackCollision(gp.player);

        if (onContact){
            gp.player.health -= damage;
        }

        if (!attackOn) return;
        if (attackCounter < attackCoolDown) return;


        switch (direction){
            case "up" ->{
                y -= speed;
            }
            case "down" ->{
                y += speed;
            }
            case "left" ->{
                x -= speed;
            }
            case "right" ->{
                x += speed;
            }

            case "northEast"->{
                x += speed;
                y -= speed;
            }

            case "northWest" ->{
                x -= speed;
                y -= speed;
            }

            case "southEast" ->{
                x += speed;
                y += speed;
            }

            case "southWest" ->{
                x -= speed;
                y += speed;
            }

        }

        attackCounter = 0;
    }

    @Override
    public void drawAttack(Graphics2D g2d) {

        int screenX = x - gp.player.World_x + gp.player.ScreenX;
        int screenY = y - gp.player.World_y + gp.player.ScreenY;

        if (!gp.util.onScreen(this,gp)) return;

        g2d.setColor(Color.cyan);
        g2d.fillOval(screenX,screenY,25,25);

        if(gp.debug){
            g2d.setColor(Color.red);
            g2d.drawRect(screenX+solidArea.x , screenY+solidArea.y , solidArea.width, solidArea.height);
        }
    }

    // ================== Helpers ====================


    /***
     * Creates a new Attack
     */

    void createNewAttack(){
        x = entity.World_x;
        y = entity.World_y;
        direction = entity.direction;
        attackOn = true;

    }
}
