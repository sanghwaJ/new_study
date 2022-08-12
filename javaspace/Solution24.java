import java.util.*;
import java.lang.Math;

// Codility - ChocolatesByNumbers
public class Solution24 {
    public static void main(String[] args) {
        // Retry 필요
        int N = 10;
        int M = 4;

        int gcd;
        long ans;
        gcd = solution(N,M);
        ans = N / gcd;
        
        System.out.println(ans);
    }

    public static int solution(int N, int M) {
        // write your code in Java SE 11
        if(M == 0){
            return N;
        }
        return solution(M, N % M);
    }
}
