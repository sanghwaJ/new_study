// 프로그래머스 - 같은 숫자는 싫어
import java.util.*;

public class Solution34 {
    public static void main(String[] args) {
        int[] arr = {1,1,3,3,0,1,1};

        System.out.println(solution(arr));   
    }

    public static int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        
        list.add(arr[0]);
        for (int i=0; i<arr.length-1; i++) {
            if(arr[i] != arr[i+1]) {
                list.add(arr[i+1]);
            } 
        }
        
        int[] answer = list.stream().mapToInt(i->i).toArray();
        
        return answer;
    }
}
