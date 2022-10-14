# BOJ - 2480 주사위 세개

dices = sorted(list(map(int, input().split())))

def solution(dices):
    if len(set(dices)) == 1:
        return 10000 + dices[0] * 1000
    elif len(set(dices)) == 2:
        return 1000 + dices[1] * 100
    else:
        return max(dices) * 100

solution(dices)