# https://www.acmicpc.net/problem/1715
# 예상 알고리즘: 정렬, 그리디
# 베스트 알고리즘: 정렬, 그리디
from sys import stdin
from collections import deque
from heapq import heappop, heapify, heappush
def solutionInput():
    N = int(stdin.readline().strip())
    cards = [int(stdin.readline().strip()) for _ in range(N)]

    return N, cards


def solution(N, cards):    
    heapify(cards)
    answer = 0
    while len(cards) != 1:
        temp = heappop(cards) + heappop(cards)
        answer += temp
        heappush(cards, temp)
    return answer

print(solution(*solutionInput()))