# https://www.acmicpc.net/problem/1092
# 베스트 알고리즘: 그리디
# 예상 알고리즘: 그리디

import sys
input = sys.stdin.readline

def solutionInput():
    n = int(input())
    cranes = list(map(int, input().split()))
    m = int(input())
    boxes = list(map(int, input().split()))
    return n, cranes, m, boxes

def solution(n, cranes, m, boxes):
    cranes.sort(reverse=True)
    boxes.sort(reverse=True)
    if cranes[0] < boxes[0]:
        return -1
    answer = 0
    while boxes:
        answer += 1
        for crane in cranes:
            if boxes and crane < boxes[-1]:
                break
            for i in range(len(boxes)):
                if crane >= boxes[i]:
                    del boxes[i]
                    break
    return answer

print(solution(*solutionInput()))
