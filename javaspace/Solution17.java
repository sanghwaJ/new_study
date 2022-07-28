import java.util.*;

// Codility - Brackets
public class Solution17 {
    public static void main(String[] args) {
        String S = "([)()]";
        
        System.out.println(solution(S));
    }

    public static int solution(String S) {
        // write your code in Java SE 11
        Stack<Character> stack = new Stack<>();

        for (char c : S.toCharArray()) {
            if ('(' == c || '{' == c || '[' == c ) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return 0;
                }
                if (')' == c && '(' != stack.pop()) {
                    return 0;
                } else if ('}' == c && '{' != stack.pop()) {
                    return 0;
                } else if (']' == c && '[' != stack.pop()) {
                    return 0;
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
