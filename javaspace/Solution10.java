import java.util.*;

// Codility - FrogJmp
public class Solution10 {
    public static void main(String[] args) {
        int X = 10;
        int Y = 85;
        int D = 20;

        System.out.println(solution(X, Y, D));
    }

    public static int solution(int X, int Y, int D) {
        // write your code in Java SE 11
        int answer = 0;
        
        if (X <= Y) {
            double temp = (Y - X) / (double) D;
            if (temp > (int) temp) {
                answer = (int) temp + 1;
            } else {
                answer = (int) temp;
            }
            return answer;
        } 
        return answer;
    }
}
