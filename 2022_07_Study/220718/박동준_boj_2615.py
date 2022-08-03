
board =  [list(map(int,input().split())) for i in range(19)]

# 방향벡터

# 우 하 대각아래 대각위
dx = [0, 1, 1, -1]
dy = [1, 0, 1, 1]
# finish = False
for x in range(19):
    for y in range(19):
        # 0이 아닌돌을 만났을 때 탐색
        if board[x][y] != 0:
            color = board[x][y]
            for i in range(4):
                # 돌 하나 발견 --> 같은 숫자인지 확인해야함
                count = 1
                # 각 좌표값 탐색
                nx = x + dx[i]
                ny = y + dy[i]
                # print(x,y,'while 전')
                while 0 <= nx < 19 and 0 <= ny < 19 and board[nx][ny] == color:
                    count += 1
                    # print(x,y,'while 진행중')
                    # 계속 범위내의 카운트 탐색

                    if count == 5:
                        # print('왔음',x,y)
                        # 6목조건 파악 2가지 경우 1. 첫 시작점 이전 좌표가 같은색인경우, 2. 끝점 이후좌표가 같은색인경우
                        if 0 <= x - dx[i] < 19 and 0 <= y - dy[i] < 19 and board[ x - dx[i]][y - dy[i]] == color:
                            break

                        if 0 <= nx + dx[i] < 19 and 0 <= ny + dy[i] < 19 and board[nx + dx[i]][ny + dy[i]] == color:
                            break

                        print(color)
                        print(x+1, y+1)
                        exit()
                    nx += dx[i]
                    ny += dy[i]
            

print(0)




