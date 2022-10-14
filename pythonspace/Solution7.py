# Softeer - 지도 자동 구축

import sys
#input = sys.stdin.readline
import math

N = int(input())

square = 4 ** (N)

dots_for_line = int(math.sqrt(square))

print((dots_for_line + 1) ** 2)