# Softeer - 성적평균

import sys
#input = sys.stdin.readline

N, K = map(int, input().split())

grades = list(map(int, input().split()))

for i in range(K):
    start, end = map(int, input().split())
    sum = 0
    for j in range(start - 1, end):
        sum += grades[j]
    answer = round(sum / (end - start + 1), 2)
    print(f'{answer:.2f}')