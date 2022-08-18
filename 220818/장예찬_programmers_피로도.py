#순열 문제
answer = 0
N = 0
visited = []

def solution(k, dungeons):
    global visited, N
    N = len(dungeons)
    visited = [False] * N
    permutation(k, 0, dungeons)
    return answer

def permutation(currK, cnt, dungeons):
    global answer
    if cnt > answer:
        answer = cnt
    for i in range(N):
        if not visited[i] and dungeons[i][0] <= currK:
            visited[i] = True
            permutation(currK-dungeons[i][1], cnt+1, dungeons)
            visited[i] = False

print(solution(80, [[80,20],[50,40],[30,10]]))