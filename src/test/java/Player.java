import Main.GamePanel;

import java.awt.*;

public class Player {
    TestPannel tp;
    GamePanel gp;
    int i = 0;

    public Player(TestPannel tp) {
        this.tp = tp;
    }

    public void update() {

        if (tp.keyHandler.upPressed) {
            tp.keyHandler.upPressed = false;
            int nh = tp.cursor[1] - 1;
            if (nh >= 0) tp.cursor[1] = nh;
        }
        if (tp.keyHandler.downPressed) {
            tp.keyHandler.downPressed = false;
            int nh = tp.cursor[1] + 1;
            if (nh < tp.max_pixel_row) tp.cursor[1] = nh;
        }
        if (tp.keyHandler.leftPressed) {
            tp.keyHandler.leftPressed = false;
            int nw = tp.cursor[0] - 1;
            if (nw >= 0) tp.cursor[0] = nw;
        }
        if (tp.keyHandler.rightPressed) {
            tp.keyHandler.rightPressed = false;
            int nw = tp.cursor[0] + 1;
            if (nw < tp.max_pixel_col) tp.cursor[0] = nw;
        }

        if(tp.keyHandler.srcPressed){
            tp.keyHandler.srcPressed = false;

            if (tp.srcNode != null) tp.srcNode.isSrc = false;
            tp.src[0] = tp.cursor[0];
            tp.src[1] = tp.cursor[1];

            tp.srcNode = tp.tiles[tp.cursor[1]][tp.cursor[0]];
            tp.srcNode.isSrc = true;

            tp.tileManager.tileColor = Color.pink;

            tp.pathfinder.total.clear();
            tp.tileManager.TotalLocalQueue.clear();

            tp.arr = tp.pathfinder.Astar(tp.srcNode,tp.destNode,tp);
            tp.tileManager.totalindex = 0;
            tp.tileManager.index = 0;
            tp.tileManager.localQueue.clear();
        }

        if(tp.keyHandler.enterPressed){
            tp.keyHandler.enterPressed = false;

            tp.tileManager.tileColor = Color.LIGHT_GRAY;
            tp.pathfinder.total.clear();
            tp.tileManager.TotalLocalQueue.clear();
            tp.arr = tp.pathfinder.Astar(tp.srcNode,tp.destNode,tp);
            tp.tileManager.totalindex = 0;
            tp.tileManager.index = 0;
            tp.tileManager.localQueue.clear();
        }

        if(tp.keyHandler.destPressed){
            tp.keyHandler.destPressed = false;

            if (tp.destNode != null) tp.destNode.isDest = false;
            tp.dest[0] = tp.cursor[0];
            tp.dest[1] = tp.cursor[1];

            tp.destNode = tp.tiles[tp.cursor[1]][tp.cursor[0]];
            tp.destNode.isDest = true;

            tp.tileManager.tileColor = Color.magenta;

            tp.pathfinder.total.clear();
            tp.tileManager.TotalLocalQueue.clear();
            tp.arr = tp.pathfinder.Astar(tp.srcNode,tp.destNode,tp);
            tp.tileManager.totalindex = 0;
            tp.tileManager.index = 0;
            tp.tileManager.localQueue.clear();
        }
    }
}
