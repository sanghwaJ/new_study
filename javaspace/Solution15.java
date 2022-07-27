import java.util.*;

// Codility - MaxProductOfThree
public class Solution15 {
    public static void main(String[] args) {
        int[] A = {1,1,2,3,2,3};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        Arrays.sort(A);

        int ALen = A.length;

        int answer1 = A[ALen-3]*A[ALen-2]*A[ALen-1];
        int answer2 = A[0]*A[1]*A[ALen-1];

        if (answer1 > answer2) {
            return answer1;
        } else {
            return answer2;
        }
    }
}
