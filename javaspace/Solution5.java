import java.util.*;

// 프로그래머스 - 전화번호 목록
public class Solution5 {
    public static void main(String[] args) {
        //String[] phone_book = {"119", "97674223", "1195524421"};
        String[] phone_book = {"123","456","789"};
        //String[] phone_book = {"12","123","1235","567","88"};
        System.out.println(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        boolean answer = true;
        System.out.println(phone_book[1]);

        for(int i = 0; i < (phone_book.length - 1); i++){
            if(phone_book[i+1].startsWith(phone_book[i])){
                answer = false;
                break;
            }
        }

        return answer;
    }    
}