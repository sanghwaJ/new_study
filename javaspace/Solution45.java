// 프로그래머스 - 전력망을 둘로 나누기
import java.util.*;

public class Solution45 {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        //int n = 4;
        //int[][] wires = {{1,2},{2,3},{3,4}};

        //int n = 7;
        //int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};

        System.out.println(solution(n, wires));   
    }

    public static int solution(int n, int[][] wires) {
        int answer = -1;
        return answer;
    }
}
