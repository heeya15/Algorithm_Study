from sys import stdin
from collections import deque

N = int(input())
board = [list(map(int, stdin.readline().split())) for _ in range(N)]
visited = [[False] * N for _ in range(N)]

queue = deque([(0, 0)])
dx, dy = (0, 1), (1, 0)

# 큐 돌리기 bfs
while queue:
    pos = queue.popleft()
    x, y = pos[0], pos[1]
    if visited[y][x]:
        continue
    visited[y][x] = True
    val = board[y][x]

    for i in range(2):
        nx = x + val * dx[i]
        ny = y + val * dy[i]

        if nx < 0 or nx >= N or ny < 0 or ny >= N:
            continue

        if board[ny][nx] == -1:
            print('HaruHaru')
            break
        queue.append((nx, ny))
    else:
        continue
    break
else:
    print("Hing")













