# BOJ - 2484 주사위 네개

N = int(input())

def solution(N):
    answer = 0
    for i in range(N):
        dices_list = sorted(list(map(int, input().split())))
        dices_set = set(dices_list)
        
        if len(dices_set) == 1:
            answer = max(answer, 50000 + dices_list[0] * 5000)
        elif len(dices_set) == 2:
            if dices_list[1] == dices_list[2]:
                answer = max(answer, 10000 + dices_list[1] * 1000)
            else:
                answer = max(answer, 2000 + dices_list[0] * 500 + dices_list[2] * 500)
        elif len(dices_set) == 3:
            temp = 0
            for i in range(len(dices_list)):
                if dices_list[i] == dices_list[i+1]:
                    _temp = dices_list[i]
                    break
            answer = max(answer, 1000 + _temp * 100)
        else:
            answer = max(answer, max(dices_list) * 100)
    return answer

solution(N)