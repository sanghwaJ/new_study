// 프로그래머스 - JadenCase 문자열 만들기
import java.util.*;

public class Solution46 {
    public static void main(String[] args) {
        String s = "3people unFollowed me";
        // String s = "for the last week";

        System.out.println(solution(s));   
    }

    public static String solution(String s) {
        String answer = "";
        
        answer += Character.toUpperCase(s.charAt(0));
        for (int i=0; i<s.length()-1; i++) {
            if(s.charAt(i) == ' ') {
                answer += Character.toUpperCase(s.charAt(i+1));
            } else {
                answer += Character.toLowerCase(s.charAt(i+1));
            }
        }
        
        return answer;
    }
}
