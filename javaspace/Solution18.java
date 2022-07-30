import java.util.*;

// Codility - Fish
public class Solution18 {
    public static void main(String[] args) {
        int[] A = {4, 3, 2, 1, 5};
        int[] B = {0, 1, 0, 0, 0};
        
        System.out.println(solution(A, B));
    }

    public static int solution(int[] A, int[] B) {
        // write your code in Java SE 11
        Stack<Integer> downStream = new Stack<>();
        int answer = 0;

        for (int i=0; i<A.length; i++) {
            if (B[i] == 1) {
                // downStream인 경우 stack push
                downStream.push(A[i]);
            } else {
                // upStream인 경우
                // downStream stack이 비어있지 않은 경우
                while (!downStream.isEmpty()) {
                    // downStream stack이 모두 비어질 때까지 loop
                    // downStream에 있는 것이 더 크다면 loop 종료
                    if (downStream.peek() > A[i]) {
                        break;
                    } else {
                        // upStream이 더 크다면 downStream pop
                        downStream.pop();
                    }
                }
                // downStream stack이 비었다면, answer++
                if (downStream.isEmpty()) {
                    answer++;
                }
            }
        }
        return answer + downStream.size();
    }
}
