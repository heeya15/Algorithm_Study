from sys import stdin

N = map(int, input())
mable = list(map(int, stdin.readline().split()))

ans = 0
def increase(v, num):
    global ans
    if len(v) <= 2:
        ans = max(ans, num)
        return
    for i in range(1, len(v)-1):
        cc = v[:]
        temp = cc[i-1]*cc[i+1]
        del cc[i]
        increase(cc, num+temp)

increase(mable, 0)
print(ans)