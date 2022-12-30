# https://school.programmers.co.kr/learn/courses/30/lessons/1844
# 예상 알고리즘: BFS
# 베스트 알고리즘: BFS

from collections import deque

def solution(maps):
    n, m = len(maps), len(maps[0])
    goal = (n - 1, m - 1)
    queue = deque([(0, 0, 1)])
    dxy = (1, 0), (-1, 0), (0, 1), (0, -1)
    while queue:
        x, y, d = queue.popleft()
        if (x, y) == goal:
            return d
        for dx, dy in dxy:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < m and maps[nx][ny]:
                maps[nx][ny] = 0
                queue.append((nx, ny, d + 1))
    return -1

print(solution([[1, 0, 1, 1, 1], [1, 0, 1, 0, 1], [1, 0, 1, 1, 1], [1, 1, 1, 0, 1], [0, 0, 0, 0, 1]]))