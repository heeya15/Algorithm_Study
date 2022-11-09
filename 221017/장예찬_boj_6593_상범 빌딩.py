# https://www.acmicpc.net/submit/6593
# 예상 알고리즘: bfs
# 베스트 알고리즘: bfs

from collections import deque
from sys import stdin


dlrc = ((0, 0, 1), (0, 0, -1), (0, -1, 0), (0, 1, 0), (1, 0, 0), (-1, 0, 0))

def solution(start, building, LRC):
    L, R, C = LRC
    queue = deque([(*start, 0)])
    while queue:
        l, r, c, count = queue.popleft()

        for dl, dr, dc in dlrc:
            nl = l + dl
            nr = r + dr
            nc = c + dc

            if 0 <= nl < L and 0 <= nr < R and 0 <= nc < C:
                if building[nl][nr][nc] == '#':
                    continue
                if building[nl][nr][nc] == 'E':
                    return "Escaped in {0} minute(s).".format(count+1)
                building[nl][nr][nc] = '#'                    
                queue.append((nl, nr, nc, count+1))
    else:
        return "Trapped!"


def solutionInput():
    L, R, C = map(int, stdin.readline().strip().split())
    if L == R == C == 0:
        return False
    
    building = [[[] for _ in range(R)] for _ in range(L)]
    startIdx = ()

    for l in range(L):
        for r in range(R):
            building[l][r] = list(stdin.readline().strip())
            if not len(startIdx):
                for c, item in enumerate(building[l][r]):
                    if item == 'S':
                        startIdx = (l, r, c)
        stdin.readline()
    return startIdx, building, (L, R, C)

inputData = solutionInput()
while inputData:
    print(solution(*inputData))
    inputData = solutionInput()