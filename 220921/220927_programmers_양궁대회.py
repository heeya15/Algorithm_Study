# https://school.programmers.co.kr/learn/courses/30/lessons/92342
# 예상 알고리즘: dfs
# 베스트 알고리즘: dfs
# 시간안에 풀기: 실패
# 해결 못한것들: 남은개수 쓰기 / -1 리턴 / 같은 점수차면 낮은수에 화살쓰기

# 솔루션 코드
def solution(n, info):
    answer = []     # 답안 리스트
    score = 0       # 어피치 라이언 점수차
    mintotal = 100000000000  # 점수 같을 때 화살 낮은 것 비교용

    def dfs(idx, remain, apeach, ryan, ans, total): # 인덱스, 남은수, 어피치 점수, 라이언 점수, 라이언화살리스트
        nonlocal answer, score, mintotal

        if idx > 10 and remain >= 0:                # 로직종료
            if score <= ryan - apeach:              # 점수차가 같거나 큼
                if score == ryan - apeach and total > mintotal:
                    return             # 더 적은 점수 화살을 적게 맞춘 경우 그냥 넘어감
                if remain:                          # 화살 남아있을때 모두 0점에 갱신
                    ans[-1] += remain
                mintotal = total  
                score, answer = ryan - apeach, ans   # 점수차 새로 기록, 정답 갱신
                if ans[5] == 2:
                    print(ans)  
                    print(total, mintotal)
            return
        point = 10-idx
        if info[idx] < remain:
            cnt = info[idx] + 1
            dfs(idx+1, remain-cnt, apeach, ryan+point, ans+[cnt], total+(10**cnt)*point)  # 이번 점수 이길때
        dfs(idx+1, remain, apeach+point if info[idx] else apeach, ryan, ans+[0], total)     # 이번 점수 질때

    dfs(0, n, 0, 0, [], 0)
    return answer if score else [-1]

maxScore = 0
isPick = [False] * 11
maxScoreList = []

def solution(n, info):    
    lion = [0] * 11
    recursion(n, info,lion, 0)
    return maxScoreList

# DFS 방식이랑 비슷
def recursion(remainArrow, apeach, lion, idx):
    global maxScore, isPick, maxScoreList
    #배열 선언후 maxScore때 의 배열 저장 
    if remainArrow == 0 or idx == 10:
        # 여기서 maxScore 갱신
        # if maxScore >= sum(lion)
        diffScore = calculateDiffScore(apeach, lion)
        if diffScore > maxScore:
            maxScore = diffScore
            maxScoreList = list(lion)
        return    

    # info[i] >= lion[i]
    # lion[i] = info[i]+1   
    consumeArrow = apeach[idx] + 1      #!
    if consumeArrow <= remainArrow:
        isPick[idx] = True # 쐈음!
        lion[idx] = consumeArrow
        recursion(remainArrow-consumeArrow, apeach, lion, idx+1)

    isPick[idx] = False # 쏘지 않았을 경우! 왜 = 조건  maxScore 가 여러개일경우 , 나중에것을 리턴 
    lion[idx] = 0
    recursion(remainArrow, apeach, lion, idx+1)

def calculateDiffScore(apeach, lion):
    apeachSum = lionSum = 0
    for i in range(11):
        if apeach[i] < lion[i]:
            lionSum += 10 - i
            continue
        
        if apeach[i] > 0:            
            apeachSum += 10 - i
    
    return lionSum - apeachSum
        
print(solution(	5, [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]))
    


#10번 화살                            쐈음                                                                   안쐈음
#9번 화살            쐈음                             안쐈음                              쐈음                                 안쐈음
#8번 화살       쐈음       안쐈음               쐈음         안쐈음                 쐈음        안쐈음                      쐈음        안쐈음                    