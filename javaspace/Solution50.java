// 프로그래머스 - 캐시
import java.util.*;

public class Solution50 {
    public static void main(String[] args) {
        int[] cacheSize = {3, 3, 2, 5, 2, 0};
        String[][] cities = {
                            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"},
                            {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"},
                            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"},
                            {"Jeju", "Pangyo", "NewYork", "newyork"},
                            {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}
                        };
        int[] answerList = {50, 21, 60, 52, 16, 25};
        
        
        for (int i=0; i<cacheSize.length; i++) {
            int answer = solution(cacheSize[i], cities[i]);
            if (answerList[i] == answer) {
                System.out.println("정답 => " + answer);   
            } else {
                System.out.println("오답 => " + answer); 
            }
        }
    }

    public static int solution(int cacheSize, String[] cities) {
        ArrayList<String> cache = new ArrayList<>();

        int answer = 0;
        for (String city : cities) {
            city = city.toLowerCase();
            if (cache.contains(city)) {
                answer++;
                cache.remove(cache.indexOf(city));
                cache.add(city);
            } else {
                answer += 5;
                if (cache.size() < cacheSize) {
                    cache.add(city);
                } else if (cacheSize != 0 && cache.size() == cacheSize) {
                    cache.remove(0);
                    cache.add(city);
                }
            }
        }

        return answer;
    }
}
