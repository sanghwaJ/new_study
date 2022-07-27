import java.util.*;

// Codility - Triangle
public class Solution16 {
    public static void main(String[] args) {
        int[] A = {10,20,22,3,1,4};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        int aLength = A.length;
        if (aLength < 3) {
            return 0;
        }
        
        Arrays.sort(A);

        for (int i=0; i < aLength-2; i++) {
            long P = A[i];
            long Q = A[i+1];
            long R = A[i+2];
            if (P + Q > R) {
                return 1;
            }
        }
        return 0;
    }
}
