# 프로그래머스 - 압축

def solution(msg):
    alphaDict = {chr(65+i) : i+1 for i in range(26)}
    
    alphaIdx = 27
    startIdx, endIdx = 0, 1
    
    answer = []
    while endIdx < len(msg) + 1:
        divMsg = msg[startIdx:endIdx]
        if divMsg in alphaDict.keys():
            endIdx += 1
        else:
            # divMsg가 alphaDict에 없다는 건 마지막 알파벳을 제외한 divMsg는 alphaDict에 있다는 것
            answer.append(alphaDict[divMsg[:-1]])
            # alphaDict에 divMsg 추가
            alphaDict[divMsg] = alphaIdx
            alphaIdx += 1    
            # 처리 완료된 divMsg는 Jump
            startIdx = endIdx - 1
    # 결국 맨 마지막 한자리 알파벳만 남게되는데, 이 idx를 추가해야 함
    answer.append(alphaDict[divMsg])
            
    return answer
msg = "TOBEORNOTTOBEORTOBEORNOT"
print(solution(msg))