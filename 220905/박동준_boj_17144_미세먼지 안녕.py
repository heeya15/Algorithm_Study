
R,C,T = map(int, input().split())

air = [list(map(int, input().split())) for _ in range(R)]

cleaner_up = 0
cleaner_down = 0
for i in range(R):
    if air[i][0] == -1:
        cleaner_up = i
        cleaner_down = i+1
        break


def spread():
    # 정보를 옮겨놓을 배열
    new_air_condition = [[0]*C for _ in range(R)]

    for i in range(R):
        for j in range(C):
            if air[i][j] != 0 and air[i][j] != -1:
                dx = [-1,1,0,0]
                dy = [0,0,-1,1]
                x,y = i,j
                count = 0
                for k in range(4):
                    nx = x + dx[k]
                    ny = y + dy[k]
                    if 0 <= nx < R and 0 <= ny < C and air[nx][ny] != -1:
                        new_air_condition[nx][ny] += (air[x][y]//5) 
                        count += (air[x][y]//5)
                
                new_air_condition[x][y] += (air[x][y] - count)
    for i in range(R):
        for j in range(C):
            air[i][j] = new_air_condition[i][j]
    air[cleaner_up][0] = -1
    air[cleaner_down][0] = -1

def air_up():
    dx = [0, -1, 0, 1]
    dy = [1, 0, -1, 0]
    direct = 0
    before = 0
    x, y = cleaner_up, 1
    while True:
        nx = x + dx[direct]
        ny = y + dy[direct]
        if x == cleaner_up and y == 0:
            break
        if nx < 0 or nx >= R or ny < 0 or ny >= C:
            direct += 1
            continue
        # print(x,y,R,C,'여기')
        air[x][y], before = before, air[x][y]
        x = nx
        y = ny


def air_down():
    
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    direct = 0
    before = 0
    x, y = cleaner_down, 1
    while True:
        nx = x + dx[direct]
        ny = y + dy[direct]
        if x == cleaner_down and y == 0:
            break
        if nx < 0 or nx >= R or ny < 0 or ny >= C:
            direct += 1
            continue
        air[x][y], before = before, air[x][y]
        x = nx
        y = ny




# 확산
for t in range(T):
    # 1단계 확산
    spread()

    # 2단계 회전
    air_up()
    air_down()
    


answer = 0
for i in range(R):
    for j in range(C):
        if air[i][j] > 0:
            answer += air[i][j]

print(answer)





