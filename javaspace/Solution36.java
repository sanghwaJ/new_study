// sTest1
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Solution36 {
    public static void main(String[] args) {
        String[][] map3d = {{"XXXXX","OOSXO","OOXOO"},{"XEOOO","OXXXO","OOOOX"}};

        System.out.println(solution1(map3d));    
        System.out.println(solution2(map3d));    
    }
    public static int[] xpos = {1, -1, 0, 0, 0, 0};
    public static int[] ypos = {0, 0, 1, -1, 0, 0};
    public static int[] hpos = {0, 0, 0, 0, 1, -1};

    /* solution 1 start */
    public static Queue<Dot> q = new LinkedList<>();
    public static String[][][] map;

    public static int solution1(String[][] map3d) {
        height = map3d.length;
        length = map3d[0].length;
        width = map3d[0][0].length();
        
        visit = new boolean[height][length][width];
        
        return bfs(0, 0, 0, 0, map3d);
    }

    /* solution 2 start */
    public static boolean[][][] visit;
    public static int width, length, height;

    public static int solution2(String[][] map3d) {
        height = map3d.length;
        length = map3d[0].length;
        width = map3d[0][0].length();
        
        visit = new boolean[height][length][width];
        
        return bfs(0, 0, 0, 0, map3d);
    }
    
    // 최단경로 탐색 문제 => BFS 사용
    public static int bfs(int h, int y, int x, int d, String[][] map3d){
        Queue<Node> queue = new LinkedList<>();

        for (int i=0; i<height; i++) {
            for (int j=0; j<length; j++) {
                for (int k=0; k<width; k++) {
                    if (map3d[i][j].charAt(k) == 'S') {
                        h = i;
                        y = j;
                        x = k;
                    } 
                }
            }
        }

        visit[h][y][x] = true;
        queue.offer(new Node(h, y, x, d));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i=0; i<6; i++) {
                int hh = node.h + hpos[i];
                int yy = node.y + ypos[i];
                int xx = node.x + xpos[i];
                int dd = node.d + 1;

                // 범위를 넘어가면 continue
                if (hh < 0 || hh >= height || yy < 0 || yy >= length || xx < 0 || xx >= width) {
                    continue;
                }

                if (!visit[hh][yy][xx]) {
                    if (map3d[hh][yy].charAt(xx) == 'E') {
                        return dd;
                    } else if (map3d[hh][yy].charAt(xx) == 'X') {
                        continue;
                    } else if (map3d[hh][yy].charAt(xx) == 'O') {
                        visit[hh][yy][xx] = true;
                        queue.offer(new Node(h, y, x, d+1));
                    }
                }
            }
        }
        return -1;
    }

    public static class Node {
        int h, y, x, d;
        
        public Node(int h, int y, int x, int d) {
            this.x = h;
            this.y = y;
            this.h = x;
            this.d = d;
        }
    }
    /* solution 2 end */

    
    
    
}
