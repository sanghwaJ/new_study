// 프로그래머스 - 택배상자
import java.util.*;

public class Solution51 {
    public static void main(String[] args) {
        int[][] orderList = {{4, 3, 1, 2, 5, 6}, {5, 4, 3, 2, 1}};
        int[] answerList = {2, 5};
        
        for (int i=0; i<orderList.length; i++) {
            int answer = solution1(orderList[i]);
            if (answerList[i] == answer) {
                System.out.println("정답 => " + answer);   
            } else {
                System.out.println("오답 => " + answer); 
            }
        }
    }

    public static int solution1(int[] order) {
        Queue<Integer> beltQueue = new LinkedList<>();
        Stack<Integer> subBeltStack = new Stack<>();

        for (int i=0; i<order.length; i++) {
            beltQueue.offer(i+1);
        }

        int idx = 0;
        while (!beltQueue.isEmpty()) {
            if (order[idx] == beltQueue.peek()) {
                idx++;
                beltQueue.poll();
            } else {
                if (!subBeltStack.isEmpty() && subBeltStack.peek() == order[idx]) {
                    idx++;
                    subBeltStack.pop();
                } else {
                    subBeltStack.push(beltQueue.poll());
                }
            }
        }

        // subBelt 처리
        while (!subBeltStack.isEmpty()) {
            if (order[idx] == subBeltStack.peek()) {
                idx++;
                subBeltStack.pop();
            } else {
                break;
            }
        }

        return idx;
        
    }

    // 다른 풀이
    public static int solution2(int[] order) {
        Stack<Integer> subBeltStack = new Stack<Integer>();
        int idx = 0;
        int targetNum = 1;
        int answer = 0;
        
        while (true) {
            if (!subBeltStack.isEmpty() && order[idx] == subBeltStack.peek()) {
                subBeltStack.pop();
                idx++;
                answer++;
                continue;
            }

            if (targetNum > order.length) {
                break;
            }
                
            if (order[idx] == targetNum) {
                idx++;
                targetNum++;
                answer++;
            } else {
                subBeltStack.push(targetNum);
                targetNum++;
            }
        }

        return answer;
    }
}
