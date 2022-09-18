// Codility - Nesting
import java.util.*;

public class Solution41 {
    public static void main(String[] args) {
        String S = "(()(())())";

        System.out.println(solution(S));   
    }

    public static int solution(String S) {
        // write your code in Java SE 11
        Stack<Character> stack = new Stack<>();
        char charOfS;

        for (int idx=0; idx<S.length(); idx++) {
            charOfS = S.charAt(idx);

            if (stack.isEmpty()) {
                stack.push(charOfS);
                continue;
            }

            if ('(' == stack.peek() && ')' == charOfS) {
                stack.pop();
            } else {
                stack.push(charOfS);
            }
        }

        return stack.size() > 0 ? 0 : 1;
    }
}
