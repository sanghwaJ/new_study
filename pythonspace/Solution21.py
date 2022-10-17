# 프로그래머스 - 게임 맵 최단거리 => 개발중 => 다시풀어보기

from collections import deque

def solution(maps):
    global xLen
    xLen = len(maps)
    global yLen
    yLen = len(maps[0])
    global dx
    dx = [0,1,0,-1]
    global dy
    dy = [1,0,-1,0]
    
    visited = [[False] * (yLen) for i in range(xLen)]
    
    bfs(0, 0, maps, visited)

    return bfs(0, 0, maps, visited)

# 최단경로 탐색 문제 => BFS 사용
def bfs(x, y, maps, visited):
    queue = deque()
    queue.appendleft(Node(x, y, 1))
    visited[x][y] = True
    
    while queue:
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
                queue.appendleft(Node(xx, yy, node.cost + 1))
                
        return -1

class Node:
    def __init__(self, x, y, cost):
        self.x = x
        self.y = y
        self.cost = cost

maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
# maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]

print(solution(maps))