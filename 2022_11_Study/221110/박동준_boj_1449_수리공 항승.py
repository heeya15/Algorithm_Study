N, L = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
pipe = [0]*(10000)
length = L*2 + 1
cnt = 0
for i in range(len(arr)):
    # 바뀐 구멍의 좌표
    hole = arr[i]*2 -1

    # 해당구멍 좌우 모든 테이프가 발림?
    if sum(pipe[hole-1:hole+2]) == 3:
        continue
    else:
        a = pipe[hole-1:hole+2]
        count = 0
        for k in a:
            if k == 1:
                count += 1
        
        # 카운트 갯수만큼
        for j in range(hole-1 +count, hole-1+count + (L*2)+1):
            pipe[j] = 1

        cnt += 1
print(cnt)
