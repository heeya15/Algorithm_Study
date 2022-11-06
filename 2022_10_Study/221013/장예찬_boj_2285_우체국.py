# https://www.acmicpc.net/problem/2285
# 예상 알고리즘: 그리디
# 베스트 알고리즘: 그리디

from sys import stdin

def solution(N, peoples):
    peoples.sort()
    rightHeads = sum(list(zip(*peoples))[1])
    leftHeads = 0
    diff = 100000000000
    answer = 0
    for pos, heads in peoples:
        leftHeads += heads
        d = abs(leftHeads - rightHeads)
        if  diff > d:
            answer = pos 
            diff = d
        rightHeads -= heads

    return answer


def solutionInput():
    N = int(stdin.readline().strip())
    peoples = [list(map(int, stdin.readline().strip().split())) for _ in range(N)]
    return N, peoples

print(solution(*solutionInput()))

# print(-1 % 8)