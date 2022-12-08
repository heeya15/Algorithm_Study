# 한무 탐색이네여

N,M = map(int, input().split())
r,c,d = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

# 방향에 대해서 처리할 벡터
# 북 왼쪽 서, 서 왼쪽 남, 남 왼쪽 동, 동 왼쪽 북 반영 벡터
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
# 북 0, 동 1, 남 2, 서 3
# 방향 바꾸기는 -1 씩 inx 변경 now dire
# dirt = [3,0,1,2]
cnt = 0
while True:
    arr[r][c] = 2
    for i in range(4):
        d = (d+3)%4
        nr = r + dx[d]
        nc = c + dy[d]
    
        if 0 <= nr < N and 0 <= nc < M and arr[nr][nc] == 0:
            # 왼쪽으로 회전한 방향의 앞의 값이 0 이면
            # 왼쪽으로 회전하고 한칸 전진
            r += dx[d]
            c += dy[d]
            break
    else:
        # 4방향에서 다 못찾았다면
        # 후진
        r -= dx[d]
        c -= dy[d]

        if 0 <= r < N and 0 <= c < M and arr[r][c] == 1:
            break

for i in range(N):
    cnt += arr[i].count(2)

print(cnt)