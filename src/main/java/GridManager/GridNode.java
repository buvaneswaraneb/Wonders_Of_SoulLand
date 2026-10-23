package GridManager;

import Tile.Tile;

public class GridNode {

    public int x ;
    public int y ;

    public Tile mapTile;
    public Tile overlayTile;
    public boolean walkable = true;

    public int f;
    public int g;
    public int h;

    public GridNode parent;

    public GridNode(int x , int y){
        this.x = x;
        this.y = y;
    }
}
