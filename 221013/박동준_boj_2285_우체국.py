# 한 포인트에서 다른 모든 지점 거리 // 의 최소

# 사람 거리 --> 사람의 수 역시 중요함 같이 고려가 되어야해요

# N --> 100000만
import math
N = int(input())

arr = [(0,0)]
total_people = 0
for i in range(N):
    x,a = map(int, input().split())
    arr.append((x,a))
    total_people += a

    
# 이분탐색 조건
# 1. 중앙에서 출발 중앙 인덱스 값을 줄여줌 감소하는쪽으로 좌표를 이동
# l 의 경우 이동했을 때,

arr.sort(key=lambda x : x[0])
people  = 0
answer = 0
for i in range(N):
    people += arr[i][1]
    if people >=  total_people/2:
        answer = i
        break

print(answer)










# # 중앙 값 찾기
# mid = math.ceil(N/2)
# mid_num  = 0
# for k in range(1,N+1):
#     a = abs(k - mid)
    
#     mid_num += a*arr[k][1]

# l,r = 1, N
# flag = False
# mid = (l+r)//2
# while True:
#     mid_num  = 0
#     for k in range(1,N+1):
#         a = abs(k - mid)
    
#         mid_num += a*arr[k][1]

#     left = mid - 1
#     rigth = mid + 1
#     leftNum = 0 
#     rigthNum = 0
#     for k in range(1,N+1):
#         a = abs(k - left)
#         leftNum += a*arr[k][1]
    
#     for k in range(1,N+1):
#         a = abs(k - rigth) 
#         rigthNum += a*arr[k][1]

    
#     if leftNum > mid_num and mid_num <= rigthNum:
#         flag = True
#     elif leftNum == mid_num == rigthNum:
#         mid = left
#     elif leftNum < mid_num < rigthNum:
#         mid = left
#     elif leftNum >= mid_num >= rigthNum:
#         mid = rigth

#     if flag == True:
#         break

# print(mid)






