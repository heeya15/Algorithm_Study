# https://www.acmicpc.net/problem/9466
# 베스트 알고리즘: dfs, 백트래킹
# 예상 알고리즘: dfs

import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def solutionInput():
    T = int(input())
    studentsNumbers, studentGroups = [], []
    for _ in range(T):
        studentsNumbers.append(int(input()))
        studentGroups.append(list(map(int, input().split())))
    return studentsNumbers, studentGroups

def solution(studentsNumbers, studentGroups):
    for n, studentGroup in zip(studentsNumbers, studentGroups):
        studentGroup = [0] + studentGroup
        finished = [False] * (n + 1)
        visited = [False] * (n + 1)
        
        answer = 0
        for i in range(1, n + 1):
            visitedSet = set()
            if not visited[i]:
                answer += dfs(i, studentGroup, visited, visitedSet, finished)
        print(n - answer)

def dfs(i, studentGroup, visited, visitedSet, finished):
    visitedSet.add(i)
    visited[i] = True

    # 사이클이 발생한 경우
    if studentGroup[i] in visitedSet:
        if not finished[studentGroup[i]]:
            # finished 변경
            j = studentGroup[i]
            cnt = 1
            finished[i] = True
            while j != i:
                j = studentGroup[j]
                cnt += 1
                finished[j] = True
            return cnt
        else:
            return 0
    elif not visited[studentGroup[i]]:
        return dfs(studentGroup[i], studentGroup, visited, visitedSet, finished)
    else:
        return 0

solution(*solutionInput())