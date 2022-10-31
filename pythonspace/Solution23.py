# 프로그래머스 - 아이템 줍기
from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    answer = 0
    
    # 의도하지 않은 최단 거리를 찾지 않기 위해 2배의 2차원 배열인 map 선언 
    # 모든 좌표 값은 1이상 50이하 => 102x102 배열 선언
    map = [[-1] * 102 for i in range(102)]
    
    for i in range(len(rectangle)):
        # 2배의 map에 맞추기 위해 좌표 값 또한 2배
        x1 = rectangle[i][0] * 2
        y1 = rectangle[i][1] * 2
        x2 = rectangle[i][2] * 2
        y2 = rectangle[i][3] * 2
        
        # x1~x2, y1~y2 check
        for j in range(x1, x2+1):
            for k in range(y1, y2+1):
                # 테두리가 아닌 경우
                if x1 < j < x2 and y1 < k < y2:
                    map[j][k] = 0
                # 테두리이면서 다른 직사각형의 내부가 아닌 경우
                elif map[j][k] != 0:
                    map[j][k] = 1
                    
    visited = [[False] * 102 for i in range(102)]
    
    queue = deque()
    queue.append(Node(characterX * 2, characterY * 2, 1))
    visited[characterX * 2][characterY * 2] = True
    
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    
    while len(queue) != 0:
        node = queue.popleft()
        
        if node.x == itemX * 2 and node.y == itemY * 2:
            answer = node.cost // 2
            break
        
        for i in range(4):
            xx = node.x + dx[i]
            yy = node.y + dy[i]
            
            if map[xx][yy] == 1 and visited[xx][yy] == False:
                queue.append(Node(xx, yy, node.cost + 1))
                visited[xx][yy] = True
                
    return answer

class Node:
    def __init__(self, x, y, cost):
        self.x = x
        self.y = y
        self.cost = cost
    
# 정답 check
rectangle = [[[1,1,7,4],[3,2,5,5],[4,3,6,9],[2,6,8,8]],
             [[1,1,8,4],[2,2,4,9],[3,6,9,8],[6,3,7,7]],
             [[1,1,5,7]],
             [[2,1,7,5],[6,4,10,10]],
             [[2,2,5,5],[1,3,6,4],[3,1,4,6]]]
characterX = [1, 9, 1, 3, 1]
characterY = [3, 7, 1, 1, 4]
itemX = [7, 6, 4, 7, 6]
itemY = [8, 1, 7, 10, 3]
answer = [17, 11, 9, 15, 10]

for i in range(len(rectangle)):
    sol = solution(rectangle[i], characterX[i], characterY[i], itemX[i], itemY[i])
    print(sol)
    if (sol == answer[i]):
        print("정답")
    else:
        print("오답")