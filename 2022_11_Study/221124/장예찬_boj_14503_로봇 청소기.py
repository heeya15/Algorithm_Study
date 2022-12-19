# https://www.acmicpc.net/problem/14503
# 베스트 알고리즘: 
# 예상 알고리즘: 구현

import sys
input = sys.stdin.readline

def solutionInput():
    N, M = map(int, input().split())
    r, c, d = map(int, input().split())
    room = [list(map(int, input().split())) for _ in range(N)]
    return N, M, r, c, d, room

# 북, 동, 남, 서 방향 정의 (북쪽부터 시계방향) 
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

def solution(N, M, r, c, d, room):
    # 네 방향 모두 청소가 이미 되어있거나 벽이면서 뒤쪽 방향이 벽인 경우까지 반복
    while True:
        # 현재 위치를 청소
        room[r][c] = 2
        # 네 방향을 검사
        for _ in range(4):
            # 왼쪽 방향으로 회전
            d = (d + 3) % 4
            nr = r + dr[d]
            nc = c + dc[d]

            # nr, nc는 room의 범위를 넘으면 안됨
            # 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면
            if 0 <= nr < N and 0 <= nc < M and room[nr][nc] == 0:
                # 왼쪽 방향으로 회전한 후 한 칸 전진
                r += dr[d]
                c += dc[d]
                # 1번부터 다시 진행
                break
        # 네 방향 모두 청소가 이미 되어있거나 벽인 경우
        else:
            # 뒤쪽 방향으로 후진 (부호를 반대로하면 바라보는 방향의 반대)
            r -= dr[d]
            c -= dc[d]
            # r, c는 room의 범위를 넘으면 안됨
            # 뒤쪽 방향이 벽이라 후진할 수 없는 경우
            if 0 <= r < N and 0 <= c < M and room[r][c] == 1:
                # 작동을 멈춘다
                break

    # 청소한 칸의 개수를 출력
    return sum(row.count(2) for row in room)

print(solution(*solutionInput()))