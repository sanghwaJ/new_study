# 프로그래머스 - JadenCase 문자열 만들기

def solution(s):
    answer = ""
    
    answer += s[0].upper()
    for i in range(len(s)-1):
        if s[i] == " ":
            answer += s[i+1].upper()
        else:
            answer += s[i+1].lower()
        
    return answer

s = "3people unFollowed me"
#s = "for the last week"

print(solution(s))