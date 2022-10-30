# 프로그래머스 - 아이템 줍기
def solution(rectangle, characterX, characterY, itemX, itemY):
    rectangle = sorted(rectangle, key=lambda x : (x[0], x[1]))
    
    dotList = []
    for i in range(len(rectangle)):
        dot1 = [rectangle[i][0], rectangle[i][1]]
        dot2 = [rectangle[i][0], rectangle[i][3]]
        dot3 = [rectangle[i][2], rectangle[i][1]]
        dot4 = [rectangle[i][2], rectangle[i][3]]
        
        if len(dotList) != 0:
            dotCheck(dotList, dot1)
            dotCheck(dotList, dot2)
            dotCheck(dotList, dot3)
            dotCheck(dotList, dot4)
        else:
            dotList.append(dot1)
            dotList.append(dot2)
            dotList.append(dot3)
            dotList.append(dot4)
        
    print(dotList)
        
        
    
    answer = 0
    return answer

def dotCheck(dotList, dot):
    for i in range(len(dotList)):
        pass
    
# 정답
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