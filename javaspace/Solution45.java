// 프로그래머스 - 전력망을 둘로 나누기
import java.util.*;
import java.lang.Math;

public class Solution45 {
    public static void main(String[] args) {
        // result = 3
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        // result = 0
        //int n = 4;
        //int[][] wires = {{1,2},{2,3},{3,4}};

        // result = 1
        //int n = 7;
        //int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};

        System.out.println(solution(n, wires));   
    }

    public static int[][] adj;

    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        adj = new int[n+1][n+1];
        for (int idx=0; idx<wires.length; idx++) {
            adj[wires[idx][0]][wires[idx][1]] = 1;
            adj[wires[idx][1]][wires[idx][0]] = 1;
        }

        for (int idx=0; idx<wires.length; idx++) {
            // 선 하나를 끊음
            adj[wires[idx][0]][wires[idx][1]] = 0;
            adj[wires[idx][1]][wires[idx][0]] = 0;

            // 최소값 갱신
            answer = Math.min(answer, bfs(n, wires[idx][0]));

            // 끊은 선 복구
            adj[wires[idx][0]][wires[idx][1]] = 1;
            adj[wires[idx][1]][wires[idx][0]] = 1;
        }

        return answer;
    }

    public static int bfs(int n, int start) {
        boolean[] visited = new boolean[n+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int count = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            for (int idx=1; idx<n+1; idx++) {
                if(!visited[idx]) {
                    if (adj[node][idx] == 1) {
                        queue.offer(idx);
                        count++;
                    }
                }
            }
        }
        
        // 두 group이 가지고 있는 송전탑 갯수의 차
        return (int)Math.abs(n-2 * count);
    }
}
