# https://school.programmers.co.kr/learn/courses/30/lessons/118668
# 예상 풀이: DP
# 베스트 풀이

'''
            코딩력
            
                0   1   2   3   4   5   6   7   8   9   10  11  12
알고력
0               0   1   2   3   4   5   6   7   8   9   10  11  12
1               1   2   3   4   5   6   7   8   9   10  11  12  13
2               2   3   4   5   6   7   8   9   10  11  12  13  14
3               3
4               4
5               5
6               6
7               7
'''

class Solution:
    def __init__(self, alp, cop, problems):
        self.alp, self.cop = alp, cop
        problemsTransform = list(zip(*problems))
        self.problems = problems
        self.alpMax, self.copMax = max(problemsTransform[0]), max(problemsTransform[1])
        self.alpCopMax = max(self.alpMax, self.copMax)
        self.alpLen, self.copLen = self.alpMax - self.alp, self.copMax-self.cop

        self.dpBoard =  [[0 for _ in range(self.copLen+1)] for _ in range(self.alpLen+1)]

    def solve(self):
        for alp_req, cop_req, alp_rwd, cop_rwd, cost in self.problems:
            alp_req, cop_req = alp_req - self.alp, cop_req - self.cop
            cnt = 1
            while True:
                nx, ny = alp_req+cnt*alp_rwd, cop_req+cnt*cop_rwd
                if nx > self.alpLen or ny > self.copLen:
                    break
                temp = self.dpBoard[alp_req][cop_req] + alp_req+cop_req + cost*cnt
                if temp < nx+ny + self.dpBoard[nx][ny]:
                    self.dpBoard[nx][ny] -= (nx+ny + self.dpBoard[nx][ny])-temp
                cnt += 1 
        for s in self.dpBoard:
            print(s)
        return self.alpLen + self.copLen + self.dpBoard[self.alpLen][self.copLen]

def solution(alp, cop, problems):
    return Solution(alp, cop, problems).solve()

print(solution(10,	10,	[[10,15,2,1,2],[20,20,3,3,4]]))