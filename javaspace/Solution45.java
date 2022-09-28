// 프로그래머스 - 전력망을 둘로 나누기
import java.util.*;
import java.lang.Math;

public class Solution45 {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        //int n = 4;
        //int[][] wires = {{1,2},{2,3},{3,4}};

        //int n = 7;
        //int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};

        System.out.println(solution(n, wires));   
    }

    public static boolean[] visited;

    public static int solution(int n, int[][] wires) {
        int answer = -1;
        visited = new boolean[n];

        int[][] adj = new int[n+1][n+1];
        for (int i=0; i<wires.length; i++) {
            adj[wires[i][0]][wires[i][1]] = 1;
            adj[wires[i][1]][wires[i][0]] = 1;
        }

        for (int idx=0; idx<wires.length; idx++) {
            adj[wires[idx][0]][wires[idx][1]] = 0;
            adj[wires[idx][1]][wires[idx][0]] = 0;

            answer = Math.min(answer, bfs(0));

            adj[wires[idx][0]][wires[idx][1]] = 1;
            adj[wires[idx][1]][wires[idx][0]] = 1;
        }

        return answer;
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int nodeLen = visited.length;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            visited[node] = true;

            for (int idx=1; idx<=nodeLen; idx++) {
                
            }

            
        }


        




        return 0;

    }

    /* 
    public static int dfs(int start, int[][] wires) {
        for (int idx=0; )


        visited[start] = true;




        return 0;

    }
    */
}
