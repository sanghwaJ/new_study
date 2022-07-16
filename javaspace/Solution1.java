import java.util.*;

// BOJ - 1541 잃어버린 괄호
public class Solution1 {
    public static void solution1() {
        Scanner sc = new Scanner(System.in); 
        String temp = sc.nextLine(); 
        
        String[] str = temp.split("\\-"); 

        int answer = 0; 
        for (int i = 0; i < str.length; i++) { 
            String[] subStr = str[i].split("\\+"); 
            int calc = 0; 
            for (String item : subStr) { 
                calc += Integer.parseInt(item); 
            } 
            if (i == 0) { 
                answer += calc; 
            } else { 
                answer -= calc; 
            }
        } 

        System.out.println(answer); 
        sc.close(); 
    }
}

