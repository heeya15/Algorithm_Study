N, M, K = map(int, input().split())  # NxN격자 M개 파이어볼, K번 명령

board = [[[] for _ in range(N)] for _ in range(N)]

for _ in range(M):
    r, j, m, s, d = map(int, input().split())
    if m:
        board[r - 1][j - 1].append([m, s, d])

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(K):
    tempBoard = [[[] for _ in range(N)] for _ in range(N)]
    # 1. 모든 파이어볼이 이동한다.
    for x in range(N):
        for y in range(N):
            if board[y][x]:
                for b in board[y][x]:
                    nm, ns, nd = b
                    nx, ny = x + dy[nd] * ns, y + dx[nd] * ns
                    nx, ny = (nx + N) % N, (ny + N) % N
                    tempBoard[ny][nx].append([nm, ns, nd])
    for x in range(N):
        for y in range(N):
            cnt = len(tempBoard[y][x])
            if cnt > 1:
                nm, ns, nd = 0, 0, 0
                flag = True
                for tm, ts, td in tempBoard[y][x]:
                    nm += tm
                    ns += ts
                    nd += td % 2
                nm, ns = nm // 5, ns // cnt
                tempBoard[y][x].clear()
                if nm:  # 질량이 0 인 경우 소멸하는 조건 고려
                    if nd == 0 or nd == cnt:  # 합쳐지는 파이어볼 방향이 모두 홀수거나 짝수인 경우
                        for j in range(4):
                            tempBoard[y][x].append([nm, ns, j * 2])
                    else:
                        for j in range(4):
                            tempBoard[y][x].append([nm, ns, j * 2 + 1])
    board = tempBoard
total = 0
for x in range(N):
    for y in range(N):
        for b in board[y][x]:
            total += b[0]
print(total)