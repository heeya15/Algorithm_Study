
N = int(input())

eggs = []
brokenNum = 0
pickEgg = [(), ()]

for _ in range(N):
    a, b = map(int, input().split())
    eggs.append((a, b))

def combination(cnt, start):
    global brokenNum
    if cnt == 2:
        if pickEgg[0][0] <= pickEgg[1][1]:
            brokenNum += 1
        if pickEgg[1][0] <= pickEgg[0][1]:
            brokenNum += 1
        return
    if start == N:
        return
    pickEgg[cnt] = eggs[start]

    combination(cnt+1, start+1)
    combination(cnt + 1, start)


combination(0, 0)
print(brokenNum)
