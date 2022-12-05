// Codility - CountDiv
import java.util.*;

public class Solution55 {
    public static void main(String[] args) {
        int A = 6;
        int B = 11;
        int K = 2;
        
        System.out.println(solution(A, B, K));
    }

    public static int solution(int A, int B, int K) {
        // Timeout
        // int answer = 0;
        // for (int i=A; i<B+1; i++) {
        //     if (i % K == 0) {
        //         for (int j=i; j<B+1; j+=K) {
        //             answer++;
        //         }
        //         return answer;
        //     }
        // }
        // return answer;

        if (A % K == 0) return B/K - A/K + 1;
            
        return B/K - A/K;
    }
}
