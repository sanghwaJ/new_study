# 프로그래머스 - 게임 맵 최단거리
from collections import deque

def solution(maps):
    global xLen
    xLen = len(maps)
    global yLen
    yLen = len(maps[0])
    
    visited = [[False] * (yLen) for i in range(xLen)]
    
    return bfs(0, 0, maps, visited)

# 최단경로 탐색 문제 => BFS 사용
def bfs(x, y, maps, visited):
    dx = [0,1,0,-1]
    dy = [1,0,-1,0]
    
    queue = deque()
    
    queue.append(Node(x, y, 1))
    visited[x][y] = True
    
    while len(queue) != 0:
        node = queue.popleft()
        
        if node.x == xLen-1 and node.y == yLen-1:
            return node.cost
        
        for i in range(4):
            xx = node.x + dx[i]
            yy = node.y + dy[i]
            
            if xx < 0 or xx > xLen-1 or yy < 0 or yy > yLen-1:
                continue
            
            if visited[xx][yy] == False and maps[xx][yy] == 1:
                visited[xx][yy] = True
                # 주의! 큐 구현시 append + popleft OR appendleft + pop 조합에 맞게 사용 (FIFO)
                queue.append(Node(xx, yy, node.cost + 1))
                
    return -1

class Node:
    def __init__(self, x, y, cost):
        self.x = x
        self.y = y
        self.cost = cost

maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
# maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]

print(solution(maps))