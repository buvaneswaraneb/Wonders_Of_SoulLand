import java.util.LinkedList;
import java.util.Queue;

public class test_path {

    int[][] directions = {
            {0,1}, // right
            {1,0}, // down
            {-1,0}, // up
            {0,-1} // left
    };

    String res = " ";

    public test_path(){

    }

    public void bfs(int[] src , int[] des, int[][] adj, int[][] directions){

        int row_len = adj.length;
        int col_len = adj[0].length;

        Queue<String[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[row_len][col_len];

        q.offer(new String[]{""+src[0], ""+src[1], " "});

        visited[src[0]][src[1]] = true;

        while(!q.isEmpty()){

            String[] curr = q.poll();

            int row = Integer.parseInt(curr[0]);
            int col = Integer.parseInt(curr[1]);
            String path = curr[2];

            if (row == des[0] && col == des[1]){
                this.res = path;
                return;
            }

            for(int i = 0; i < directions.length ; i++){

                int[] d = directions[i];
                int nr = row + d[0];
                int nc = col + d[1];

                if ((nr > 0 && nr < adj.length) && (nc > 0 && nc < adj[0].length) && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    if (i == 0) q.offer(new String[]{""+nc, ""+nr, path + "RIGHT " });
                    else if (i == 1) q.offer(new String[]{""+nc, ""+nr, path + "DOWN " });
                    else if (i == 2) q.offer(new String[]{""+nc, ""+nr, path + "UP " });
                    else if (i == 3) q.offer(new String[]{""+nc, ""+nr, path + "LEFT " });
                }
            }
        }
    }

    public void dfs (int[] src , int[] dest , int[][] adj , boolean[][] visited,int[][] direction,String path){
        visited[src[0]][src[1]] = true;

        if (src[0] == dest[0] && src[1] == dest[1]){
            if(path.length() < res.length()) this.res = path;
            System.out.println(path);
        }

        for (int i = 0 ; i < direction.length ; i++){
            int[] d = direction[i];
            int nr = d[0] + src[0];
            int nc = d[1] + src[1];

            if ((nr > 0 && nr < adj.length) && (nc > 0 && nc < adj[0].length) && !visited[nr][nc]){
                if (i == 0) dfs( new int[]{nr,nc},dest,adj,visited,direction,path + "RIGHT ");
                if (i == 1) dfs( new int[]{nr,nc},dest,adj,visited,direction,path + "DOWN ");
                if (i == 2) dfs( new int[]{nr,nc},dest,adj,visited,direction,path + "UP ");
                if (i == 3) dfs( new int[]{nr,nc},dest,adj,visited,direction,path + "LEFT ");
            }
        }
}
}

