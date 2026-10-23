package Main;

import Entity.Entity;
import GridManager.GridNode;

import java.util.*;

public class PathFind {

    GamePanel gp;

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

    public List<int[]> total = new ArrayList<>();

    public PathFind(GamePanel gp) {
        this.gp = gp;
    }

    private void calculateFGH(GridNode destNode) {
        int row = gp.currentBiome.BiomeHeight;
        int col = gp.currentBiome.BiomeWidth;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GridNode curr = gp.currentBiome.grids[i][j];
                curr.g = Integer.MAX_VALUE;
                if (destNode != null) {
                    int dx = Math.abs(curr.x - destNode.x);
                    int dy = Math.abs(curr.y - destNode.y);
                    curr.h = Math.max(dx, dy);
                } else {
                    curr.h = 0;
                }
                curr.f = Integer.MAX_VALUE;
                curr.parent = null;
            }
        }
    }


    private List<int[]> reconstruct(GridNode dest) {
        List<int[]> ans = new LinkedList<>();

        GridNode curr = dest;
        while (curr != null) {
            ans.addFirst(new int[]{curr.x, curr.y});
            if (gp.debug) System.out.printf("[%d , %d] -> ", curr.x, curr.y);
            curr = curr.parent;
        }
        if(gp.debug) System.out.println("END");
        return ans;
    }


    public List<int[]> Astar(GridNode src, GridNode dest) {
        if (src == null || dest == null || gp == null || gp.currentBiome.grids == null) {
            return new ArrayList<>();
        }

        if (!src.walkable || !dest.walkable) {
            System.out.println("Source or Destination is collision tile");
            return new ArrayList<>();
        }

        if (src.x == dest.x && src.y == dest.y) {
            List<int[]> single = new ArrayList<>();
            single.add(new int[]{src.x, src.y});
            return single;
        }

        calculateFGH(dest);

        PriorityQueue<GridNode> pq = new PriorityQueue<>((a, b) -> {
            if (a.f != b.f) {
                return Integer.compare(a.f, b.f);
            }
            return Integer.compare(a.h, b.h);
        });
        Set<GridNode> openSet = new HashSet<>();
        boolean[][] closed = new boolean[gp.currentBiome.BiomeHeight][gp.currentBiome.BiomeWidth];

        src.g = 0;
        int dx = Math.abs(src.x - dest.x);
        int dy = Math.abs(src.y - dest.y);
        src.h = Math.max(dx, dy);
        src.f = src.g + src.h;

        pq.offer(src);
        openSet.add(src);

        while (!pq.isEmpty()) {
            GridNode curr = pq.poll();
            openSet.remove(curr);

            if (curr.x == dest.x && curr.y == dest.y) {
                return reconstruct(curr);
            }

            closed[curr.y][curr.x] = true;

            for (int[] d : directions) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx >= 0 && nx < gp.currentBiome.BiomeWidth && ny >= 0 && ny < gp.currentBiome.BiomeHeight) {

                    if (closed[ny][nx]) continue;

                    GridNode neighbour = gp.currentBiome.grids[ny][nx];
                    if (!neighbour.walkable) continue;

                    if (d[0] != 0 && d[1] != 0) {
                        if (!gp.currentBiome.grids[ny][curr.x].walkable && !gp.currentBiome.grids[curr.y][nx].walkable) {
                            continue;
                        }
                    }

                    int tentativeG = curr.g + 1;

                    if (tentativeG < neighbour.g) {
                        neighbour.parent = curr;
                        neighbour.g = tentativeG;
                        int ndx = Math.abs(neighbour.x - dest.x);
                        int ndy = Math.abs(neighbour.y - dest.y);
                        neighbour.h = Math.max(ndx, ndy);
                        neighbour.f = neighbour.g + neighbour.h;

                        if (gp.debug) total.add(new int[]{nx, ny});

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
        return new ArrayList<>();
    }
}
