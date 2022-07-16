import java.util.*;

// 프로그래머스 - 위장
public class Solution4 {
    public static void main(String[] args) {
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        //String[][] clothes = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String[] i : clothes){
            if(!map.containsKey(i[1])){
                map.put(i[1], 1);
            } else {
                map.put(i[1], map.get(i[1]) + 1);
            }
        }

        int answer = 1;
        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        answer -= 1;

        return answer;
    }    
}