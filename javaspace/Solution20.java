import java.util.*;

// Codility - MaxProfit
public class Solution20 {
    public static void main(String[] args) {
        int[] A = {23171,21011,21123,21366,21013,21367};
        
        System.out.println(solution(A));

    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        int len = A.length;
        
        if (len < 2) {
            return 0;
        }
        
        int answer = 0;
        int min = A[0];
        
        for (int i=0; i<len; i++) {
            if (A[i] < min) {
                min = A[i];
            } else {
                answer = Math.max(A[i] - min, answer);
            }
        }

        return answer;
    }
}
