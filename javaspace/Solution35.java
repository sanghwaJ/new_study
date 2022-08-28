// 프로그래머스 - 올바른 괄호
import java.util.*;

public class Solution35 {
    public static void main(String[] args) {
        String s = "(())()";

        System.out.println(solution1(s));   
        System.out.println(solution2(s));   
    }

    // stack을 사용하였으나, 시간초과
    public static boolean solution1(String s) {
        boolean answer = true;
        
        Stack st = new Stack();

        String[] strArr = s.split("");
        for (String str : strArr) {
            if ("(".equals(str)) {
                st.push(str);
            } else if (")".equals(str) && !st.isEmpty() && "(".equals(st.peek())){
                st.pop();
            } else {
                answer = false;
                break;
            }
        }
        
        if (!st.isEmpty()) {
            answer = false;
        }
        
        return answer;
    }

    // stack 사용 개선 함수
    public static boolean solution2(String s) {
        boolean answer = true;
        
        // 개선점 1 : char 타입 사용
        Stack<Character> st = new Stack<Character>();

        // 개선점 2 : 문자열을 split()하고 순회하는 것이 아니라, charAt()을 활용하여 바로 순회
        for (int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                st.push('(');
            } else {
                // 개선점 3 : '('이 아닌 ')'이 들어왔는데, 빈 stack이라면 바로 false return
                if (st.isEmpty()) {
                    answer = false;
                    return answer;
                } else {
                    st.pop();
                }
            }
        }
        
        if (!st.isEmpty()) {
            answer = false;
        }
        
        return answer;
    }
}
