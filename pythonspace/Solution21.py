# 프로그래머스 - 게임 맵 최단거리

from collections import deque


def solution(maps):
    xLen = len(maps)
    yLen = len(maps[0])
    
    visited = [[False] * (yLen) for i in range(xLen)]
    
    print(maps)
    print(visited)
    
    bfs(0, 0, maps)
    
    answer = 0
    return answer

# 최단경로 탐색 문제 => DFS 사용
def bfs(x, y, maps):
    queue = deque()
    node = Node(x, y, 1)
    # queue.appendleft(node)
    
    # for i in range(len(queue)):
    #     print(int(queue[i].x) + " " + int(queue[i].y) + " " + int(queue[i].cost))
    
    pass

class Node:
    def __init__(self, x, y, cost):
        self.x = x
        self.y = y
        self.cost = cost

maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
# maps = [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]

print(solution(maps))