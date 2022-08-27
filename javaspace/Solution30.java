// 프로그래머스 - 가장 큰 수
import java.util.*;
import java.util.Collections;

public class Solution30 {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};

        System.out.println(solution(numbers));   
    }

    public static int solution(int[] numbers) {
        /* 
        String[] strs = {"AA", "CB", "AB", "CA", "AC", "BA", "BB", "BC", "CC"};
        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1).compareTo(o2);
            }			
        });
        */
        String[] strs = {"AA", "CB", "AB", "CA", "AC", "BA", "B", "BC", "CC"};
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // ASC
                return (o1).compareTo(o2);
                // DESC
                // return (o2).compareTo(o1);
            }
        });
        System.out.println(Arrays.toString(strs));

        String[] strNums = new String[numbers.length];
        for (int i=0; i<numbers.length; i++){
            strNums[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(strNums, Collections.reverseOrder());
        System.out.println(Arrays.toString(strNums));
        
        

        return 0;
    }

}
