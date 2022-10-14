# Softeer - 택배 마스터 광우

import sys
#input = sys.stdin.readline
from itertools import permutations

N, M, K = map(int, input().split())
rails = list(map(int, input().split()))
permutations_rails = list(permutations(rails, N))

answer_list = []
for i in range(len(permutations_rails)):
    answer = 0
    idx = 0
    for j in range(K):
        weight = 0
        # while True 후, 원하는 조건 발생 시, break
        while True:
           weight += permutations_rails[i][idx]
           # idx가 N보다 커질 경우 0번째로 restart
           idx = (idx + 1) % N
           if weight + permutations_rails[i][idx] > M:
               break
        answer += weight
    answer_list.append(answer)
        
print(min(answer_list))
        