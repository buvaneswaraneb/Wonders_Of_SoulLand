
import Entity.Traveller;
import Main.Clock;
import Main.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TestPannel extends JPanel implements Runnable{

    final int FPS = 30;

    final int pixel = 16; // pixel size 16 x 16
    final int scale = 3; // scale
    public final int tile_size = pixel*scale; // 48 x 48

    public final int max_pixel_row = 12;   // height
    public final int max_pixel_col = 16;   // width

    public final int screen_width = max_pixel_col * tile_size; // 768
    public final int screen_height = max_pixel_row * tile_size; // 576

    public final int max_world_row = 50; // World height by tiles
    public final int max_world_col = 50; // World width by tiles

    Thread gameThread;
    List<int[]> arr ;

    TileManager tileManager = new TileManager(this);
    testPath pathfinder = new testPath();
    Clock clock = new Clock();

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this);

    Node[][] tiles = new Node[max_pixel_row][max_pixel_col];


    public int[] cursor = new int[]{0,0};
    public int[] src = new int[]{8,6}; // { width (col), height (row) }
    public Node srcNode;
    public int[] dest = new int[]{0,0};
    public Node destNode;

    public TestPannel(){
        this.setPreferredSize(new Dimension(screen_width,screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setup(){
        //add setup
        arr = new ArrayList<>();

        for (int i = 0; i < max_pixel_row ; i++){
            for(int j = 0; j < max_pixel_col ; j++){
                if (tiles[i][j] == null){
                    tiles[i][j] = new Node(j, i);

                    if (j == src[0] && i == src[1]){
                        tiles[i][j].isSrc = true;
                    }else if(j == dest[0] && i == dest[1]){
                        tiles[i][j].isDest = true;
                    }
                }
            }
        }

        srcNode = tiles[src[1]][src[0]];
        destNode = tiles[dest[1]][dest[0]];

        setCollison();

    }


    private void setCollison(){

        tiles[7][6].collison = true;
        tiles[6][7].collison = true;
        tiles[6][5].collison = true;
        tiles[5][6].collison = true;
        tiles[9][7].collison = true;
        tiles[7][9].collison = true;

    }

    public void startGameThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }


    @Override
    public void run() {
        while (gameThread != null){
            repaint();
            update();
            _clock_thread(FPS);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.fill(g2d);
        tileManager.totalDrawPath(g2d);
        tileManager.drawpath(g2d);
        tileManager.points(g2d);
        tileManager.drawCursor(g2d);

        g2d.dispose();
    }

    private void update(){
        tileManager.update();
        player.update();
    }


    public void _clock_thread(int FPS) {
        final double BILLION = 1_000_000_000;
        double drawInterval = BILLION / FPS;
        double nextInterval = System.nanoTime() + drawInterval;

        try {
            double remainingTime = nextInterval - System.nanoTime();
            remainingTime = remainingTime / 1_000_000; // Converts the Nano Seconds to Milliseconds

            if (remainingTime < 0) remainingTime = 0;

            Thread.sleep((long) remainingTime);

            nextInterval += drawInterval;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<int[]> pathfind(String algo){
        if(algo.equals("BFS")) return pathfinder.pathFindBfs(src,dest,max_pixel_row,max_pixel_col);
        if(algo.equals("Astar")) return pathfinder.Astar(srcNode,destNode,this);
        boolean[][] visited = new boolean[max_pixel_row][max_pixel_col];
        pathfinder.dfsfull(src,dest,max_pixel_col,max_pixel_row,visited,"");
        return null;
    }
}
