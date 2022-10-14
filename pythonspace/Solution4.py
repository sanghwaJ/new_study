# Softeer - 전광판

import sys
#input = sys.stdin.readline

T = int(input())

def solutuon(T):
    num_list = {" ":"0000000",
                "0":"1110111", 
                "1":"0010010", 
                "2":"1011101", 
                "3":"1011011", 
                "4":"0111010", 
                "5":"1101011", 
                "6":"1101111", 
                "7":"1110010", 
                "8":"1111111", 
                "9":"1111011"}
    
    for i in range(T):
        answer = 0
        A, B = input().split()
        A = (5 - len(A)) * " " + A
        B = (5 - len(B)) * " " + B
        
        for i in range(5):
            if A[i] != B[i]:
                for j in range(7):
                    if num_list[A[i]][j] != num_list[B[i]][j]:
                        answer += 1

        print(answer) 

solutuon(T)