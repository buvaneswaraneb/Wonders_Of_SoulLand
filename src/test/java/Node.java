
import java.awt.*;

public class Node {
    int g;
    int h;
    int f;
    boolean collison;
    int x ;
    int y;
    Color color;
    Node parent;

    boolean isSrc;
    Boolean isDest;

    Node(int x , int y){
        this.x = x;
        this.y = y;
        this.color = Color.WHITE;
    }
}
