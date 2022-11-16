# https://school.programmers.co.kr/learn/courses/30/lessons/131701
# 예상 알고리즘: 구현
# 베스트 알고리즘: 구현

def solution(elements):
    elementsTemp = [[idx, d] for idx, d in enumerate(elements)] # 초기 길이가 1인 부분수열의 인덱스와 값들을 저장
    answerSet = set(elements)

    length = len(elements)
    for _ in range(len(elements) - 1): # 부분수열의 길이가 1인 경우는 이미 구했으므로 length - 1번 반복
        for temp in elementsTemp:
            # 원형으로 인덱스를 1 증가시킴
            temp[0] = (temp[0] + 1) % length
            temp[1] += elements[temp[0]] # 값에 다음 인덱스의 값을 더함
            answerSet.add(temp[1]) # 부분수열의 합을 저장

    return len(answerSet)

# 테스트
print(solution([7, 9, 1, 1, 4])) # 18