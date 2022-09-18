// Codility - OddOccurrencesInArray
import java.util.*;

public class Solution39 {
    public static void main(String[] args) {
        int[] A = {9,3,9,3,9,7,9};

        System.out.println(solution(A));   
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        int ALen = A.length;

        if (ALen == 1) {
            return A[0];
        }

        int answer = 0;
        Arrays.sort(A);

        for (int i=0; i<ALen; i+=2) {
            if (i+1 == ALen) {
                answer =  A[i];
                break;
            } else if (A[i] != A[i+1]) {
                answer =  A[i];
                break;
            }
        }

        return answer;
    }
}
