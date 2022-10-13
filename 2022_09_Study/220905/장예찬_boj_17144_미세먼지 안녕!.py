from sys import stdin

R, C, T = map(int, input().split())

dx = [-1, 0, 1, 0] 
dy = [0, 1, 0, -1] 

board = [list(map(int, stdin.readline().split())) for _ in range(R)]
    

def solution():
    global board
    air = [-1, -1]

    for i in range(R):
        if board[i][0] == -1:
            air[0], air[1] = i, i+1
            break
# q초기화
    q = set()
    for r in range(R):
        for c in range(C):
            if board[r][c] > 0:
                q.add((r, c))
# 로직
    for i in range(T):
        qTemp = set()
        tmpBoard = [b[:] for b in board]
        for x, y in q:
            origin = board[x][y]
            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]

                if 0<=nx<R and 0<=ny<C and not board[nx][ny] == -1:
                    temp = origin // 5
                    tmpBoard[nx][ny] += temp
                    tmpBoard[x][y] -= temp
                    qTemp.add((x, y))
                    qTemp.add((nx, ny))
        q = qTemp
        board = tmpBoard

        d, d2 = 0, 0
        x, y = air[0], 0
        x2, y2 = air[1], 0
        while True:
            nx, ny = x + dx[d], y + dy[d]
            if 0<=nx<R and 0<=ny<C and nx != air[1]:
                board[x][y] = board[nx][ny]
                board[air[0]][0], board[nx][ny] = 0, 0
                
                x, y = nx, ny
            else:
                if ny == -1:
                    break
                d +=1

        while True:
            nx2, ny2 = x2 - dx[d2], y2+ dy[d2]
            if 0<=nx2<R and 0<=ny2<C and nx2 != air[0]:
                board[x2][y2] = board[nx2][ny2]
                board[air[1]][0], board[nx2][ny2] = 0, 0
                x2, y2 = nx2, ny2
            else:
                if ny2 == -1:
                    break
                d2 +=1
    
    answer = 0
    for l in board:

        answer += sum(l)
    return answer 

print(solution())