## 단계별 이동할때 주변 쓰레기 count

# 쓰레기를 밟고 지나가는 경우  cnt +1

# 안밟고 지나가는 경우 cnt  그대로 대신 좌표는 동일cnt 만 다르게

# 도착했을 때 경로의 count 를 함께 담는다

# 지나간다 x,y,throw, aside
import heapq

N,M = map(int, input().split())
# 입력받고
L, G = list(), list()
for i in range(N):
    L.append(list(input().rstrip()))
    for j in range(M):
        if L[i][j] == 'g':
            G.append([i, j])
        elif L[i][j] == 'S':
            sx, sy = i, j
        elif L[i][j] == 'F':
            fx, fy = i, j

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


for x, y in G:
    for i in range(4):
        tx = x + dx[i]
        ty = y + dy[i]
        if 0 <= tx < N and 0 <= ty < M and L[tx][ty] == '.':
            L[tx][ty] = '#'

Q = []
heapq.heappush(Q, (0, 0, sx, sy))
V = [[0] * (M) for _ in range(N)]
V[sx][sy] = 1

while Q:
    a, b, x, y = heapq.heappop(Q)

    for i in range(4):
        tx = x + dx[i]
        ty = y + dy[i]
        if 0 <= tx < N and 0 <= ty < M and not V[tx][ty]:
            V[tx][ty] = 1
            if L[tx][ty] == '.':
                heapq.heappush(Q, (a, b, tx, ty))
            elif L[tx][ty] == '#':
                heapq.heappush(Q, (a, b + 1, tx, ty))
            elif L[tx][ty] == 'g':
                heapq.heappush(Q, (a + 1, b, tx, ty))
            else:
                print(a, b)
                break


