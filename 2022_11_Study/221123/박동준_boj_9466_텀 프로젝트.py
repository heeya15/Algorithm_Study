from collections import deque

T = int(input())
# 테케마다 한줄씩 출력
for t in range(T):
    n = int(input())
    arr = [-1] + list(map(int, input().split()))
    # 방문한 노드 체크를 위한 배열
    visit = [0]*(n+1)
    count = n
    # 2가지 중에 1개네 -> 자기 자신인 경우 -> 싸이클인경우
    for i in range(1,n+1):
        if visit[i] == 0:
            check = [i]
            while True:
                a = check[-1]
                b = arr[a]
                visit[a] = 1
                if visit[b] == 1:
                    if b in check:
                        count -= len(check[check.index(b):])
                    break
                else:
                    check.append(b)



                
    print(count)

