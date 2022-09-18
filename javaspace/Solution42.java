// Codility - StoneWall
import java.util.*;

public class Solution42 {
    public static void main(String[] args) {
        int[] H = {8,8,5,7,9,8,7,4,8};

        System.out.println(solution(H));   
    }

    public static int solution(int[] H) {
        // write your code in Java SE 11
        int blocks = 0;

        Stack<Integer> stack = new Stack<>();

        for (int idx=0; idx<H.length; idx++) {
            // stack이 비어있지 않는 경우
            while (!stack.isEmpty()) {
                // stack의 top이 h보다 큰 경우 stack pop & blocks + 1
                if (stack.peek() > H[idx]) {
                    stack.pop();
                    blocks++;
                // stack의 top이 h보다 작은 경우 stack에 h push & break
                } else if (stack.peek() < H[idx]) {
                    stack.push(H[idx]);
                    break;
                // stack의 top이 h와 같은 경우 그냥 생략(break)
                } else {
                    break;
                }
            }
            
            // stack이 비어있는 경우
            if (stack.isEmpty()) {
                stack.push(H[idx]);
            }
        }
        return blocks + stack.size();
    }
}
