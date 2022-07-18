import sys

length = 19
board = [[]] * length

for i in range(length):
    board[i] = list(map(int, sys.stdin.readline().split()))

dx = [0, 1, 1, 1]
dy = [1, 0, -1, 1]


def fiveTree():
    for y in range(length):
        for x in range(length):
            if board[y][x] != 0:
                curr = board[y][x]
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    count = 1
                    while nx != -1 and ny != -1 and nx != length and ny != length and board[ny][nx] == curr:
                        count += 1

                        if count == 5:
                            rx, ry = x - dx[d], y - dy[d]  # 6목
                            nnx, nny = nx + dx[d], ny + dy[d]  # 6목
                            if rx != -1 and ry != -1 and rx != length and ry != length and board[ry][rx] == curr:
                                break
                            if nnx != -1 and nny != -1 and nnx != length and nny != length and board[nny][nnx] == curr:
                                break
                            return curr, y+1, x+1
                        nx += dx[d]
                        ny += dy[d]
    return 0, 0, 0


winner, x, y = fiveTree()
print(winner)
if winner:
    print(x, y)
