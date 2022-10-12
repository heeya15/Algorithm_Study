# https://school.programmers.co.kr/learn/courses/30/lessons/118668
# 예상 풀이: DP
# 베스트 풀이: DP, Dijkstra

class Solution:
    def __init__(self, alp, cop, problems):
        problemsTransform = list(zip(*problems))
        self.problems = problems
        self.alpMax, self.copMax = max(problemsTransform[0]), max(problemsTransform[1])
        self.alp, self.cop = min(self.alpMax, alp), min(self.copMax, cop)
        self.alpLen, self.copLen = self.alpMax+2, self.copMax+2
        self.dpBoard =  [[151 for _ in range(self.copLen)] for _ in range(self.alpLen)]
        self.dpBoard[self.alp][self.cop] = 0

    def solve(self):
        for i in range(self.alp, self.alpMax+1):
            for j in range(self.cop, self.copMax+1):
                self.dpBoard[i+1][j] = min(self.dpBoard[i+1][j], self.dpBoard[i][j]+1)
                self.dpBoard[i][j+1] = min(self.dpBoard[i][j+1], self.dpBoard[i][j]+1)
                for alp_req, cop_req, alp_rwd, cop_rwd, cost in self.problems:
                    if i >= alp_req and j >= cop_req:
                        ni, nj = min(self.alpMax, i + alp_rwd), min(self.copMax,j + cop_rwd)
                        self.dpBoard[ni][nj] = min(self.dpBoard[ni][nj], self.dpBoard[i][j]+cost)
        return self.dpBoard[self.alpMax][self.copMax]

def solution(alp, cop, problems):
    return Solution(alp, cop, problems).solve()

print(solution(10,	10,	[[10,15,2,1,2],[20,20,3,3,4]]))