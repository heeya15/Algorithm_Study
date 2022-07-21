import sys, math

N, L = map(int, input().split())

board = []  # 일반 지도
for _ in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))
boardT = list(zip(*board))  # 전치된 지도


# 라인을 입력받아 1 또는 0 반환
def isOk(line, size, limit):
    isBuilt = [False] * size
    for i in range(size - 1):
        curr, nxt = line[i], line[i + 1]
        if abs(nxt - curr) > 1:
            return 0
        if isBuilt[i + 1] or curr == nxt:
            continue
        if curr < nxt:
            for j in range(i, i - limit, -1):
                if j < 0 or isBuilt[j] or line[j] != curr:
                    return 0
                isBuilt[j] = True
        else:
            for j in range(i+1, i + limit + 1):
                if j > size or isBuilt[j] or line[j] != nxt:
                    return 0
                isBuilt[j] = True
    return 1


answer = 0
for m in range(N):
    answer += isOk(board[m], N, L)
    answer += isOk(boardT[m], N, L)
print(answer)
