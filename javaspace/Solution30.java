// 프로그래머스 - 피로도
import java.util.*;

public class Solution30 {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};

        System.out.println(solution(numbers));   
    }

    public static int solution(int[] numbers) {

        /* 
        Integer[] integerArr = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(integerArr,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        */
        Integer[] integerArr = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Collections.sort(integerArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //o1보다 o2의 넘버가 크다면 no기준 오름차순 정렬
                if(o1.getNo()<o2.getNo()) { //1,2,3
                    return -1;
                } else if(o1.getNo()>o2.getNo()){
                    return 1;
                } else {
                    return 0;				
                }			
            }			
        });
        


        for (Integer i : integerArr) {
            System.out.println(i);
        }

        return 0;
    }

}
