// sTest1
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Solution36 {
    public static void main(String[] args) {
        String[][] map3d = {{"XXXXX","OOSXO","OOXOO"},{"XEOOO","OXXXO","OOOOX"}};

        System.out.println(solution(map3d));    
    }

    public static int[] xpos = {1, -1, 0, 0, 0, 0};
    public static int[] ypos = {0, 0, 1, -1, 0, 0};
    public static int[] hpos = {0, 0, 0, 0, 1, -1};
    public static boolean[][][] visit;
    public static int width, length, height;

    public static int solution(String[][] map3d) {
        height = map3d.length;
        length = map3d[0].length;
        width = map3d[0][0].length();
        
        visit = new boolean[height][length][width];


        System.out.println(map3d[1][1].charAt(1));
        
        for (int i=0; i<height; i++) {
            for (int j=0; j<length; j++) {
                for (int k=0; k<width; k++) {
                    if (map3d[i][j].charAt(k) == 'S') {
                        return bfs(0, 0, 0, map3d);
                    }
                }
            }
        }
    
        return 1;
    }
    
    // 최단경로 탐색 문제 => BFS 사용
    public static int bfs(int h, int y, int x, String[][] map3d){
        Queue<Node> queue = new LinkedList<>();

        visit[h][y][x] = true;
        queue.offer(new Node(h, y, x));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            
        }




        // Queue<Node> q = new LinkedList<>();

        // visit[x][y] = true;
        // q.offer(new Node(x, y, 1));

        // while(!q.isEmpty()) {
        //     Node node = q.poll();

        //     // 목표 지점에 도착하면 cost return
        //     if (node.x == xLen-1 && node.y == yLen-1) {
        //         return node.cost;
        //     }
            
        //     for (int i=0; i<4; i++) {
        //         int xx = node.x + dx[i];
        //         int yy = node.y + dy[i];

        //         // 좌표 범위를 넘어가면 continue
        //         if (xx < 0 || xx > xLen-1 || yy < 0 || yy > yLen-1) {
        //             continue;
        //         }

        //         // 아직 방문하지 않았거나, 존재하는 길(1)이라면 node 추가
        //         if (!visit[xx][yy] && maps[xx][yy] == 1) {
        //             visit[xx][yy] = true;
        //             q.offer(new Node(xx, yy, node.cost + 1));
        //         }
        //     }
        // }
        
        
        return -1;
    }
    
    public static class Node {
        int h, y, x;
        
        public Node(int h, int y, int x) {
            this.x = h;
            this.y = y;
            this.h = x;
        }
    }
}
