# Softeer - 회의실 예약 (2)

import sys
#input = sys.stdin.readline

N, M = map(int, input().split())

time_table = {}
for n in range(N):
    room_name = input()
    time_table[room_name] = [0] * 18

for m in range(M):
    room, start, end = input().split()
    start = int(start)
    end = int(end)
    for j in range(start, end):
        time_table[room][j] = 1

time_table = sorted(time_table.items())

for i in range(N):
    # boolean 활용
    start_check = True 
    temp = []
    for j in range(9, 18):
        if start_check and time_table[i][1][j] == 0:
            start_time = j
            start_check = False
        elif not start_check and time_table[i][1][j] == 1:
            end_time = j
            start_check = True
            temp.append([start_time, end_time])
    if not start_check:
        temp.append([start_time, 18])
     
    print("Room " + time_table[i][0] + ":")
    if len(temp) == 0:
        print("Not available")
    else:
        print(str(len(temp)) + " available:" )
        for j in range(len(temp)):
            # python format
            print(f"{temp[j][0]:02d}-{temp[j][1]}")
    if i != N - 1:
        print("-----")