# https://school.programmers.co.kr/learn/courses/30/lessons/42890
# 예상 알고리즘: 조합
# 베스트 알고리즘: 조합

from itertools import combinations

def solution(relation):
    # 모든 조합 구하기
    allKeys = findKeys(relation)    
    
    # 유니크키 구하기 (유일성 만족)
    uniqueKeys = findUnique(relation, allKeys)
            
    # 후보키 구하기 (유일성 + 최소성 만족)
    candidateKeys = findMinimal(uniqueKeys)
    
    return len(candidateKeys)

def findKeys(relation):
    keyList = []
    rLen = len(relation[0])
    allAttr = [i for i in range(rLen)]
    for i in range(1, rLen + 1):
        keyList.extend(list(combinations(allAttr, i)))
    return keyList

def findUnique(relation, keys):
    uniqueKeys = []
    subRelation = set()
    rSize = len(relation)
    for key in keys:
        subRelation.clear()
        for i in range(rSize):
            subRelation.add(tuple(relation[i][k] for k in range(rSize) if k in key))
        if len(subRelation) == rSize:
            uniqueKeys.append(key)
    return uniqueKeys
            
def findMinimal(uniqueKeys):
    minimalKeys = []
    cSize = len(uniqueKeys)
    for i, uniqueKey in enumerate(uniqueKeys):
        if len(uniqueKey) == 0: 
            continue
        minimalKeys.append(uniqueKey)
        for c in range(cSize):
            if i == c or len(uniqueKeys[c]) == 0:
                continue
            current = set(list(uniqueKey))
            target = set(list(uniqueKeys[c]))
            if current.issubset(target):
                uniqueKeys[c] = ()
    return minimalKeys


print(solution([['a',1,'aaa','c','ng'],['b',1,'bbb','c','g'],['c',1,'aaa','d','ng'],['d',2,'bbb','d','ng']]))