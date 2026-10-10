package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enterPressed;
    public boolean escPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.gameState == gp.titleState || gp.gameState == gp.dialogueState){

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.currentMenu--;
                upPressed = true;
                if(gp.ui.currentMenu < 0){
                    gp.ui.currentMenu = gp.ui.menuTotal-1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
               gp.ui.currentMenu++;
               downPressed = true;
                if (gp.ui.currentMenu >= gp.ui.menuTotal){
                    gp.ui.currentMenu = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

        }


        if (gp.gameState == gp.playState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }
        if(code == KeyEvent.VK_P) {
            if (gp.gameState == gp.pauseState) gp.gameState = gp.playState;
            else gp.gameState = gp.pauseState;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }
}
