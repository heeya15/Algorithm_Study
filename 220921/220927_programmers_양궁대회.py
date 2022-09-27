# https://school.programmers.co.kr/learn/courses/30/lessons/92342
# 예상 알고리즘: dfs
# 베스트 알고리즘: dfs
# 시간안에 풀기: 실패
# 해결 못한것들: 남은개수 쓰기 / -1 리턴 / 같은 점수차면 낮은수에 화살쓰기


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