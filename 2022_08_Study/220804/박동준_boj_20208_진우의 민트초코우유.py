from collections import deque

def recur(x,y,hp,dis,count):
    global max_mint
    # 종료조건
    # 1. 현재 채력으로 집에 도착하지 못할때
    # if hp <= 0 or dis > hp:
    #     return
    # 위의 조건 통과할때, 집을 돌아갈 수 있음? 그럼 일단 체크해
    # if count == 3:
    #     return

    # print(x,y,'들어옴', " || hp :", hp, " || count : ",count, "|| 집까지의 거리 :", dis)
    if dis <= hp and max_mint < count:
        max_mint = count

    if count == len(mint):
        return
    #반복 탐색
    for i in range(len(mint)):
        # 이걸 가냐 안가냐의 문제
        mx,my,home_dis = mint[i]
        # print(mx,my,home_dis, "나는 지금 어디서 왔는가", x,y, "로부터 왔고 카운트는" , count, "이 좌표로갔을때 집까지의 거리는",home_dis,"이다")
        # 현재위치와의 거리
        distance = abs(mx-x) + abs(my-y)
        # 가능한지 체크할려
        
        # 방문하지 않은곳이고, 현재 채력으로 갈 수 있는 곳인가?!
        if visit[i] == 0 and hp - distance >= 0:
            visit[i] = 1
            recur(mx,my,hp-distance+H,home_dis,count + 1)
            visit[i] = 0


N,M,H = map(int, input().split())
# N,M,H 모두 10보다 작기 때문에, 백트래킹으로 모든 수를 찾아낸다.

arr = [list(map(int,input().split())) for _ in range(N)]
mint = []
x,y = 0,0
for i in range(N):
    for j in range(N):
        if arr[i][j] == 1:
            x,y  = i,j
        elif arr[i][j] == 2:
            mint.append([i,j])

for i in range(len(mint)):
    mint_x,mint_y = mint[i]
    x_dis = abs(x - mint_x)
    y_dis = abs(y - mint_y)
    dis = x_dis + y_dis
    mint[i].append(dis)


count = 0
visit = [0]*len(mint)
max_mint = 0
recur(x,y,M,0,0)
# 그 지점을 가냐 안가냐의 문제
# 마지막에는 집에 도착해야함,
print(max_mint)
            
        



