# https://www.acmicpc.net/problem/1174
# 예상 알고리즘: 조합
# 베스트 알고리즘: 조합, 백트래킹

import sys
input = sys.stdin.readline
from itertools import combinations

# 입력
def solutionInput():
    return int(input())

# 조합으로 푼다.
def solution(n):
    comb = []
    for i in range(1, 11):
        for j in combinations(range(10), i):
            j = sorted(list(j), reverse=True)
            comb.append(int(''.join(map(str, j))))
    comb.sort()
    return -1 if n > len(comb) else comb[n-1]

print(solution(solutionInput()))
