import java.util.*;

// 프로그래머스 - 폰캣몬
public class Solution28 {
    public static void main(String[] args) {
        int[] nums = {3, 3, 3, 2, 2, 2};
    }

    public static int solution(int[] nums) {
        int targetN = nums.length / 2;
        
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int targetSet = set.size();
        
        if (targetSet >= targetN) {
            return targetN;
        } else {
            return targetSet;
        }
    }
}
