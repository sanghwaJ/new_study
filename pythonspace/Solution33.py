# 221106 TEST
from collections import deque

def solution(times, n):
    timesDq = deque(times)
    
    car = []
    
    time = 0
    car.append([timesDq.popleft(), time])
    while car:
        time += 1
        for i in range(len(car)):
            car[i][0] -= 1
        for i in range(len(car)):
            if car[i][0] <= 0 and (time - car[i][1]) % n == 0:
                del car[i]
                break
        if len(car) < n and timesDq:
            car.append([timesDq.popleft(), time])
    return time

times = [4, 2, 1]
n = 2
print(solution(times, n))


'''
t 0 / [[4, 0]]
t 1 / [[3, 0], [2, 1]]
t 2 / [[2, 0], [1, 1]]
t 3 / [[1, 0], [0, 1]] => [[1, 0]] => [[1, 0], [1, 3]]
t 4 / [[0, 0], [0, 3]] => [[0, 3]]
t 5 / []
'''