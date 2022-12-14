# https://school.programmers.co.kr/learn/courses/30/lessons/42883
# 베스트 알고리즘: 스택
# 예상 알고리즘: 스택

def solution(number, k):
    nList = [number[0]]
    for num in number[1:]:
        while len(nList) > 0 and nList[-1] < num and k > 0: 
        #바꾸는 경우- 새로들어온수가 기존수보다 크거나, k가 0이 안됐거나, nList 0개가 아닐때 
            k -= 1
            nList.pop()
        nList.append(num) #항상 append
    if k != 0: #for문 다 돌았는데 k가 안찼을때 
        nList = nList[:-k]
    return ''.join(nList)