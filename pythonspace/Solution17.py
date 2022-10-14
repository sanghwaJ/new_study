# Codility - Nesting
# -> 개발중...
def solution(A):
    # write your code in Python 3.6
    if len(S) < 2:
        return 0

    if S[0] == ")":
        return 0
    
    if S[-1] == "(":
        return 0

    stack = []
    
    for i in range(len(S)):
        stack.append(S[i])
        
        pass
    
S = "(()(())())"
solution(S)