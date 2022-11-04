# 프로그래머스 - 파일명 정렬

def solution(files):
    answer = []
    for file in files:
        HEAD, NUMBER, TAIL = "", "", ""
        for i in range(len(file)):
            if file[i].isdigit():
                HEAD = file[:i]
                NUMBER = file[i:]
                for j in range(len(NUMBER)):
                    if not NUMBER[j].isdigit():
                        TAIL = NUMBER[j:]
                        NUMBER = NUMBER[:j]
                        break
                answer.append((HEAD, NUMBER, TAIL))
                break

    answer.sort(key=lambda x : (x[0].lower(), int(x[1])))        

    return ["".join(a) for a in answer]
    
files = ["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]
#files = ["F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"]

print(solution(files))