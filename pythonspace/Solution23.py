# 1260번 DFS와 BFS
from collections import deque

# dfs를 재귀함수로 구현
def dfs(v):
    print(v, end = ' ')
    visited[v] = True
    for e in adj[v]:
        # 방문하지 않았다면(False)
        if not visited[e]:
            dfs(e)

def bfs(v):
    q = deque([v])
    while q:
        v = q.popleft()
        if not visited[v]:
            visited[v] = True
            print(v, end = ' ')
            for e in adj[v]:
                # 방문하지 않았다면(False)
                if not visited[e]:
                    q.append(e)

n, m, v = map(int, input().split())

# 인접 리스트 : 인접된 정점들을 묶은 리스트
# n + 1만큼 반복문을 돌리는 이유는 리스트 인덱스가 0부터 시작하기 때문
adj = [[] for _ in range(n + 1)]

for _ in range(m):
    x, y = map(int, input().split())
    adj[x].append(y)
    adj[y].append(x)

# 정점 번호가 작은 것 부터 방문하기 때문에, 정점 정렬
for e in adj:
    e.sort()

# 한 번 방문한 정점은 다시 방문하지 않도록 visited로 관리
visited = [False] * (n + 1)
dfs(v)
print()
visited = [False] * (n + 1)
bfs(v)