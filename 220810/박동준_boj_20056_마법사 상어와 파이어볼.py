N, M, K = map(int, input().split())

# fire_ball = [list(map(int, input().split())) for _ in range(M)]

arr = [[[] for __ in range(N)] for _ in range(N)]

for _ in range(M):
    r, j, m, s, d = map(int, input().split())

    if m:
        arr[r - 1][j - 1].append([m, s, d])

# 방향성을 위한 방향벡터 주어진 그림대로 이동좌표를 잡음
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

# 홀짝구분 나머지로 구분하자
# 각 좌표에 들어가게 되는 파이어볼이 있는지 알아야함

# 몇번 이동시킴?
for k in range(K):
    move_fire = [[[] for _ in range(N)] for _ in range(N)]

    for x in range(N):
        for y in range(N):
            if arr[x][y]:
                for m, s, d in arr[x][y]:
                    nx = ((x + s*dx[d])+N) % N
                    ny = ((y + s*dy[d])+N) % N
                    move_fire[nx][ny].append([m, s, d])

    for x1 in range(N):
        for y1 in range(N):
            cnt = len(move_fire[x1][y1])
            if cnt > 1:
                nm, ns, nd = 0, 0, 0
                for m, s, d in move_fire[x1][y1]:
                    nm += m
                    ns += s
                    nd += d % 2

                nm, ns = nm//5, ns//cnt
                # print(move_fire[x][y])
                move_fire[x1][y1] = []
                # print(move_fire[x][y], nm)
                if nm != 0:
                    if nd == 0 or nd == cnt:
                        for i in range(4):
                            move_fire[x1][y1].append([nm, ns, i*2])
                    else:
                        for i in range(4):
                            move_fire[x1][y1].append([nm, ns, i*2+1])

    arr = move_fire
result = 0
for i in range(N):
    for j in range(N):
        for d in arr[i][j]:
            # print(m, n, d)

            result += d[0]
print(result)
