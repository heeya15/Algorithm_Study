# https://school.programmers.co.kr/learn/courses/30/lessons/132267?language=python3
# 예상 알고리즘: 재귀
# 베스트 알고리즘: 재귀

import sys
sys.setrecursionlimit(1000000)
def solution(a, b, n):
    k = n//a
    l = b*k
    if k > 0:
        return l + solution(a, b, n - a*k + l)
    return 0

print(solution(2, 1, 20))