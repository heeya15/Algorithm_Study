# 
# 예상 알고리즘:
# 베스트 알고리즘:

def solution(elements):
    elementsTemp = list(elements) + list(elements) #원형 수열이므로 두 번 붙여줌
    answerSet = set(elements)

    print(elements)
    # 길이가 1씩 늘어나는 원형 수열의 부분 수열의 합을 구함
    for i in range(1, len(elements)+1):
        for j in range(len(elements)):
            answerSet.add(sum(elementsTemp[j:j+i]))
    return len(answerSet)

# 테스트
print(solution([7, 9, 1, 1, 4])) # 18