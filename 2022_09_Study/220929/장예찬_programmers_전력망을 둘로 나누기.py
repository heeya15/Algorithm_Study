# https://school.programmers.co.kr/learn/courses/30/lessons/86971
# 예상 알고리즘: 트리
# 베스트 알고리즘: BFS

from collections import deque

def bfs(n, tree, start, cut):
    cnt = 0
    queue = deque([(start, tree)])
    visited = [False] * (n + 1)
    visited[start] = True
    while queue:
        node, tree = queue.popleft()
        cnt += 1

        for i in tree[node]:
            if not (node == start and i == cut) and not visited[i]:
                visited[i] = True
                queue.append((i, tree))
    return cnt


def solution(n, wires):
    answer = 101
    tree = [[] for _ in range(n + 1)]

    for a, b in wires:
        tree[a].append(b)
        tree[b].append(a)

    for a, b in wires:
        answer = min(answer, abs(bfs(n, tree, a, b) - bfs(n, tree, b, a)))
    return answer

print(solution(	9, [[1, 3], [2, 3], [3, 4], [4, 5], [4, 6], [4, 7], [7, 8], [7, 9]]))