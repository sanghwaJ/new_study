# Softeer - 회의실 예약 (1)

import sys
#input = sys.stdin.readline

N, M = map(int, input().split())

time_table = {}

for n in range(N):
    room_name = input()
    time_table[room_name] = [0 for i in range(19)]
time_table = dict(sorted(time_table.items()))

reserved_list = []
for m in range(M):
    room, start, end = input().split()
    reserved_list.append([room, int(start), int(end)])

for reserved in reserved_list:
    for i in range(reserved[1], reserved[2]):
        time_table[reserved[0]][i] = 1

for room in time_table:
    print("Room " + room + ":")
    avail_list = []
    for i in range(9, 18):
        if time_table[room][i] == 0:
            avail_list.append(i)
    if len(avail_list) == 0:
        print("Not available")
    else:
        cnt = 1
        start = ""
        avail_time_list = []
        temp = []
        if len(avail_list) > 1:
            for j in range(len(avail_list) - 1):
                temp.append(avail_list[j])
                if avail_list[j] + 1 == avail_list[j + 1]:
                    pass
                else:
                    cnt += 1
                    avail_time_list.append([str(temp[0]), str(avail_list[j] + 1)])
                    temp = []
            avail_time_list.append([str(temp[0]), str(avail_list[-1] + 1)])
        else:
            avail_time_list.append([str(avail_list[0]), str(avail_list[0] + 1)])
                
        print(str(cnt) + " available:")
        for avail_time in avail_time_list:
            if len(avail_time[0]) == 1:
                start = "0" + avail_time[0]
            else:
                start = avail_time[0]
            if len(avail_time[1]) == 1:
                end = "0" + avail_time[1]
            else:
                end = avail_time[1]
            print(start + "-" + end)
    if list(time_table.keys())[-1] != room:
        print("-----")