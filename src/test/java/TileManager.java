import java.awt.*;
import java.util.ArrayList;

public class TileManager {

    TestPannel tp;

    public int index = 0;
    public int totalindex = 0;
    int timecounter = 0;

    ArrayList<int[]> localQueue = new ArrayList<>();
    ArrayList<int[]> TotalLocalQueue = new ArrayList<>();
    public Color tileColor = Color.yellow;


    TileManager(TestPannel tp){
        this.tp = tp;
    }

    public void draw(Graphics2D g2d){

    }

    public void fill(Graphics2D g2d){

        for(int i = 0; i < tp.max_pixel_row;i++){
            for (int j = 0; j < tp.max_pixel_col; j++){

                if (tp.tiles[i][j].collison){
                    g2d.setColor(Color.darkGray);
                }
                else g2d.setColor(Color.WHITE);

                g2d.fillRect(j*tp.tile_size,i*tp.tile_size,tp.tile_size,tp.tile_size);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke( 1));
                g2d.drawRect(j*tp.tile_size,i*tp.tile_size,tp.tile_size,tp.tile_size);
            }
        }
    }





    public void update(){
        timecounter++;

        if (timecounter >= 2 && tp.pathfinder.total != null && totalindex < tp.pathfinder.total.size()){
            TotalLocalQueue.add(tp.pathfinder.total.get(totalindex));
            totalindex++;
            timecounter = 0;
        }
        if(totalindex >= tp.pathfinder.total.size() && timecounter >= 1 && tp.arr != null && index < tp.arr.size()){
            localQueue.add(tp.arr.get(index));
            index++;
            timecounter = 0;
        }
    }

    public void totalDrawPath(Graphics2D g2d){

        for (int[] i : TotalLocalQueue){
            int x = i[0];
            int y = i[1];

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(x * tp.tile_size, y * tp.tile_size, tp.tile_size, tp.tile_size);

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(x * tp.tile_size, y * tp.tile_size, tp.tile_size, tp.tile_size);
        }

    }

    public void drawpath(Graphics2D g2d){

           for (int[] i : localQueue){
                int x = i[0];
                int y = i[1];

                g2d.setColor(Color.orange);
                g2d.fillRect(x * tp.tile_size, y * tp.tile_size, tp.tile_size, tp.tile_size);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawRect(x * tp.tile_size, y * tp.tile_size, tp.tile_size, tp.tile_size);
            }
    }


    public void drawCursor(Graphics2D g2d){
        g2d.setColor(Color.orange);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(tp.cursor[0]*tp.tile_size,tp.cursor[1]*tp.tile_size,tp.tile_size,tp.tile_size);

    }

    public void points(Graphics2D g2d){
        // start point or source
        int x  = tp.src[0];
        int y  = tp.src[1];
        g2d.setColor(Color.RED);
        g2d.fillRect(x*tp.tile_size,y*tp.tile_size,tp.tile_size,tp.tile_size);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke( 1));
        g2d.drawRect(x*tp.tile_size,y*tp.tile_size,tp.tile_size,tp.tile_size);

        String src = "src";
        x = (x * tp.tile_size) + tp.tile_size/2  -  10;
        y = (y * tp.tile_size) + tp.tile_size/2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(src,x,y);

        //dest
        x  = tp.dest[0];
        y  = tp.dest[1];

        g2d.setColor(Color.green);
        g2d.fillRect(x*tp.tile_size,y*tp.tile_size,tp.tile_size,tp.tile_size);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke( 1));
        g2d.drawRect(x*tp.tile_size,y*tp.tile_size,tp.tile_size,tp.tile_size);

        String dest = "dest";
        x = (x * tp.tile_size) + tp.tile_size/ 2 - 10 ;
        y = (y * tp.tile_size) + tp.tile_size/2;
        g2d.setColor(Color.BLACK);
        g2d.drawString(dest,x,y);


    }
}
