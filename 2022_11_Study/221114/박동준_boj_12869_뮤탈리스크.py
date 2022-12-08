import heapq

N = int(input())

q = []
arr = list(map(int, input().split()))

for i in range(N):
    heapq.heappush(q,-arr[i])

deal = [9,3,1]
flag = False
count = 0
while q:
    del_scv = []
    # 3번만 쨀 수 있음
    for i in range(3):
        if q:
            a = heapq.heappop(q)
            del_scv.append(a)
        else:
            break
    print(del_scv)
    for i in range(len(del_scv)):
        k = (-1*del_scv[i]) - deal[i]
        if k <= 0:
            continue
        else:
            heapq.heappush(q,-k)
    print(q)
    count += 1

print(count)