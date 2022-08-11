import java.util.*;

// Codility - CountFactors
public class Solution22 {
    public static void main(String[] args) {
        int N = 2147395600;
        
        System.out.println(solution(N));
    }

    public static int solution(int N) {
        // write your code in Java SE 11
        if (N == 1) {
            return 1;
        }

        int answer = 0;
        long i = 1;

        while (i * i < N) {
            if (N % i == 0) {
                answer++;
            }
            i++;
        }
        answer = answer * 2;

        if (i * i == N) {
            answer++;
        }
        
        return answer;
    }
}
