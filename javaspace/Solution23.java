import java.util.*;
import java.lang.Math;

// Codility - MinPerimeterRectangle
public class Solution23 {
    public static void main(String[] args) {
        int N = 36;
        
        System.out.println(solution(N));
    }

    public static int solution(int N) {
        // write your code in Java SE 11
        if (N == 1) {
            return 4;
        }

        int answer = N + 1;
        int i = 1;

        while (i * i < N) {
            if (N % i == 0) {
                answer = Math.min(answer, i+(N/i));
            }
            i++;
        }

        if (i * i == N) {
            answer = Math.min(answer, i+(N/i));
        }

        answer = answer * 2;

        return answer;
    }
}
