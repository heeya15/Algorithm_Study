from copy import deepcopy
from xml.dom import minicompat

N, M = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]

# 칠하는 방향만 바꿔주면 된다.
cam = []

for x in range(N):
    for y in range(M):
        if arr[x][y] != 0 and arr[x][y] != 6:
            cam.append([x, y, arr[x][y]])

# 캠 번호에 따라 칠해주는 방향을 다양하게 해준다.
# 카메라별 감시 방향 조합

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
min_num = 1e10
mode = {
    1: [[0], [1], [2], [3]],
    2: [[0, 2], [1, 3]],
    3: [[0, 1], [1, 2], [2, 3], [3, 0]],
    4: [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],
    5: [[0, 1, 2, 3]]
}


def fillhash(x, y, temp, dir):

    for i in range(len(dir)):
        direct = dir[i]
        nx, ny = x, y
        while True:
            nx += dx[direct]
            ny += dy[direct]
            if 0 > nx or 0 > ny or nx >= N or ny >= M:
                break

            if temp[nx][ny] == 6:
                break

            else:
                temp[nx][ny] = 9


def dfs(cur, arr):
    global min_num

    if cur == len(cam):
        count = 0
        for kk in range(N):
            count += arr[kk].count(0)
        min_num = min(min_num, count)
        return

    temp = deepcopy(arr)
    x, y, cctv = cam[cur]
    for i in range(len(mode[cctv])):
        # 색칠
        fillhash(x, y, temp, mode[cctv][i])
        # 다음단계
        dfs(cur+1, temp)
        # 다음 반복 문 전 초기화
        temp = deepcopy(arr)


dfs(0, arr)
print(min_num)
