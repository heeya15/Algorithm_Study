# https://www.acmicpc.net/problem/1162
# 예상 알고리즘:
# 베스트 알고리즘:
from sys import stdin
from typing import List, Tuple
from collections import defaultdict

class Problem:
    def __init__(self, info: List[int], times: List[int]):
        self.info = info
        self.times = times
        self.map = defaultdict(list)
        return

    def solve(self):
        

        return


    def problemInput() -> Tuple[int]:        
        info = list(map(int, stdin.readline().split()))
        times = [list(map(int, stdin.readline().split())) for _ in range(info[1])]
        return info, times


def solution(info: List[int], times: List[int]) -> int:
    return Problem(info,times).solve()


info, times = Problem.problemInput()
print(solution(info, times))