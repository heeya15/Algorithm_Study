# https://www.acmicpc.net/problem/1445
# 예상 알고리즘: DP, BFS
# 베스트 알고리즘:
from sys import stdin
from collections import deque

def solution(N, M, startPos, endPos, forest):
    dp1 = [[0] * M for _ in range(N)]
    dp2 = [[0] * M for _ in range(N)]

    queue = deque([startPos])
    # dp1[startPos[0]][startPos[1]] = -1
    # dp2[startPos[0]][startPos[1]] = -1

    dr = startPos[0] - endPos[0]
    dc = startPos[1] - endPos[1]
    dr = 0 if dr == 0 else int(dr/abs(dr))
    dc = 0 if dc == 0 else int(dc/abs(dc))

    for r in range(startPos[0]+dr, endPos[0]+1, dr):
        if isNearGarbage(N, M, r, 0, forest):
            dp2[r][startPos[1]] = dp2[r-dr][startPos[1]] + 1
        if forest[r][0] == 'g':
            dp1[r][startPos[1]] = dp1[r-dr][startPos[1]] + 1

    for c in range(startPos[1]+dc, endPos[1]+1, dc):
        if isNearGarbage(N, M, r, 0, forest):
            dp2[startPos[0]][c] = dp2[startPos[0]][c-dc] + 1
        if forest[r][0] == 'g':
            dp1[startPos[0]][c] = dp1[startPos[0]][c-dc] + 1


    for r in range(startPos[0], endPos[0]+1, dr):
        for c in range(startPos[1], endPos[1]+1, dc):
            left, up = c-dc, r-dr
            if dp1[r][left] < dp1[up][c]:
                dp1[r][c] = dp1[r][left]
                dp2[r][c] = dp1[r][left]
            elif dp1[r][left] > dp1[up][c]:
                dp1[r][c] = dp1[up][c]
                dp2[r][c] = dp2[up][c]
            else:
                if dp2[r][left] < dp2[up][c]:
                    dp1[r][c] = dp1[r][left]
                    dp2[r][c] = dp2[r][left]
                else:
                    dp1[r][c] = dp1[up][c]
                    dp2[r][c] = dp2[up][c]
            if forest[r][c] == 'g':
                dp1[r][c] += 1
            if isNearGarbage(N,M,r,c,forest):
                dp2[r][c] += 1

    return (dp1[endPos[0]][endPos[1]], dp2[endPos[0]][endPos[1]])

drc = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]
def isNearGarbage(N, M, r, c, forest):
    for dr, dc in drc:
        nr = r + dr
        nc = c + dc
        if 0 <= nr < N and 0 <= nc < M:
            if forest[nr][nc] == 'g':
                break
    else:
        return True
    return False    

def solutionInput():
    N, M = map(int, stdin.readline().strip().split())
    forest = [[] for _ in range(N)]
    startR = startC = 0
    endR = endC = 0
    for n in range(N):
        forest[n] = list(stdin.readline().strip())
        for m in range(M):
            if forest[n][m] == 'S':
                startR, startC = n, m
            if forest[n][m] == 'E':
                endR, endC = n, m 
    
    return N, M, (startR, startC), (endR, endC), forest

print('{0} {1}'.format(*solution(*solutionInput())))