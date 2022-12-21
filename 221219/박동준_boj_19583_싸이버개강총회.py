S,E,Q = input().split()
# 어짜피 다 시간 단위로 들어옴
S = int(S[:2] + S[3:])
E = int(E[:2] + E[3:])
Q = int(Q[:2] + Q[3:])
user = dict()
result = set()
count = 0
while True:
    try:
        a,b = input().split()
        a = int(a[:2] + a[3:])
        # 입력을 받고, 시작 전 시간이라면?
        if a <= S:
            user[b] = 1
        # 스트리밍 종료 직전 직후 시작 전 입력을 한 사람이라면?
        elif E <= a <= Q:
            if user.get(b) and user[b] == 1:
                user[b] = 0
                count += 1
    except:
        break
print(count)