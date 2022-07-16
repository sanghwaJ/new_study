import java.util.*;

// 프로그래머스 - 타겟넘버
public class Solution3 {
    public static int solution3(int[] numbers, int target) {
        int answer = 0;

        dfs(numbers, target, answer, 0, 0);

        System.out.println(answer);
        return answer;
    }    

    public static void dfs(int[] numbers, int target, int answer, int idx, int result) {
        if(idx == numbers.length) {
            if(result == target) {
                answer += 1;
            }
            return;
        } else {
            dfs(numbers, target, answer, idx + 1, (result + numbers[idx]));
            dfs(numbers, target, answer, idx + 1, (result - numbers[idx]));
        }
        
    }
}
