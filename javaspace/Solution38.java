// 프로그래머스 - 게임 맵 최단거리
import java.util.*;

public class Solution38 {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        System.out.println(solution(maps));   
    }

    public static boolean[][] visit;
    public static int[] dx = {0,1,0,-1};
    public static int[] dy = {1,0,-1,0};
    public static int xx = 0;
    public static int yy = 0;
    public static int cnt = 0;

    public static int solution(int[][] maps) {
        //int cnt = 0;

        visit = new boolean[maps.length][maps[0].length];

        bfs(0, 0, maps);

        return cnt;
    }

    // 최단경로 탐색 문제 => BFS 사용
    public static int bfs(int x, int y, int[][] maps){
        Queue<Node> q = new LinkedList<>();

        q.offer(new Node(x, y, 1));

        visit[x][y] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            
            for (int i=0; i<maps.length; i++) {
                for (int j=0; i<maps[0].length; j++) {
                    if 
                }
            }
        }


        return -1;
    }

    public static class Node {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    // 완전 탐색 문제 => DFS 사용
    /* 
    public static void dfs(int x, int y, int[][] maps) {
        visit[x][y] = true;
        cnt += 1;

        for (int i=0; i<4; i++) {
            xx = x + dx[i];
            yy = y + dy[i];
            
            if (xx < 0 || xx >= visit[0].length || yy < 0 || yy >= visit.length) {
                continue;
            }

            if (!visit[xx][yy] && maps[xx][yy] == 1) {
                dfs(xx, yy, maps);
            }
        }
    }
    */
}
