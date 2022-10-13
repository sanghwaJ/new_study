// 프로그래머스 - 베스트 앨범
import java.util.*;

public class Solution47 {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        
        String solution = Arrays.toString(solution(genres, plays));
        System.out.println(solution);   
    }

    public static int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        
        Song[] songs = new Song[len];
        for (int i=0; i<len; i++) {
            songs[i] = new Song(i, genres[i], plays[i]);
        }
        Arrays.sort(songs);

        /*
        Arrays.sort(songs, new Comparator<int[]>() {
             @Override
             public int compare(int[] s1, int[] s2) {
                 if (s1[0]==s1[0]){
                    return s1[0]-s1[0];
                 }
                 else{
                    return s1.play-s2.play;
                 }
             }
        });
         */
        
        for (int i=0; i<len; i++) {
            System.out.println(songs[i].idx + " " + songs[i].genre + " " + songs[i].play);
        }
       
        
        
        int[] answer = {};
        return answer;
    }

    public static class Song implements Comparable<Song> {
        int idx;
        String genre; 
        int play; 

        public Song(int idx, String genre, int play) {
            this.idx = idx;
            this.genre = genre;
            this.play = play;
        }

        // 오버라이딩 후 Arrays.sort를 사용하면 됨
        @Override
        public int compareTo(Song s) {
            return this.play - s.play;
        }

        // 2차원 Array 정렬
        /* 
        @Override
        public int compareTo(Node o) {
           int result = Integer.compare(this.weight1, o.weight1);
           
            if(result == 0) { 
                result = Integer.compare(this.weight2, o.weight2);
            }

           return result;
        }
        */
    }
}
