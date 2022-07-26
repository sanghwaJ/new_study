import java.util.*;

// Codility - PermCheck
public class Solution12 {
    public static void main(String[] args) {
        int[] A = {1,3,2,5,4};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        int answer = 1;

        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            if (A[i] != i+1) {
                answer = 0;
                break;
            }
        }

        return answer;
    }

}
