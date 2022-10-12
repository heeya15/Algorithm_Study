# https://www.acmicpc.net/problem/13397
from sys import stdin
N, M = map(int, input().split())

A = list(map(int, stdin.readline().split()))

l, r = min(A)-1, max(A)

while l <= r:
    sCnt = 1
    mid =  (r+l)//2
    top, low = 0, 10001
    for i in range(N):
        top = max(top, A[i])
        low = min(low, A[i])
        if top - low > mid:
            top = low = A[i]
            sCnt += 1
    if sCnt > M: 
        l = mid + 1    
    else:
        r = mid - 1
print(l)
