from sys import stdin

N, K = map(int, input().split())
NN = N * 2
A = list(map(int, stdin.readline().split()))
Robot = [False] * NN
enter, out = 0, N - 1

answer, count = 1, 0
ran = range(N)
while True:
    enter, out = (enter-1) % NN, (out-1) % NN
    Robot[out] = False #바로 내리기
    for i in ran:
        curr, nxt = (out - i - 1) % NN, (out - i) % NN
        if Robot[curr] and not Robot[nxt] and A[nxt]:
            Robot[nxt], Robot[curr] = True, False
            A[nxt] -= 1
            count += A[nxt] == 0
    Robot[out] = False #바로 내리기

    if A[enter]:
        A[enter] -= 1
        count += A[enter] == 0
        Robot[enter] = True

    if count >= K:
        break
    answer += 1

print(answer)



