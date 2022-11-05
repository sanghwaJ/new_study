# 프로그래머스 - 피보나치 수

# 점화식 이용 => Fail
def solution1(n):
    fiboList = [0, 1]
    
    if n > 1:
        for i in range(n-1):
            fiboList.append(fiboList[i] + fiboList[i+1])
        
    return fiboList[n] % 1234567
    
# 파이썬 style 풀이
def solution2(n):
    a, b = 0, 1
    for i in range(n):
        a, b = b, a + b
    
    return a % 1234567

n = 1000
print(solution1(n))
print(solution2(n))