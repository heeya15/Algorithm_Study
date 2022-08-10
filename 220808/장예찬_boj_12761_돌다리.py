from collections import deque

A, B, N, M = map(int, input().split()) #콩콩이 힘 AB, 동규 위치 N, 주미 위치 M

def bfs(start, M):
    dx = [-1, 1, -A, A, -B, B, A, B]
    count = [0 for _ in range(100001)]
    count[N] = 1

    queue = deque([start])

    while queue:
        px = queue.popleft()
        if px == M:
            return count[px] - 1

        for i in range(8):
            if i < 6:
                nx = px + dx[i]

                if nx < 0 or nx > 100000 or count[nx] != 0:
                    continue
                queue.append(nx)
                count[nx] = count[px] + 1
            else:
                nx = px * dx[i]
                if nx < 0 or nx > 100000 or count[nx] != 0:
                    continue
                queue.append(nx)
                count[nx] = count[px] + 1

print(bfs(N, M))

