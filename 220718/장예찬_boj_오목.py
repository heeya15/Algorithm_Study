import sys

length = 19
board = [[]] * length

for i in range(length):
    board[i] = list(map(int, sys.stdin.readline().split()))

dx = [-1, 0, -1, 1]
dy = [0, -1, -1, 1]

for y in range(length):
    for x in range(length):
        if board[y][x] != 0:
            while x ==
