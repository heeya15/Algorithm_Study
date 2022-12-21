# https://school.programmers.co.kr/learn/courses/30/lessons/142086
# 예상 알고리즘: 딕셔너리
# 베스트 알고리즘: 딕셔너리(해시)

def solution(s):
    answer = []
    alphabetPos = {}
    for i, alpha in enumerate(s):
        if alpha in alphabetPos:
            answer.append(i - alphabetPos[alpha])
            alphabetPos[alpha]= i
        else:
            alphabetPos[alpha] = i      
            answer.append(-1)
    return answer 

print(solution("banana"))