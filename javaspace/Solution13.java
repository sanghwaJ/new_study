import java.util.*;

// Codility - PassingCars
public class Solution13 {
    public static void main(String[] args) {
        int[] A = {0,1,0,1,1};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        int cnt = 0;
        int answer = 0;
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                cnt++;
            } else if (A[i] == 1) {
                answer += cnt;
                if (answer > 1000000000) {
                    return -1;
                }
            }
        }
        return answer;
    }
}
