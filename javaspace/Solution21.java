import java.util.*;

// Codility - MaxSliceSum
public class Solution21 {
    public static void main(String[] args) {
        int[] A = {23171,21011,-21123,21366,-21013,21367};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        if (A.length < 2) {
            return A[0];
        } 

        int localMaxSum = A[0];
        int globalMaxSum = A[0];
        
        // 카데인 알고리즘 사용 
        // Brute Force로 하는 것이 아닌, 이전 계산 값을 저장하고 있기 때문에 배열을 한 번만 순회하면 됨
        for(int i=1; i<A.length; i++) {
            localMaxSum = Math.max(A[i], localMaxSum + A[i]);
            globalMaxSum = Math.max(globalMaxSum, localMaxSum);
        }
       
        return globalMaxSum;
    }
}
