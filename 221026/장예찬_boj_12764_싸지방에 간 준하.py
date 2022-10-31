from sys import stdin

def solutionInput():
    X = int(stdin.readline().strip())
    times = [list(map(int, stdin.readline().strip().split())) for _ in range(X)]

    times.sort(key = lambda x: x[0])
    return X, times

def solution(X, times):
    print(X, times)
    return


solution(*solutionInput())