# https://www.acmicpc.net/problem/1937
# 예상 풀이: DP
# 베스트 풀이: DP+DFS
from sys import stdin
# 주의: 최대 비용이기에 push, pop할 때 마이너스를 붙여야함
from heapq import heappush, heappop

class Solution:
    def __init__(self):
        self.N = int(input())
        self.map = [list(map(int, stdin.readline().split())) for _ in range(self.N)]
        self.move = [(1,0),(-1,0),(0,1),(0,-1)]
        self.dp= [[-1] * self.N for _ in range(self.N)]


    def solve(self):
        answer = 0
        for i in range(self.N):
            for j in range(self.N):
                answer = max(answer, self.dfs(i,j))
        return answer
    
    def dfs(self, x, y):
        if self.dp[x][y] == -1:
            self.dp[x][y] = 0

            for dx, dy in self.move:
                nx, ny = x + dx, y + dy
                if 0 <= nx < self.N and 0 <= ny < self.N and self.map[x][y] < self.map[nx][ny]:
                    self.dp[x][y] = max(self.dp[x][y], self.dfs(nx, ny))
        
        return self.dp[x][y] + 1

def solution():
    return Solution().solve()

print(solution())