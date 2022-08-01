from sys import stdin

board = [list(map(int, stdin.readline().split())) for _ in range(9)]
zeros = [(x, y) for x in range(9) for y in range(9) if not board[x][y]]

# 스도쿠 어렵다....
# 살려줘...
def checkBoard(x, y, a):
    for i in range(9):
        if a == board[x][i] or a == board[i][y]:
            return False
    nx = x // 3 * 3
    ny = y // 3 * 3
    for i in range(3):
        for j in range(3):
            if a == board[nx+i][ny+j]:
                return False
    return True
def dfs(idx):
    if idx == len(zeros):
        for i in range(9):
            print(*board[i])
        exit(0)
    for i in range(1, 10):
        x, y = zeros[idx][0], zeros[idx][1]

        if checkBoard(x, y, i):
            board[x][y] = i
            dfs(idx + 1)
            board[x][y] = 0
dfs(0)
