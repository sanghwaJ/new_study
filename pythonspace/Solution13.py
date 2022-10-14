# 프로그래머스 - 카펫
def solution(brown, yellow):
    b = int(brown / 2) + 2
    
    return_list = []
    for i in range(3, b):
        return_list.append([i, b-i])
        
    answer = []
    for j in return_list:
        if (j[0] - 2) * (j[1] - 2) == yellow:
            answer.append(j)
            
    answer.sort(reverse = True)
     
    return answer[0]