# https://www.acmicpc.net/problem/1449
# 예상 알고리즘: 그리디
# 베스트 알고리즘: 그리디

import sys
input = sys.stdin.readline

def solutionInput():
    n, l = map(int, input().split())
    leakPos = list(map(int, input().split()))
    leaks = [0] * 1001
    for leak in leakPos:
        leaks[leak] = 1
    return n, l, leaks

# n은 물이 새는 곳의 개수, l은 테이프의 길이
def solution(n, l, leaks):
    answer = 0
    for idx, leak in enumerate(leaks): # 물이 새는 곳을 하나씩 확인
        if leak == -1: # 이미 테이프로 막혀있으면 넘어감
            continue
        if leak == 1: # 테이프로 막혀있지 않으면
            answer += 1 # 테이프를 하나 더 붙이고
            for i in range(idx, idx + l): # 테이프를 붙이면서 물이 새는 곳을 -1로 표시
                if i >= 1001:
                    break
                leaks[i] = -1
    return answer


n, l, leaks = solutionInput()
print(solution(n, l, leaks))



