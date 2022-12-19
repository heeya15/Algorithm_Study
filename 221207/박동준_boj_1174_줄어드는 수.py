'''
n = 19 인 경우
0 1 2 3 4 5 6 7 8 9
10 20 21 30 31 32 40 41 42
이 규칙대로 42가 19번째의 가장 작은 수 이다.
# 작은 수를 기준으로 올라간다.
# 아 그냥 9-0까지 모든 수의 조합을 찾고 거기서 가능한 수만 찾으면 되겠네
# 골랐을 수 있고 안골랐을 수 있고
# print(num_list)
# 순서가 중요한 모든 경우의 수를 찾아야함 
'''

n : int = int(input())

num : int = 0


num_list = list(map(str, range(9,-1,-1)))
arr = []
combi =[]
def recur(cur,start,d):
    if cur == d:
        a = arr[:]
        b = "".join(a)
        combi.append(b)
        return
    
    for i in range(start,10):
        arr.append(num_list[i])
        recur(cur+1,i+1,d)
        arr.pop()

flag = False
for i in range(10):
    recur(0,0,i+1)

if n > len(combi):
    print(-1)
else:
    a = list(map(int, combi))
    a.sort()
    print(a[n-1])
