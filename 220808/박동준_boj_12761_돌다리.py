from collections import deque

A,B,N,M = map(int, input().split())

visit = [-1]*100001

q = deque()
q.append(N)
visit[N] = 0
# print(A*3)
while q:
    x = q.popleft()
    
    if x == M:
        print(visit[x])
        break
    dx = [-1,1,A,B,-1*A,-1*B,A,B]
    # print(dx)

    for i in range(8):
        # print(dx[i])
        if i < 6:

            nx = x + dx[i]
            if 0 <= nx < 100001 and visit[nx] == -1:
                visit[nx] = visit[x] + 1
                q.append(nx)

        else:
            nx = x*dx[i]
            if 0 <= nx < 100001 and visit[nx] == -1:
                visit[nx] = visit[x] + 1
                q.append(nx)