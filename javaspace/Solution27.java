import java.util.*;
import java.lang.Math;

// Codility - nTest2
public class Solution27 {
    public static void main(String[] args) {
        int N = 500;
        System.out.println(solution(N));        
    }

    public static int solution(int N) {
        // write your code in Java SE 11
        int answer = N;
        int digitsSumN = digitsSum(N) * 2;
        while (true) {
            if (digitsSumN <= digitsSum(answer)) {
                break;
            }    
            answer++;
        }
        return answer;
    }

    // 각 자리수 합 구하는 함수
    public static int digitsSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num%10;
            num = num/10;
        }
        return sum;
    }
}
