import java.util.*;
import java.lang.Math;

// Codility - TieRopes
public class Solution25 {
    public static void main(String[] args) {
        int[] A = {1,1,3,4,1,2,3};
        int K = 4;
        
        System.out.println(solution(A, K));
    }

    public static int solution(int[] A, int K) {
        // write your code in Java SE 11
        int ropes = 0;
        int cnt = 0;

        for (int i=0;i<A.length; i++) {
            ropes += A[i];
            if (ropes >= K) {
                cnt++;
                ropes = 0;
            } 
        }
        return cnt;
    }
}
