# 프로그래머스 - 올바른 괄호

def solution(s):
    stack = []
    for i in range(len(s)):
        if s[i] == "(":
            stack.append(s[i])
        elif s[i] == ")" and len(stack) != 0:
            stack.pop()
        else:
            return False
        
    if len(stack) != 0:
        return False
    
    return True

s = ["()()", "(())()", ")()(", "(()("]

for i in range(len(s)):
    print(solution(s[i]))