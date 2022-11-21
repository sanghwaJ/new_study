// 프로그래머스 - 불량 사용자
import java.util.*;

public class Solution53 {
    public static void main(String[] args) {
        String[][] userIdList = {{"frodo", "fradi", "crodo", "abc123", "frodoc"}
                                , {"frodo", "fradi", "crodo", "abc123", "frodoc"}
                                , {"frodo", "fradi", "crodo", "abc123", "frodoc"}};
        String[][] bannedIdList = {{"fr*d*", "abc1**"}, {"*rodo", "*rodo", "******"}, {"fr*d*", "*rodo", "******", "******"}};
        int[] answerList = {2, 2, 3};
        
        for (int i=0; i<userIdList.length; i++) {
            int answer = solution(userIdList[i], bannedIdList[i]);
            
            if (answer == answerList[i]) {
                System.out.println("정답 => " + answer);
            } else {
                System.out.println("오답 => " + answer);
            }
        }
    }

    public static HashSet<HashSet<String>> answer = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();
        // 순서를 기억해야 하기 때문에 LinkedHashSet 사용
        dfs(new LinkedHashSet<>(), user_id, banned_id);
        return answer.size();
    }

    public static void dfs(LinkedHashSet<String> set, String[] user_id, String[] banned_id) {
        if (set.size() == banned_id.length) {
            if (banCheck(set, banned_id)) {
                answer.add(new HashSet<>(set));
            }
            return;
        }
        
        for (int i=0; i<user_id.length; i++) {
            if (!set.contains(user_id[i])) {
                set.add(user_id[i]);
                dfs(set, user_id, banned_id);
                set.remove(user_id[i]);
            }
        }
    }

    public static boolean banCheck(HashSet<String> set, String[] banned_id) {
        int idx = 0;
        boolean check = true;

        for (String userId : set) {
            String banId = banned_id[idx++];

            if (userId.length() != banId.length()) {
                check = false;
                break;
            } else {
                for (int i=0; i<banId.length(); i++) {
                    if (banId.charAt(i) != '*') {
                        if (userId.charAt(i) != banId.charAt(i)) {
                            check = false;
                            break;
                        }
                    }
                }
            }
        }

        return check;
    }
}
