# 프로그래머스 - 위장

def solution(clothes):
    clothesDict = dict()
    
    for cloth in clothes:
        if cloth[1] in clothesDict:
            clothesDict[cloth[1]] += 1
        else:
            clothesDict[cloth[1]] = 1
            
    answer = 1
    for cdVal in clothesDict.values():
        answer *= (cdVal + 1)
    
    return answer - 1