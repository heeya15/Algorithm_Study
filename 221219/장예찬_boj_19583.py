# https://www.acmicpc.net/problem/19583
# 예상 알고리즘: 집합
# 베스트 알고리즘: 집합

import sys
input = sys.stdin.readline
inputs = sys.stdin.readlines

def solutionInput():
    S, E, Q = input().split()
    logs = inputs()
    return S, E, Q, logs

def calculateTime(s : str):
    hour, minute = map(int, s.split(':'))
    return hour * 60 + minute

def solution(S : str, E : str, Q : str, logs: list):
    sTime, eTime, qTime = calculateTime(S), calculateTime(E), calculateTime(Q)

    enterSet = set()
    resultSet = set()

    for log in logs:
        chatTimeStr, nickName = log.split()
        chatTime = calculateTime(chatTimeStr)

        if chatTime <= sTime:
            enterSet.add(nickName)
            continue
        elif eTime <= chatTime <= qTime and nickName in enterSet:
            resultSet.add(nickName)
            continue
    return len(resultSet)

print(solution(*solutionInput()))