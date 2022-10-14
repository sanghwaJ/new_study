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
    public static int xLen, yLen;

    public static int solution(int[][] maps) {
        xLen = maps.length;
        yLen = maps[0].length;

        visit = new boolean[xLen][yLen];
        
        return bfs(0, 0, maps);
    }

    // 최단경로 탐색 문제 => BFS 사용
    public static int bfs(int x, int y, int[][] maps){
        Queue<Node> q = new LinkedList<>();

        visit[x][y] = true;
        q.offer(new Node(x, y, 1));

        while(!q.isEmpty()) {
            Node node = q.poll();

            // 목표 지점에 도착하면 cost return
            if (node.x == xLen-1 && node.y == yLen-1) {
                return node.cost;
            }
            
            for (int i=0; i<4; i++) {
                int xx = node.x + dx[i];
                int yy = node.y + dy[i];

                // 좌표 범위를 넘어가면 continue
                if (xx < 0 || xx > xLen-1 || yy < 0 || yy > yLen-1) {
                    continue;
                }

                // 아직 방문하지 않았거나, 존재하는 길(1)이라면 node 추가
                if (!visit[xx][yy] && maps[xx][yy] == 1) {
                    visit[xx][yy] = true;
                    q.offer(new Node(xx, yy, node.cost + 1));
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
