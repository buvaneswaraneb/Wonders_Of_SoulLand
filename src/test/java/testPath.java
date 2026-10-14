import java.util.*;

public class testPath {

    int[][] directions = {
            {1, 0},  // right
            {0, 1},  // down
            {0, -1}, // up
            {-1, 0}, // left
            {1, -1}, // North East
            {-1, 1}, // South West
            {-1, -1},// North West
            {1, 1}   // South East
    };

    String res = " ";

    public List<int[]> total = new ArrayList<>();
    public testPath(){

    }


    public ArrayList<int[]> pathFindBfsByPathString(String pathString, int maxRow, int maxCol){
        ArrayList<int[]> q = new ArrayList<>();

        String[] p1 =pathString.split("->");
        for(int i = 1; i < p1.length - 1; i++){
            String p2 = p1[i].trim();
            // p2 [4,4]
            p2 = p2.substring(1, p2.length()-1);

            String[] p3 = p2.split(",");

            int[] p4 = {Integer.parseInt(p3[0]),Integer.parseInt(p3[1])};
            q.add(p4);

        }

        return q;
    }





    public String bfs_full(int[] src , int[] des, int maxRow, int maxCol){

        Queue<String[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maxRow][maxCol];

        q.offer(new String[]{""+src[1], ""+src[0], "[" + src[0] + "," + src[1] + "]"});
        String totalPath = "[" + src[0] + "," + src[1] + "]";
        visited[src[1]][src[0]] = true;

        while(!q.isEmpty()){

            String[] curr = q.poll();

            int row = Integer.parseInt(curr[0]);
            int col = Integer.parseInt(curr[1]);
            String path = curr[2];

            if (row == des[1] && col == des[0]){
                return totalPath;
            }

            for(int i = 0; i < directions.length ; i++){

                int[] d = directions[i];
                int nr = row + d[1];
                int nc = col + d[0];

                if ((nr >= 0 && nr < maxRow) && (nc >= 0 && nc < maxCol) && !visited[nr][nc]){
                    //System.out.println("Current: [" + nc + "," + nr + "]");
                    visited[nr][nc] = true;
                    totalPath += " -> [" + nc + "," + nr + "]";
                    String[] arr = new String[]{ "" + nr, "" + nc, path + " -> [" + nc + "," + nr + "]"};
                    if (nr == des[1] && nc == des[0]){
                        System.out.println(totalPath);
                        return totalPath;
                    }
                    q.offer(arr);
                }
            }
        }

        System.out.println("cant find");
        return "cant find";
    }

    public ArrayList<int[]> pathFindBfs(int[] src , int[] dest, int maxRow, int maxCol){
        ArrayList<int[]> q = new ArrayList<>();
        String pathString = bfs(src,dest,maxRow,maxCol);

        // fix ->

        String[] p1 =pathString.split("->");
        for(int i = 1; i < p1.length - 1; i++){
            String p2 = p1[i].trim();
            // p2 [4,4]
            p2 = p2.substring(1, p2.length()-1);

            String[] p3 = p2.split(",");

            int[] p4 = {Integer.parseInt(p3[0]),Integer.parseInt(p3[1])};
            q.add(p4);

        }

        return q;
    }

    public String bfs(int[] src , int[] des, int maxRow, int maxCol){

        Queue<String[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maxRow][maxCol];


        q.offer(new String[]{""+src[1], ""+src[0], "[" + src[0] + "," + src[1] + "]"});

        visited[src[1]][src[0]] = true;

        while(!q.isEmpty()){

            String[] curr = q.poll();

            int row = Integer.parseInt(curr[0]);
            int col = Integer.parseInt(curr[1]);
            String path = curr[2];

            if (row == des[1] && col == des[0]){
                return path;
            }

            for(int i = 0; i < directions.length ; i++){

                int[] d = directions[i];
                int nr = row + d[1];
                int nc = col + d[0];

                if ((nr >= 0 && nr < maxRow) && (nc >= 0 && nc < maxCol) && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    String[] arr = new String[]{ "" + nr, "" + nc, path + " -> [" + nc + "," + nr + "]"};
                    q.offer(arr);
                }
            }
        }

        System.out.println("cant find");
        return "cant find";
    }

