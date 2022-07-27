import java.util.*;

// Codility - Distinct
public class Solution14 {
    public static void main(String[] args) {
        int[] A = {1,1,2,3,2,3};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        HashSet<Integer> set = new HashSet<>();

        for (int i : A) {
            set.add(i);
        }

        return set.size();
    }
}
