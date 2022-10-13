
N = int(input())
S, W = [0]*N, [0]*N

for i in range(N):
    S[i], W[i] = map(int, input().split())

brokenNum = 0
def pick(idx, eggsW):
    global brokenNum
    if idx == N:
        cnt = 0
        for eggW in eggsW:
            if eggW <= 0:
                cnt += 1
        brokenNum = max(cnt, brokenNum)
        return

    if eggsW[idx] > 0:
        flag = False
        for i in range(N):
            if eggsW[i] < 1 or i == idx:
                continue
            flag = True
            tmp = eggsW[:]
            tmp[i] -= W[idx]
            tmp[idx] -= W[i]
            pick(idx + 1, tmp)
        if not flag:
            pick(idx + 1, eggsW)
    else:
        pick(idx+1, eggsW)

pick(0, S)
print(brokenNum)
