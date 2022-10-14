# Codility - PermMissingElem

def solution(A):
    # write your code in Python 3.6
    A = sorted(A)
    
    for i in range(len(A)):
        if i+1 != A[i]:
            return i+1

    return len(A) + 1