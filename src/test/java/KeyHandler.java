import Entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean enterPressed;
    public boolean srcPressed;
    public boolean destPressed;

    // counters

    public int upCounter;
    public int downCounter;
    public int leftCounter;
    public int rightCounter;
    public int srcCounter;
    public int destCounter;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){ upPressed = true; };
        if (code == KeyEvent.VK_S){ downPressed = true; };
        if (code == KeyEvent.VK_A){ leftPressed = true; };
        if (code == KeyEvent.VK_D){ rightPressed = true; };
        if (code == KeyEvent.VK_O){ srcPressed = true; };
        if (code == KeyEvent.VK_P){ destPressed = true; };
        if (code == KeyEvent.VK_ENTER){ enterPressed = true; };

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){ upPressed = false; upCounter = 0;}
        else if (code == KeyEvent.VK_S){ downPressed = false; downCounter = 0; }
        else if (code == KeyEvent.VK_A){ leftPressed = false; leftCounter = 0; }
        else if (code == KeyEvent.VK_D){ rightPressed = false; rightCounter = 0; }
        else if (code == KeyEvent.VK_O){ srcPressed = false;srcCounter = 0; }
        else if (code == KeyEvent.VK_P){ destPressed = false; destCounter = 0; }
        else if (code == KeyEvent.VK_ENTER){ enterPressed = false; destCounter = 0; }


    }
}
