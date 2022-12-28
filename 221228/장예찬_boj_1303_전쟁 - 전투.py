# https://www.acmicpc.net/problem/1303
# 예상 알고리즘: DFS, BFS
# 베스트 알고리즘: DFS, BFS, 탐색


def solutionInput():
    N, M = map(int, input().split())
    board = [list(input()) for _ in range(M)]

    return N, M, board

def solution(N, M, board):
    visited = [[False] * N for _ in range(M)]
    WN, BN = 0, 0
    for i in range(M):
        for j in range(N):
            if board[i][j] == 'W' and not visited[i][j]:
                WN += dfsGroup(i, j, N, M, board, visited, 'W')**2
            elif board[i][j] == 'B' and not visited[i][j]:
                BN += dfsGroup(i, j, N, M, board, visited, 'B')**2
    return WN, BN

dxy = [(1, 0), (-1, 0), (0, 1), (0, -1)]

def dfsGroup(x, y, N, M, board, visited, team):
    visited[x][y] = True
    cnt = 1
    for dx, dy in dxy:
        nx, ny = x + dx, y + dy
        if 0 <= nx < M and 0 <= ny < N and not visited[nx][ny] and board[nx][ny] == team:
            cnt += dfsGroup(nx, ny, N, M, board, visited, team)
    return cnt

print(*solution(*solutionInput()))