    public void dfsfull (int[] src , int[] dest ,int max_col , int max_row, boolean[][] visited,String path){
        visited[src[1]][src[0]] = true;

        if (src[0] == dest[0] && src[1] == dest[1]){
            this.res = path;
            System.out.println(path);
        }

        for(int[] d: directions){
            int nc = d[0] + src[0];
            int nr = d[1] + src[1];

            if ((nr >= 0 && nr < max_row) && (nc >= 0 && nc < max_col) && !visited[nr][nc]){
                dfsfull( new int[]{nc,nr},dest,max_col,max_row,visited,path +  " -> [" + nc + "," + nr + "]");
            }
        }
    }




    // ================================ A* Search ========================================

    public void calculateFGH(TestPannel tp){
        int row = tp.max_pixel_row;
        int col = tp.max_pixel_col;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                Node curr = tp.tiles[i][j];
                curr.g = Integer.MAX_VALUE;
                if (tp.destNode != null) {
                    int dx = Math.abs(curr.x - tp.destNode.x);
                    int dy = Math.abs(curr.y - tp.destNode.y);
                    curr.h = Math.max(dx, dy);
                } else {
                    curr.h = 0;
                }
                curr.f = Integer.MAX_VALUE;
                curr.parent = null;
            }
        }
    }


    public List<int[]> reconstruct (Node dest){
        List<int[]> ans = new LinkedList<>();

        Node curr = dest;
        while(curr != null){
            ans.addFirst(new int[]{curr.x, curr.y});
            System.out.printf("[%d , %d] -> ", curr.x, curr.y);
            curr = curr.parent;
        }
        System.out.println("END");
        return ans;
    }


    public List<int[]> Astar(Node src ,Node dest, TestPannel tp){
        if (src == null || dest == null || tp == null) {
            return new ArrayList<>();
        }

        if (src.collison || dest.collison) {
            System.out.println("Source or Destination is collision tile");
            return new ArrayList<>();
        }

        if (src.x == dest.x && src.y == dest.y) {
            List<int[]> single = new ArrayList<>();
            single.add(new int[]{src.x, src.y});
            return single;
        }

        calculateFGH(tp);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.f != b.f) {
                return Integer.compare(a.f, b.f);
            }
            return Integer.compare(a.h, b.h);
        });
        Set<Node> openSet = new HashSet<>();
        boolean[][] closed = new boolean[tp.max_pixel_row][tp.max_pixel_col];

        src.g = 0;
        int dx = Math.abs(src.x - dest.x);
        int dy = Math.abs(src.y - dest.y);
        src.h = Math.max(dx, dy);
        src.f = src.g + src.h;

        pq.offer(src);
        openSet.add(src);

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            openSet.remove(curr);

            if(curr.x == dest.x && curr.y == dest.y) {
                return reconstruct(curr);
            }

            closed[curr.y][curr.x] = true;

            for(int[] d : directions){
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx >= 0 && nx < tp.max_pixel_col && ny >= 0 && ny < tp.max_pixel_row){

                    if (closed[ny][nx]) continue;

                    Node neighbour = tp.tiles[ny][nx];
                    if (neighbour.collison) continue;

                    if (d[0] != 0 && d[1] != 0) {
                        if (tp.tiles[ny][curr.x].collison && tp.tiles[curr.y][nx].collison) {
                            continue;
                        }
                    }

                    int tentativeG = curr.g + 1;

                    if (tentativeG < neighbour.g){
                        neighbour.parent = curr;
                        neighbour.g = tentativeG;
                        int ndx = Math.abs(neighbour.x - dest.x);
                        int ndy = Math.abs(neighbour.y - dest.y);
                        neighbour.h = Math.max(ndx, ndy);
                        neighbour.f = neighbour.g + neighbour.h;

                        total.add(new int[]{nx,ny});
                        if (openSet.contains(neighbour)) {
                            pq.remove(neighbour);
                        } else {
                            openSet.add(neighbour);
                        }
                        pq.offer(neighbour);
                    }
                }
            }
        }

        System.out.println("cant find");
        return new ArrayList<>();
    }

}

