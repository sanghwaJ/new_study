// 1260번 DFS와 BFS
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution58 {
    static int[][] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // Scanner scan = new Scanner(System.in);
        //
        // int n = scan.nextInt();
        // int m = scan.nextInt();
        // int v = scan.nextInt();
        //
        // // 인접 리스트 생성
        // adj = new int[n+1][n+1];
        // for(int i = 0; i < m; i++) {
        //     int x = scan.nextInt();
        //     int y = scan.nextInt();
        //     adj[x][y] = 1;
        //     adj[y][x] = 1;
        // }
        //
        // scan.close();

        int n = 4;
        int m = 5;
        int v = 1;

        int[][] adjList = {{1, 2}, {1, 3}, {1, 4}, {2, 4}, {3, 4}};

        // 인접 리스트 생성
        adj = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int x = adjList[i][0];
            int y = adjList[i][1];
            adj[x][y] = 1;
            adj[y][x] = 1;
        }

        visited = new boolean[n+1];
        dfs(v);
        System.out.println();
        visited = new boolean[n+1];
        bfs(v);
    }

    public static void dfs(int v) {
        System.out.print(v + " ");
        visited[v] = true;

        for (int i = 1; i < adj[v].length; i++) {
            if (adj[v][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(v);
        visited[v] = true;
        System.out.print(v + " ");

        while (!q.isEmpty()) {
            int next = q.poll();
            for (int i = 0; i < adj[next].length; i++) {
                if (adj[next][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }
}
