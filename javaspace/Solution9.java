import java.util.*;
import java.util.stream.Collectors;

// Codility - CyclicRotation 
public class Solution9 {
    public static void main(String[] args) {
        int[] A = {3,8,9,7,6};
        int K = 3;

        System.out.println(Arrays.toString(solution(A, K)));
    }

    public static int[] solution(int[] A, int K) {
        if (A.length == 0) {
            return A;
        }

        // arr to list
        List<Integer> _answer = Arrays.stream(A).boxed().collect(Collectors.toList());
        
        int size = _answer.size() - 1;
       
        int temp = 0;
        for (int i=0; i<K; i++) {
            temp = _answer.get(size);
            _answer.remove(size);
            _answer.add(0, temp);
        }

        // list to arr
        int[] answer = _answer.stream().mapToInt(i->i).toArray();

        return answer;
    }
}
