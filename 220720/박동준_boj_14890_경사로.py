# 입력
N, L = map(int, input().split())
arr = [ list(map(int, input().split())) for _ in range(N)]
ans = 0

# 가로 탐색
for i in range(N):
    # 제일 처음 값 탐색
    pre = arr[i][0]

    # 같은 칸을 지나온 갯수 --> 1. 높아질경우 , 2, 같을경우, 3. 낮아질경우
    cnt = 1
    for j in range(1,N):
        if arr[i][j] == pre:
            cnt += 1
            pre = arr[i][j]
        elif arr[i][j] == pre+1 and cnt >= 0:
            if cnt >= L:
                cnt = 1
                pre = arr[i][j]
            else:
                break
    # 하는중.... 머리가 꼬인다...


print(ans)