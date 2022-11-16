// 프로그래머스 - 햄버거 만들기
import java.util.*;

public class Solution52 {
    public static void main(String[] args) {
        int[][] ingredientArray = {{2, 1, 1, 2, 3, 1, 2, 3, 1}, {1, 3, 2, 1, 2, 1, 3, 1, 2}};
        int[] answerList = {2, 0};
        
        for (int i=0; i<ingredientArray.length; i++) {
            int answer = solution(ingredientArray[i]);
            if (answerList[i] == answer) {
                System.out.println("정답 => " + answer);   
            } else {
                System.out.println("오답 => " + answer); 
            }
        }
    }

    public static int solution(int[] ingredient) {
        ArrayList<Integer> burger = new ArrayList<>();
        int answer = 0;
        int startBread, vegi, gogi, endBread;

        for (int ingre : ingredient) {
            burger.add(ingre);

            if (burger.size() >= 4) {
                endBread = burger.get(burger.size()-1);
                if (endBread == 1) {
                    startBread = burger.get(burger.size()-4);
                    vegi = burger.get(burger.size()-3);
                    gogi = burger.get(burger.size()-2);

                    if (startBread == 1 && vegi == 2 && gogi == 3) {
                        burger.remove(burger.size()-1);
                        burger.remove(burger.size()-1);
                        burger.remove(burger.size()-1);
                        burger.remove(burger.size()-1);

                        answer += 1;
                    }
                }
            }
        }

        return answer;
    }
}
