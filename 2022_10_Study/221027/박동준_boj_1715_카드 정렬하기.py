
import heapq
N = int(input())

# arr = [int(input()) for _ in range(N)]

# arr.sort()
# sort 때문에 시간초과 heapq 사용해서 최적으로 최소값 빼오기
arr = []
for i in range(N):
    heapq.heappush(arr,int(input()))



# 일단 정렬
if len(arr) == 1:
    print(0)
else:
    result = 0
    while len(arr) >= 2:
        a = heapq.heappop(arr)
        b = heapq.heappop(arr)
        s = a + b
        result += s
        heapq.heappush(arr,s)
    print(result)
    # count = 0
    # result = 0
    # # 모든 카드를 비교할때까지
    # # 4개의 묶음 -> 총 3번
    # # 3개의 묶음 -> 총 2번
    # while count < N-1:
    #     arr.sort()
    #     count += 1
    #     # 2개 빼서
    #     a = arr.pop(0)
    #     b = arr.pop(0)
    #     s = a + b
    #     result += s
    #     arr.append(s)

    # print(result)