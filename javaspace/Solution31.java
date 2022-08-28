// 프로그래머스 - H-Index
import java.util.*;

public class Solution31 {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};

        System.out.println(solution(citations));   
    }

    public static int solution(int[] citations) {
        Arrays.sort(citations);
        
        int answer = 0;
        int h = 0;

        // [0] 1 3 5 6 => 0회 이상 인용된 논문이 5편
        // 0 [1] 3 5 6 => 1회 이상 인용된 논문이 4편
        // 0 1 [3] 5 6 => 3회 이상 인용된 논문이 3편
        // 0 1 3 [5] 6 => 5회 이상 인용된 논문이 2편
        // 0 1 3 5 [6] => 6회 이상 인용된 논문이 1편

        for (int i=0; i<citations.length; i++) {
            h = citations.length - i;
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}
