import java.util.*;
import java.lang.Math;

// Codility - nTest1
public class Solution26 {
    public static void main(String[] args) {
        int[] A = {5112,7311,1427,4112,2141,2151,1232};
        //int[] A = {51,71,17,42};
        //int[] A = {42,33,60};
        System.out.println(solution(A));        
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        if (A.length == 1) {
            return -1;
        }
        
        // array 내림차순 sort
        Integer[] integerArr = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(integerArr, Collections.reverseOrder());
        int[] sortA = Arrays.stream(integerArr).mapToInt(Integer::intValue).toArray(); 
        
        // map 생성
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<A.length; i++) {
            map.put(sortA[i], digitsSum(sortA[i]));
        }

        // value로 정렬된 list map 생성
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((o1, o2) -> map.get(o1.getKey()) - map.get(o2.getKey()));
        
        // logic start
        int tempAns = 0;
        int answer = -1;
        for (int j=0; j<A.length-1; j++) {
            if (entryList.get(j).getValue().equals(entryList.get(j+1).getValue())) {
                tempAns = entryList.get(j).getKey() + entryList.get(j+1).getKey();
                answer = Math.max(tempAns, answer);
            } 
        }
        
        
        return answer;
    }

    // 각 자리수 합 구하는 함수
    public static int digitsSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num%10;
            num = num/10;
        }
        return sum;
    }
}
