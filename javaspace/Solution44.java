// 프로그래머스 - 단어 변환
import java.util.*;

public class Solution44 {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot","dot","dog","lot","log","cog"};

        System.out.println(solution(begin, target, words));   
    }

    static boolean[] visitedWords;

    public static int solution(String begin, String target, String[] words) {
        visitedWords = new boolean[words.length];

        int answer = 0;
        if (target.contains("target")) {
            for (int idx=words.length-1; idx<=0; idx--) {
                if (target.equals(words[idx])) {
                    answer++;
                    continue;
                }
    
    
            }
        } else {
            return 0;
        }
        
        return answer;
    }

    public static void dfs(String[] words) {
        for (int i=0; i<words.length; i++) {
            if(!visitedWords[i]) {
                visitedWords[i] = true;
                dfs(words);
            }
        }
    }

    /*
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
     */
}
