// Codility - EquiLeader
import java.util.*;
import java.lang.Math;

public class Solution43 {
    public static void main(String[] args) {
        int[] A = {4,3,4,4,4,2};

        System.out.println(solution(A));   
    }

    public static int solution(int[] A) {
        // write your code in Java SE 11
        if (A.length == 1) {
            return 0;
        }

        // array 요소별 카운트 기록 + 가장 많이 나온 수 Leader 지정
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int leader = 0;
        int numCount = 0;
        for (int i=0; i<A.length; i++) {
            if(countMap.containsKey(A[i])) {
                countMap.replace(A[i], countMap.get(A[i]) + 1);
            } else {
                countMap.put(A[i], 1);
            }

            if(countMap.get(A[i]) > numCount) {
                leader = A[i];
                numCount = countMap.get(A[i]);
            }
        }

        // 각 인덱스 위치까지의 leader의 갯수 기록
        ArrayList<Integer> leaderCntList = new ArrayList<>();
        int leaderCount = 0;
        for (int i=0; i<A.length; i++) {
            if (A[i] == leader) {
                leaderCount++;
            }
            leaderCntList.add(leaderCount);
        }

        // answer 구하는 함수
        int answer = 0;
        for (int i=0; i<A.length-1; i++) {
            int leftDivCnt = leaderCntList.get(i);
            int rightDivCnt = leaderCntList.get(A.length-1) - leftDivCnt;

            int equiLeft = ((i + 1) / 2) + 1;
            int equiRight = (((A.length) - (i + 1)) / 2) + 1;
            
            // equi leader 조건을 모두 만족시키는 경우 answer+1
            if (leftDivCnt >= equiLeft && rightDivCnt >= equiRight) {
                answer++;
            }
        }
        
        /* Vector로 구하는 방법 (멀티쓰레딩이 가능하다는 장점이 있으나 지양하자)
        // 각 인덱스 위치까지의 leader의 갯수 기록
        Vector<Integer> leaderCntList = new Vector<>();
        int leaderCount = 0;
        for (int i=0; i<A.length; i++) {
            if (A[i] == leader) {
                leaderCount++;
            }
            leaderCntList.add(leaderCount);
        }

        // answer 구하는 함수
        int answer = 0;
        for (int i=0; i<A.length-1; i++) {
            int leftDivCnt = leaderCntList.elementAt(i);
            int rightDivCnt = leaderCntList.lastElement() - leftDivCnt;

            int equiLeft = ((i + 1) / 2) + 1;
            int equiRight = (((A.length) - (i + 1)) / 2) + 1;
   
            // equi leader 조건을 모두 만족시키는 경우 answer+1
            if (leftDivCnt >= equiLeft && rightDivCnt >= equiRight) {
                answer++;
            }
        }
        */
        return answer;
    }
}
