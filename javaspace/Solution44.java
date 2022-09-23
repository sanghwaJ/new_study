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
    static int answer = 0;

    public static int solution(String begin, String target, String[] words) {
        visitedWords = new boolean[words.length];

        if (Arrays.asList(words).contains(target)) {
            dfs(begin, target, words, 0);
        }
        return answer;
    }
    
    public static void dfs(String nowWord, String target, String[] words, int cnt) {
        if (nowWord.equals(target)) {
            answer = cnt;
            return;
        }

        for (int i=0; i<words.length; i++) {
            // 아직 방문하지 않은 경우
            if(!visitedWords[i]) {
                // 한글자만 다르다면 dfs recursive
                if (charCheck(nowWord, words[i])) {
                    visitedWords[i] = true;
                    dfs(words[i], target, words, cnt+1);
                    visitedWords[i] = false;
                }
            }
        }
    }

    // 두 단어를 비교해서 1글자만 다른지에 대한 여부 return
    public static boolean charCheck(String nowWord, String nextWord) {
        int diffCount = 0;

        for (int i=0; i<nowWord.length(); i++) {
            if (nowWord.charAt(i) != nextWord.charAt(i)) {
                diffCount++;
            }
        }

        return diffCount == 1 ? true : false;
    }
}
