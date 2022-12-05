// 프로그래머스 - 단속카메라
import java.util.*;

public class Solution56 {
    public static void main(String[] args) {
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        
        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        
        int answer = 0;
        int camera = -30001;

        for (int[] route : routes) {
            if (camera < route[0]) {
                answer += 1;
                camera = route[1];
            }
        }
        return answer;
    }
}
