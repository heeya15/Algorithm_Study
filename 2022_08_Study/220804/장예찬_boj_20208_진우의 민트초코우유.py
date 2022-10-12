from sys import stdin
from collections import defaultdict

N, M, H = map(int, stdin.readline().split())
world = [list(map(int, stdin.readline().split())) for _ in range(N)]
milk = []
my = ()

def distance(pos1, pos2):
    return abs(pos1[0]-pos2[0]) + abs(pos1[1]-pos2[1])

for x in range(N):
    for y in range(N):
        if world[x][y] == 2:
            milk.append((x, y))
        if world[x][y] == 1:
            my = (x, y)
milkT = len(milk)
distDict = defaultdict(list)

for i in range(milkT):
    distDict[-1].append(distance(milk[i], my))
    for j in range(milkT):
        distDict[i].append(distance(milk[i], milk[j]))

answer, startIdx = 0, -1
visited = [False] * milkT

milkRange = range(milkT)

def dfs(strIdx, cnt, M):
    global answer
    for i in milkRange:
        if not visited[i]:
            D = distDict[strIdx][i]
            if D > M:
                continue
            visited[i] = True
            dfs(i, cnt + 1, M - D + H)
            visited[i] = False
    if strIdx != -1 and distDict[-1][strIdx] <= M:
        answer = answer if answer > cnt else cnt

dfs(startIdx, 0, M)
print(answer)