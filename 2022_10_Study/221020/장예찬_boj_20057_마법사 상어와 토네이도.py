# https://www.acmicpc.net/problem/20057
# 예상 알고리즘: 시뮬레이션
# 베스트 알고리즘: 시뮬레이션

import sys
input = sys.stdin.readline

def solutionInput():
	N = int(input())
	sand = [list(map(int, input().split())) for _ in range(N)]
	return N, sand

def solution(N, sand):
	answer = 0
	r, c = N // 2, N // 2
	dr, dc = 0, -1
	cnt = 0
	while True:
		for i in range(1, N+1):
			for _ in range(2):	# 같은 크기의 이동을 2번씩 반복
				for _ in range(i): # 1부터 N-1까지의 크기만큼 이동
					r += dr
					c += dc
					cnt += 1
					if cnt == N * N:
						return answer
					answer += moveSand(r, c, sand, N)
				dr, dc = -dc, dr # 방향 전환 
				tornadoTurn()
		dr, dc = dc, -dr # 방향 전환
		tornadoTurn()

tornado = [[0, 0, 0.02, 0, 0],
		   [0, 0.1, 0.07, 0.01, 0],
		   [0.05, -1, 0, 0, 0],
		   [0, 0.1, 0.07, 0.01, 0],
		   [0, 0, 0.02, 0, 0]]

def tornadoTurn():
	global tornado
	# 행열 좌측 회전
	tornado = list(list(zip(*tornado))[::-1])

def moveSand(yr, yc, sand, N):
	answer = 0
	remainSands = sandAmount = sand[yr][yc]
	sand[yr][yc] = 0
	ar, ac = 0, 0
	for i in range(5):
		for j in range(5):
			if tornado[i][j] == 0:
				continue			
			nr, nc = yr + i - 2, yc + j - 2			
			if tornado[i][j] == -1:
				ar, ac = nr, nc
				continue
			diffSand = int(sandAmount * tornado[i][j])
			if isInMap(nr, nc, N):
				sand[nr][nc] += diffSand
			else:
				answer += diffSand
			remainSands -= diffSand
	if isInMap(ar, ac, N):
		sand[ar][ac] += remainSands
	else:
		answer += remainSands
	return answer

def isInMap(r, c, N):
	return 0 <= r < N and 0 <= c < N

N, sand = solutionInput()
print(solution(N, sand))