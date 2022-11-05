# 프로그래머스 - 2 x n 타일링

def solution1(n):
    ansList = []
    for i in range(n):
        if i == 0:
            ansList.append(1)
        elif i == 1:
            ansList.append(2)
        else:
            # 미리 계산을 해두고 append 하는 것이 더 효율적인 방법
            ansList.append((ansList[i-2] + ansList[i-1]) % 1000000007)
        
    return ansList[-1]

# 더 깔끔한 방법
def solution2(n):
    ansList = [0] * 60001
    ansList[1] = 1
    ansList[2] = 2
    for i in range(3,n+1):
        ansList[i] = (ansList[i-1] + ansList[i-2]) % 1000000007
    return ansList[n]

n = 100
print(solution1(n))
print(solution2(n))