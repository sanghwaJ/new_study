import java.util.*;

// Codility - Dominator
public class Solution19 {
    public static void main(String[] args) {
        int[] A = {0,1,1,1,1,1,1,4,1,2,1,4,1,2,0,2,2,1,1};
        
        System.out.println(solution(A));
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        if (A.length == 0) {
            return -1;
        } else if (A.length == 1) {
            return 0;
        }

        int answer = -1;
        int check = A.length / 2;

        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i=0; i<A.length; i++) {
            if(map.containsKey(A[i])) {
                map.replace(A[i], map.get(A[i])+1);
                if (map.get(A[i]) > check) {
                    answer = i;
                    return answer;
                }
            } else {
                map.put(A[i], 1);
            }
        }
        return answer;
    }
}
