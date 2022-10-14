# Codility - TapeEquilibrium
def solution(A):
    # write your code in Python 3.6
    start = A[0]
    end = sum(A[1:])
    answer = abs(start - end)
    
    for i in range(1, len(A)-1):
        start += A[i]
        end -= A[i]
        answer = min(answer, abs(start - end))

    return answer

print(solution([1,2,3,4,10]))
print(solution([-1,-2]))
print(solution([-10, -20, -30, -40, 100]))