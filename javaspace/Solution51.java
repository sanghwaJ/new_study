// 프로그래머스 - 택배상자
import java.util.*;

public class Solution51 {
    public static void main(String[] args) {
        int[][] orderList = {{4, 3, 1, 2, 5}, {5, 4, 3, 2, 1}};
        int[] answerList = {2, 5};
        
        
        for (int i=0; i<orderList.length; i++) {
            int answer = solution(orderList[i]);
            if (answerList[i] == answer) {
                System.out.println("정답 => " + answer);   
            } else {
                System.out.println("오답 => " + answer); 
            }
        }
    }

    public static int solution(int[] order) {
        int answer = 0;
        return answer;

    }
}
