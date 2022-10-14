# 프로그래머스 - 베스트 앨범

#from collections import defaultdict

# 클래스 이용
def solution1(genres, plays):
    playCnt = {}
    songList = []
    for i in range(len(genres)):
        if genres[i] not in playCnt:
            playCnt[genres[i]] = plays[i]
        else:
            playCnt[genres[i]] += plays[i]
        song = Song(i, genres[i], plays[i])
        songList.append(song)
        
    playCnt = sorted(playCnt.items(), key=lambda x : -x[1])
    songList = sorted(songList, key=lambda song : (-song.play, song.idx))
    
    answer = []
    for i in range(len(playCnt)):
        cnt = 0
        for j in range(len(songList)):
            if playCnt[i][0] == songList[j].genre:
                answer.append(songList[j].idx)
                cnt += 1
                if cnt == 2:
                    break
                
    return answer

class Song:
    def __init__(self, idx, genre, play):
        self.idx = idx
        self.genre = genre
        self.play = play
        
# 클래스 이용하지 않음
def solution2(genres, plays):
    playCnt = {}
    songList = []
    for i in range(len(genres)):
        if genres[i] not in playCnt:
            playCnt[genres[i]] = plays[i]
        else:
            playCnt[genres[i]] += plays[i]
        songList.append([i, genres[i], plays[i]])
        
    playCnt = sorted(playCnt.items(), key=lambda x : -x[1])
    songList = sorted(songList, key=lambda x : (-x[2], x[0]))
    
    answer = []
    for i in range(len(playCnt)):
        cnt = 0
        for j in range(len(songList)):
            if playCnt[i][0] == songList[j][1]:
                answer.append(songList[j][0])
                cnt += 1
                if cnt == 2:
                    break
                
    return answer


genres = ["classic", "pop", "classic", "classic", "pop"]
plays = [500, 600, 500, 800, 2500]

print(solution1(genres, plays))
print(solution2(genres, plays))