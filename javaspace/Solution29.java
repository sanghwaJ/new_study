// 프로그래머스 - 피로도
public class Solution29 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};

        System.out.println(solution(k, dungeons));   
    }

    public static int answer = 0;
    public static boolean[] visit;
    public static int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];

        dfs(k, dungeons, 0);

        return answer;
    }
    
    public static void dfs(int k, int[][] dungeons, int cnt) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k) {
                visit[i] = true;
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                visit[i] = false;
            }
        }

        answer = Math.max(answer, cnt);
    }
}
