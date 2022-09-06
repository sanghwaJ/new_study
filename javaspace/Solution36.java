// sTest1
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        width = map3d[0][0].length();
        length = map3d[0].length;
        height = map3d.length;

        visit = new boolean[height][length][width];
        


        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd 09:00:00");
        Date today = new Date();
        //System.out.println(today);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.HOUR, 1);
        System.out.println(dateParser.format(cal.getTime()));
 
        //System.out.println(Arrays.deepToString(visit));
        return bfs(0, 0, 0, map3d);

        //return 1;
    }
    
    // 최단경로 탐색 문제 => BFS 사용
    public static int bfs(int x, int y, int h, String[][] map3d){
        // for (int i=0; i<height; i++) {
        //     for (int j=0; j<length) {
        //         for (int k=0;)
        //     }
        // }
        
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
        int x, y, h;
        
        public Node(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
