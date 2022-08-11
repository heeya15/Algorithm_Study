from sys import stdin

N, M = map(int, input().split())
board = [list(map(int, stdin.readline().split())) for _ in range(N)]
boards = [[b[:] for b in board] for _ in range(9)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1, ]
dirs = [0, [(0,), (1,), (2,), (3,)],
        [(0, 1), (2, 3)],
        [(0, 3), (0, 2), (1, 2), (1, 3)],
        [(0, 2, 3), (0, 1, 3), (1, 2, 3), (0, 1, 2)],
        [(0, 1, 2, 3)]]

cameraPos = []
cameraNum = []
minSpot = 64

for x in range(N):
    for y in range(M):
        if 0 < board[x][y] < 6:
            cameraPos.append((x, y))
            cameraNum.append(board[x][y])

cameraLen = len(cameraNum)

def dfs(start, curr):
    if start == cameraLen:
        total(cameraLen)
        return
    for ds in dirs[cameraNum[start]]:
        x, y = cameraPos[start]
        for d in ds:
            for i in range(1, 8):
                nx, ny = x + dx[d] * i, y + dy[d] * i
                if not (0 <= nx < N and 0 <= ny < M and boards[curr][nx][ny] != 6):
                    break
                if boards[curr][nx][ny] == 0:
                    boards[curr][nx][ny] = -1
        if curr < 8:
            boards[curr+1] = [b[:] for b in boards[curr]]
        dfs(start+1, curr+1)
        boards[curr] = [b[:] for b in boards[curr - 1]]


def total(v):
    global minSpot
    temp = 0
    for b in boards[v]:
        for i in b:
            if i == 0:
                temp += 1
    minSpot = min(minSpot, temp)
    return

dfs(0, 1)
print(minSpot)