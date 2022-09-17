import sys
sys.setrecursionlimit(10**6)
N = int(input())

arr = [list(map(int, input().split())) for _ in range(N)]

dx = [0,0,-1,1]
dy = [-1,1,0,0]

dp = [[0]*N for _ in range(N)]
# print(dp, arr)


# 메모이제이션을 통한 재귀탐색 가지치기
# 방문 지점에서 갈 수 있는 최대 거리를 미리 구함
visit = [[False]*N for _ in range(N)]
def dfs(x,y):
    
    # 가지치기
    if dp[x][y]:
        return dp[x][y]
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N  and 0 <= ny < N and arr[x][y] < arr[nx][ny]:
            dp[x][y] = max(dp[x][y],dfs(nx,ny) + 1)

    return dp[x][y]        
            
            


for i in range(N):
    for j in range(N):
        dfs(i,j)

count = 0
for i in range(N):
    for j in range(N):
        count = max(count,dp[i][j])

print(count+1)
