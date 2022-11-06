# 프로그래머스 - 타겟넘버
from collections import deque

# bfs
def solution1(numbers, target):
    answer = 0
    queue = deque()
    
    queue.append([numbers[0], 0])
    queue.append([numbers[0] * -1, 0])
    
    while queue:
        temp, idx = queue.popleft()
        idx += 1
        if idx < len(numbers):
            queue.append([temp + numbers[idx], idx])
            queue.append([temp - numbers[idx], idx])
        else:
            if temp == target:
                answer += 1
    
    return answer

# dfs
def solution2(numbers, target):
    answer = 0
    
    def dfs(idx, result):
        if idx == len(numbers):
            if result == target:
                nonlocal answer
                answer += 1
            return
        else:
            dfs(idx + 1, result + numbers[idx])
            dfs(idx + 1, result - numbers[idx])
            
    dfs(0, 0)
    
    return answer                

numbers = [[1, 1, 1, 1, 1], [4, 1, 2, 1]]
target = [3, 4]
for i in range(len(numbers)):
    print(solution1(numbers[i], target[i]))
    print(solution2(numbers[i], target[i]))
