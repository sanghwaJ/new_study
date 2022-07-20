import java.util.*;

// Codility - FrogRiverOne => 수정필요
public class Solution11 {
    public static void main(String[] args) {
        int X = 5;
        int[] A = {1,3,1,4,2,3,2,5,4};
        
        System.out.println(solution(X, A));
    }

    public static int solution(int X, int[] A) {
        // write your code in Java SE 11
        int answer = -1;

        HashSet<Integer> set = new HashSet<>();
        for (int i=0; i<A.length; i++) {
            set.add(A[i]);
            if (set.size() == X && A[i] == X) {
                answer = i;
                return answer;
            }
        }
        
        return answer;
    }

}
