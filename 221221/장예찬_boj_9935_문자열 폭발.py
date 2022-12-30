# https://www.acmicpc.net/problem/9935
# 예상 알고리즘: 문자열, 스택
# 베스트 알고리즘: 문자열, 스택

import sys
input = sys.stdin.readline

def solutionInput():
    string = input().strip()
    bomb = input().strip()
    return string, bomb

def solution(string, bomb):
    bomb, bombLen = list(bomb), len(bomb)
    stack = []
    for c in string:
        stack.append(c)
        if stack[-bombLen:] == bomb:
            for _ in range(bombLen):
                stack.pop()
    return ''.join(stack) if stack else 'FRULA'

print(solution(*solutionInput()))