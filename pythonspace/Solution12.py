# Softeer - 비밀메뉴2

import sys
# input = sys.stdin.readline

N, M, K = map(int, input().split())
_secret = input().split()
secret = ''
for _ in _secret:
    secret += _
_con = input().split()
con = ''
for _ in _con:
    con += _

if secret in con:
    print(len(secret))
else:
    secret_list = []
    for i in range(len(secret)):
        for j in range(1, len(secret)+1):
            if len(secret[i:j]) > 0:
                secret_list.append(secret[i:j])
    
    answer = 0
    for secret in secret_list:
        if secret in con:
            answer = max(answer, len(secret))
            
    print(answer)

    