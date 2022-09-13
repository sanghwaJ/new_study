// sTest1
import java.util.*;

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
        q = new LinkedList<Dot>();

        int answer = -1;

        int H = map3d.length;
        int N = map3d[0].length;
        int M = map3d[0][0].length();

        map = new String[H][N][M];

        for (int h=0; h<H; h++) {
            for (int n=0; n<N; n++) {
                for (int m=0; m<M; m++) {
                    String value = map3d[h][n].charAt(m)+"";
                    // 어짜피 S의 위치를 찾아야하기 때문에 loop를 돌아야 함
                    // 이 때 접근하기 편하고 방문한 곳 처리를 위해 map을 만들어 줌
                    if ("S".equals(value)) {
                        map[h][n][m] = "S";
                        q.add(new Dot(h, n, m, 0));
                    }
                    if ("O".equals(value)) {
                        map[h][n][m] = "O";
                    }
                    if ("E".equals(value)) {
                        map[h][n][m] = "E";
                    }
                }
            }
        }

        while(!q.isEmpty()) {
            int qLen = q.size();

            // for문은 있어도 없어도 그만이었을 듯..
            for (int i=0; i<qLen; i++) {
                Dot dot = q.poll();
                
                int h = dot.h;
                int y = dot.y;
                int x = dot.x;
                int d = dot.d;

                for (int j=0; j<6; j++) {
                    int hh = h + hpos[j];
                    int yy = y + ypos[j];
                    int xx = x + xpos[j];
                    int dd = d + 1;
                    
                    // 범위를 넘어가면 continue
                    if (hh < 0 || hh >= H || yy < 0 || yy >= N || xx < 0 || xx >= M) {
                        continue;
                    }

                    if (map[hh][yy][xx] != null) {
                        if ("E".equals(map[hh][yy][xx])){
                            return dd;
                        } else if ("O".equals(map[hh][yy][xx])) {
                            map[hh][yy][xx] = "X";
                            q.add(new Dot(hh, yy, xx, dd));
                        } 
                    } 
                }
            }
        }
        return answer;
    }

    public static class Dot {
        int h, y, x, d;
        
        public Dot(int h, int y, int x, int d) {
            this.h = h;
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
    /* solution 1 end */

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

                // visit만 만들고 바로 접근
                if (!visit[hh][yy][xx]) {
                    if (map3d[hh][yy].charAt(xx) == 'E') {
                        return dd;
                    } else if (map3d[hh][yy].charAt(xx) == 'X') {
                        continue;
                    } else if (map3d[hh][yy].charAt(xx) == 'O') {
                        visit[hh][yy][xx] = true;
                        queue.offer(new Node(hh, yy, xx, dd));
                    }
                }
            }
        }
        return -1;
    }

    public static class Node {
        int h, y, x, d;
        
        public Node(int h, int y, int x, int d) {
            this.h = h;
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
    /* solution 2 end */
}
