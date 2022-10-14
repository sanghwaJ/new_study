# Softeer - 비밀메뉴

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

if N > M:
    print('normal')
else:
    if secret in con:
        print('secret')
    else:
        print('normal')