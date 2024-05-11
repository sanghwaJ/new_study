// 프로그래머스 - 주식가격

import java.util.*;
import java.util.stream.IntStream;

public class Solution57 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};

        System.out.println(Arrays.toString(solution1(prices)));
        System.out.println(Arrays.toString(solution2(prices)));
        System.out.println(Arrays.toString(solution3(prices)));
    }

    // 이중 for문 사용
    public static int[] solution1(int[] prices) {
        int pricesLen = prices.length;
        int[] answer = new int[pricesLen];

        for (int i = 0; i < pricesLen; i++) {
            for (int j = i + 1; j < pricesLen; j++) {
                answer[i]++;

                if (prices[i] > prices[j]) break;
            }
        }
        return answer;
    }

    // IntStream 사용
    public static int[] solution2(int[] prices) {
        int pricesLen = prices.length;
        int[] answer = new int[pricesLen];

        IntStream.range(0, pricesLen)
                .forEach(i -> {
                    answer[i] = (int) IntStream.range(i + 1, pricesLen)
                            .takeWhile(j -> prices[i] <= prices[j])
                            .count();
                });

        return answer;
    }


    // stack 사용
    public static int[] solution3(int[] prices) {
        int pricesLen = prices.length;
        int[] answer = new int[pricesLen];
        Stack<Integer[]> stack = new Stack<>();

        for (int i = 0; i < pricesLen; i++) {
            answer[i] = pricesLen - 1 - i; // 가능한 최대 기간
            Integer[] arr = {i, prices[i]};

            // 가격이 떨어진 경우
            while (!stack.empty() && stack.peek()[1] > prices[i]) {
                Integer[] price = stack.pop();
                answer[price[0]] = i - price[0]; // price[0]은 인덱스
            }

            stack.push(arr);
        }

        return answer;
    }
}
