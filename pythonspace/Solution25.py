# 프로그래머스 - 네트워크
from collections import deque

# bfs
def solution1(n, computers):
    queue = deque()
    visited = [False for i in range(len(computers))]
    
    def bfs(start):
        visited[start] = True
        queue.append(start)
        while queue:
            q = queue.popleft()    
            for i in range(n):
                if computers[q][i] == 1 and not visited[i]:
                    visited[i] = True
                    queue.append(i)
    
    answer = 0
    for i in range(n):
        if not visited[i]:
            bfs(i)
            answer += 1
    
    return answer

# dfs
def solution2(n, computers):
    visited = [False for i in range(len(computers))]
    
    def dfs(i):
        visited[i] = True
        for j in range(n):
            if computers[i][j] == 1 and not visited[j]:
                dfs(j)
    
    answer = 0        
    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer += 1
    
    return answer

n = [3, 3]
computers = [[[1, 1, 0], [1, 1, 0], [0, 0, 1]], [[1, 1, 0], [1, 1, 1], [0, 1, 1]]]

for i in range(len(n)):
    print(solution1(n[i], computers[i]))
    print(solution2(n[i], computers[i]))
    