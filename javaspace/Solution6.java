import java.util.*;

// BOJ - 2480 주사위세개

public class Solution6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer a = sc.nextInt();
        Integer b = sc.nextInt();
        Integer c = sc.nextInt();

        solution(a, b, c);

        sc.close(); 
    }

    public static void solution(Integer a, Integer b, Integer c) {
        if(a == b && a == c && b == c) {
            System.out.print(10000+(a*1000));
        } else if(a == b || a == c) {
            System.out.print(1000+(a*100));
        } else if(b == c) {
            System.out.print(1000+(b*100));
        } else {
            System.out.print((Math.max(a, Math.max(b, c))*100));
        }
    }   
}