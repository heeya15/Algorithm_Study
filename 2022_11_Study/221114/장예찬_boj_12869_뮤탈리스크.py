# https://www.acmicpc.net/problem/12869
# 예상 알고리즘: 탐색, DP
# 베스트 알고리즘: DP

import sys
from itertools import permutations

input = sys.stdin.readline

def solutionInput():
    N = int(input())
    SCVs = list(map(int, input().split()))
    return N, SCVs

deals = [9, 3, 1]
def solution(N, SCVs):
    SCVs = SCVs + (3 - N) * [0] #3개 이하의 SCV가 있을 때를 위해 0을 채워줌
    comb = list(permutations([9, 3, 1]))
    dp = [[[0]*61 for _ in range(61)] for _ in range(61)]
    dp[SCVs[0]][SCVs[1]][SCVs[2]] = 1
    
    for i in range(SCVs[0], -1, -1):
        for j in range(SCVs[1], -1, -1):
            for k in range(SCVs[2], -1, -1):
                if dp[i][j][k] > 0:
                    # comb를 순회해서 i, j, k에서 빼고 0보다 작으면 0으로 초기화
                    for c in comb:
                        ni = max(i - c[0], 0)
                        nj = max(j - c[1], 0)
                        nk = max(k - c[2], 0)
                        # 처음 도착한 경우 또는 더 적을 경우에만 dp를 업데이트
                        if dp[ni][nj][nk] == 0 or dp[ni][nj][nk] > dp[i][j][k] + 1:
                            dp[ni][nj][nk] = dp[i][j][k] + 1                    
    
    return dp[0][0][0] - 1

N, SCVs = solutionInput()
print(solution(N, SCVs))





