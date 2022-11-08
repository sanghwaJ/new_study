// 프로그래머스 - 베스트 앨범
import java.util.*;

public class Solution47 {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 500, 800, 2500};
        
        String solution = Arrays.toString(solution(genres, plays));
        System.out.println(solution);   
    }

    public static int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        
        HashMap<String, Integer> genreMap = new HashMap<>();
        Song[] songs = new Song[len];   
        for (int i=0; i<len; i++) {
            if (genreMap.containsKey(genres[i])) {
                genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]);
            } else {
                genreMap.put(genres[i], plays[i]);
            }
            songs[i] = new Song(i, genres[i], plays[i]);
        }
        Arrays.sort(songs);

        ArrayList<String> orderedGenres = new ArrayList<>();
        while(genreMap.size() != 0) {
            int maxPlay = 0;
            String maxGenre = "";
            for (String key : genreMap.keySet()) {
                int tempPlay = genreMap.get(key);
                if (tempPlay > maxPlay) {
                    maxPlay = tempPlay;
                    maxGenre = key;
                }
            }
            orderedGenres.add(maxGenre);
            genreMap.remove(maxGenre);
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        for (String og : orderedGenres) {
            int cnt = 0;
            for (Song song : songs) {
                if (og.equals(song.genre)) {
                    answer.add(song.idx);
                    cnt += 1;
                    if (cnt == 2) break;
                }
            }
        }
        
        return answer.stream().mapToInt(i->i).toArray();
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
        // 즉, 아래의 메소드는 Arrays.sort의 compareTo 기능을 오버라이딩하여 재정의한 것
        @Override
        public int compareTo(Song s) {
            if (this.play != s.play) {
                return s.play - this.play;
            } else {
                return this.idx - s.idx;
            }
        }
    }
}
