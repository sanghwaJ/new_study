// Codility - MinMaxDivision
import java.util.*;

public class Solution40 {
    public static void main(String[] args) {
        int K = 3;
        int M = 5;
        int[] A = {2,1,5,1,2,2,2};

        System.out.println(solution(K, M, A));   
    }

    public static int solution(int K, int M, int[] A) {
        // write your code in Java SE 11
        int sumA = 0;
        int maxOfA = 0;

        // 배열A의 합계와 배열A 내 최대 값 확인
        for (int idx = 0; idx<A.length; idx++) {
            sumA += A[idx];
            if (A[idx] > maxOfA) {
                maxOfA = A[idx];
            }
        }

        // answer를 배열A의 전체 합(answer의 상한선)으로 초기화
        int answer = sumA;
        // 배열A 내 최대 값이(answer의 하한선) answer를 배열A의 전체 합(answer의 상한선)보다 작거나 같을 경우 계속 수행
        while (maxOfA <= sumA) {
            // 상한 값과 하한 값을 더하고 2로 나누어 binary search 수행
            int midOfA = (maxOfA + sumA) / 2;
            // 배열A를 K개의 블록으로 분할 가능한지 확인
            if(divisable(midOfA, K, A)) {
                // K개의 블록으로 분할이 가능하면 answer에 mid 값을 넣고, 
                answer = midOfA;
                // 상한 값을 mid-1 값으로 변경하여 binary search 수행
                sumA = midOfA - 1;
            } else {
                // K개의 블록으로 분할이 불가능하면(반복 도중 divisableSize가 0이되면), 하한 값을 mid+1 값으로 변경하여 binary search 수행 
                maxOfA = midOfA + 1;
            }
        }
        return answer;
    }

    private static boolean divisable(int midOfA, int K, int[] A) {
        int divisableSize = K;
        int sumA = 0;

        for (int idx = 0; idx < A.length; idx++) {
            sumA += A[idx];
            if (sumA > midOfA) {
                divisableSize--;
                sumA = A[idx];
            }
            if (divisableSize == 0) {
                return false;
            }
        }
        return true;
    }
}
