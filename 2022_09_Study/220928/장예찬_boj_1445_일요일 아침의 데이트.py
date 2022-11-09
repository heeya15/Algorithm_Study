# https://www.acmicpc.net/problem/1445
# 예상 알고리즘: BFS, 탐색
# 베스트 알고리즘: BFS, Dijkstra
from sys import stdin
from heapq import heappush, heappop

drc = [(-1, 0), (1, 0), (0, -1), (0, 1)]

def solutionInput():
    N, M = map(int, stdin.readline().strip().split())
    forest = [[] for _ in range(N)]
    startR = startC = 0
    for n in range(N):
        forest[n] = list(stdin.readline().strip())
    
    for n in range(N):
        for m in range(M):
            if forest[n][m] == 'S':
                startR, startC = n, m
            if forest[n][m] == 'g':
                for dr, dc in drc:
                    nr, nc = n + dr, m + dc
                    if 0 <= nr < N and 0 <= nc < M and forest[nr][nc] == '.':
                        forest[nr][nc] = 'n'            
    return N, M, startR, startC, forest

def solution(N, M, startR, startC, forest):
    queue = [(0, 0, startR, startC)]
    forest[startR][startC] = 'v'
    while queue:
        g, n, r, c = heappop(queue)
        for dr, dc in drc:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < M and forest[nr][nc] != 'v':
                if forest[nr][nc] == 'g':
                    heappush(queue, (g + 1, n, nr, nc))
                elif forest[nr][nc] == '.':
                    heappush(queue, (g, n, nr, nc))
                elif forest[nr][nc] == 'n':
                    heappush(queue, (g, n + 1, nr, nc))
                elif forest[nr][nc] == 'F':
                    return g, n
                forest[nr][nc] = 'v'
        

print(*solution(*solutionInput()))