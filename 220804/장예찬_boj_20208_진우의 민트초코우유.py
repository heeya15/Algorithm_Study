from sys import stdin
from collections import defaultdict

N, M, H = map(int, input().split())
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
milk.append(my)
milkT = len(milk)

distDict = defaultdict(list)

for i in range(milkT):
    for j in range(milkT):
        distDict[i].append(distance(milk[i], milk[j]))

startIdx = len(milk) - 1
visited = [False] * milkT

answer = 0

def dfs(strIdx, cnt, M):
    global answer
    for i in range(milkT):
        if visited[i] or distDict[strIdx][i] > M:
            continue
        if i == startIdx:
            answer = answer if answer > cnt else cnt
            return
        visited[i] = True
        dfs(i, cnt + 1, M - distDict[strIdx][i] + H)
        visited[i] = False

dfs(startIdx, 0, M)
print(answer)