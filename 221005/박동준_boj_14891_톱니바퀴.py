from collections import deque
arr = [list(input()) for _ in range(4)]
K = int(input())
rot =[ list(map(int, input().split())) for _ in range(K)]

for gear, rotation in rot:
    ge = gear-1
    
    # -- 연결된 기어들 확인하기

    check = [[] for _ in range(4)]
    if arr[0][2] != arr[1][6]:
        check[0].append(1)
        check[1].append(0)
    
    if arr[1][2] != arr[2][6]:
        check[1].append(2)
        check[2].append(1)
    
    if arr[2][2] != arr[3][6]:
        check[2].append(3)
        check[3].append(2)
    # 회전에 맞게 한개 돌림
    
    commit = [ge]
    visit = [0]*4
    
    q = deque()
    q.append(ge)
    visit[ge] = 1
    while q:
        a = q.popleft()
        for i in range(len(check[a])):
            if visit[check[a][i]] == 0:
                q.append(check[a][i])
                commit.append(check[a][i])
                visit[check[a][i]] = 1
    
    # --- 연결된 기어들 확인이 끝남

    for node_num in commit:
        # node_num 에 따라 회전 방향을 결정
        a = abs(node_num-ge)%2
        rotate = 0
        if a == 1:
            rotate = -rotation
        else:
            rotate = rotation
        # 회전 방향 결정
        if rotate == 1:
            del_num = arr[node_num].pop()
            arr[node_num] = [del_num] + arr[node_num]
        else:
            del_num = arr[node_num].pop(0)
            arr[node_num] = arr[node_num] + [del_num]

result = 0
for k in range(len(arr)):
    if arr[k][0] == "1":
        result += 2**k

print(result)




    


    

    



    # 연결된거 돌림








## 마지막 0번 idx 숫자 판별
