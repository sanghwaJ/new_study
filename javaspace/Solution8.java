import java.util.*;

// Codility - BinaryGap
public class Solution8 {
    public static void main(String[] args) {
        int N = 1818;

        System.out.println(solution(N));
    }

    public static int solution(int N) {
        // write your code in Java SE 11
        String binary = Integer.toBinaryString(N);
        int answer = 0;
        int cnt = 0;
  
        if (binary.contains("0")) {
            String[] binaryArr = binary.split("");
            for (int i=0; i<binaryArr.length; i++) {
                if (binaryArr[i].equals("0")){
                    cnt++;
                } else {
                    answer = Math.max(answer, cnt);
                    cnt = 0;
                }
            }   
        } else {
            return answer;
        }
        return answer;
    }
}
