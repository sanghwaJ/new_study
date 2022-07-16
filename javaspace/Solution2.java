import java.util.*;

// 프로그래머스 - 네트워크
public class Solution2 {
    public static int solution2(int n, int[][] computers) {
        int answer = 0;
        int[] visited = new int[n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited, 0);
        }
        
        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                dfs(i, computers, visited, answer);
                answer += 1;
            }
        }
        
        System.out.println(answer);
        return answer;
    }
    
    public static int dfs(int start, int[][] computers, int[] visited, int answer){
        visited[start] = 1;
        for(int i = 0; i < visited.length; i++) {
            if(computers[start][i] == 1 && visited[i] == 0) {
                dfs(i, computers, visited, answer);
            }
        }
        return answer;
    }
}
