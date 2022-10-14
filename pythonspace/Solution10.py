# Softeer - 장애물 인식 프로그램

import sys
# input = sys.stdin.readline

def dfs(x, y):
    if x < 0 or x >= N or y < 0 or y >= N:
        return False
    if blocks[x][y] == 1:
        cnt.append(1)
        blocks[x][y] = 0
        
        dfs(x, y + 1)
        dfs(x + 1, y)
        dfs(x, y - 1)
        dfs(x - 1, y)
        return True
    return False

N = int(input())

blocks = []
for i in range(N):
    blocks.append(list(map(int, input())))

cnt = []
all_cnt = 0
answers = []
for i in range(N):
    for j in range(N):
        if dfs(i, j) == True:
            all_cnt += 1
            answers.append(len(cnt))
            cnt = []

print(all_cnt)

answers.sort()
for answer in answers:
    print(answer)