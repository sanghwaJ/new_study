# Codility - OddOccurrencesInArray

def solution(A):
    # write your code in Python 3.6
    if len(A) == 1:
        return A[0]

    A = sorted(A)
    print(A)
    for i in range(0, len(A), 2):
        if i+1 == len(A):
            return A[i]
        if A[i] != A[i+1]:
            return A[i]