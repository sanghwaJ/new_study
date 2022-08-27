// 프로그래머스 - 가장 큰 수
import java.util.*;

public class Solution30 {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};

        System.out.println(solution(numbers));   
    }

    public static String solution(int[] numbers) {
        // String[]으로 변환
        String[] strN = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            strN[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(strN, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // o1+o2, o2+o1 중 비교하여 더 큰 수를 return (내림차순 정렬)
                return (o2+o1).compareTo(o1+o2);
            }
        });

        if (strN[0].equals("0")) {
            return "0";
        } else {
            // 정렬된 strN Array의 모든 요소 join하여 return
            return String.join("", strN);
        }
    }
}
