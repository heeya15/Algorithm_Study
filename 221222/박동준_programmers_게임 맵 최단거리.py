from collections import deque

def solution(maps):
    answer = 0
    n,m = len(maps), len(maps[0])
    visit = [ [0] * m for _ in range(n)]
    q = deque([])
    
    dx = [-1,1,0,0]
    dy = [0,0,1,-1]
    
    q.append((0,0))
    
    while q:
        x,y = q.popleft()
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if 0 <= nx < n and 0 <= ny < m and visit[nx][ny] == 0 and maps[nx][ny] == 1:
                # 범위내에 있고 갈 수 있는 공간이라면?
                visit[nx][ny] = visit[x][y] + 1
                q.append((nx,ny))
    
    if visit[n-1][m-1] == 0:
        return -1
    else:
        return visit[n-1][m-1] + 1
