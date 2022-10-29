// 프로그래머스 - 최소직사각형
import java.util.*;

public class Solution32 {
    public static void main(String[] args) {
        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};

        System.out.println(solution(sizes));   
    }

    public static int solution(int[][] sizes) {
        int maxW = 0;
        int maxH = 0;
        
        for (int i=0; i<sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                maxW = Math.max(sizes[i][1], maxW);
                maxH = Math.max(sizes[i][0], maxH);
            } else {
                maxW = Math.max(sizes[i][0], maxW);
                maxH = Math.max(sizes[i][1], maxH);
            }
        }

        return maxW * maxH;
    }
}
