// 프로그래머스 - 압축
import java.util.*;

public class Solution48 {
    public static void main(String[] args) {
        String[] msgList = {"KAKAO", "TOBEORNOTTOBEORTOBEORNOT", "ABABABABABABABAB"};
        // int[][] answerList = {{1, 1, 27, 15}, {20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34}, {1, 2, 27, 29, 28, 31, 30}};
        
        for (String msg : msgList) {
            System.out.println(Arrays.toString(solution(msg)));   
        } 
    }

    public static int[] solution(String msg) {
        HashMap<String, Integer> alphaMap = new HashMap<>();
        for (int i=1; i<27; i++) {
            char alphabet = (char) (64+i);
            alphaMap.put(Character.toString(alphabet), i);
        }

        int idx = 27;
        int startIdx = 0;
        int endIdx = 1;
        String divMsg = "";
        ArrayList<Integer> answer = new ArrayList<>();
        
        while (endIdx < msg.length() + 1) {
            divMsg = msg.substring(startIdx, endIdx);
            if (alphaMap.containsKey(divMsg)) {
                endIdx++;
            } else {
                answer.add(alphaMap.get(divMsg.substring(0, divMsg.length()-1)));
                alphaMap.put(divMsg, idx);
                idx++;
                startIdx = endIdx - 1;
            }
        }
        answer.add(alphaMap.get(divMsg));

        return answer.stream().mapToInt(i->i).toArray();
    }
}
