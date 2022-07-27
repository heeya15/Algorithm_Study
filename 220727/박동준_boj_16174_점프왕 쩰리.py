from collections import deque


# -1 이 도착

def bfs():
    # 우, 하 이동가능
    dx = [1,0]
    dy = [0,1]

    # 방문확인을 위한 빈 배열
    visit = [[0]*n for _ in range(n)]

    # 시작점 추가 후 방문처리
    q = deque()
    q.append([0,0])
    visit[0][0] = 1

    # -1에 도달햇음을 확인하기 위한 변수
    flag = False

    # q가 빈값이 될때까지, --> 갈수 있는곳은 다 갈때까지
    while q:
        x,y = q.popleft()

        # 종료조건 1
        if arr[x][y] == 0:
            break
        
        # 종료조건 2
        if arr[x][y] == -1:
            flag = True
            break
        
        # 한 좌표에서 우, 하 로 이동
        for i in range(2):
            nx = x + dx[i]*arr[x][y]
            ny = y + dy[i]*arr[x][y]
            # 범위내에 있고, 방문안한곳 방문처리 및 다음경로로 추가
            if 0 <= nx < n and 0 <= ny < n and visit[nx][ny] == 0:
                visit[nx][ny] = 1
                q.append([nx,ny])

    #도착여부 return    
    return flag

# 입력
n = int(input())
arr = [ list(map(int,input().split())) for _ in range(n) ]

# bfs 실행 --> 배열 이동을 통한 도달여부 확인이기 때문
result = bfs()

# 결과 print
if result:
    print('HaruHaur')
else:
    print("Hing")
