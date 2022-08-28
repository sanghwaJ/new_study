// 프로그래머스 - 모음사전
import java.util.*;

public class Solution33 {
    public static void main(String[] args) {
        String word = "EIO";

        System.out.println(solution(word));   
    }

    public static int solution(String word) {
        String temp = "AEIOU";
        // 1번째 자리가 바뀌려면 idx+781
        // 2번째 자리가 바뀌려면 idx+156
        // 3번째 자리가 바뀌려면 idx+31
        // 4번째 자리가 바뀌려면 idx+6
        // 5번째 자리가 바뀌려면 idx+1
        int[] increase = {781, 156, 31, 6, 1};
        
        int answer = word.length();
        for (int i=0; i<word.length(); i++) {
            answer += (increase[i] * temp.indexOf(word.charAt(i)));
        }
       
        return answer;
    }
}
