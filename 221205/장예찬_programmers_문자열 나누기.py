# https://school.programmers.co.kr/learn/courses/30/lessons/140108
# 예상 알고리즘: 문자열
# 베스트 알고리즘: 문자열

def solution(s):
    answer = 0
    one, two, temp = 0, 0, 0
    for curr in s:
        if one == two:
            answer += 1
            temp = curr
        if temp == curr:
            one += 1
        else:
            two += 1
    return answer


print(solution("banana"))
print(solution("abracadabra"))
print(solution("aaabbaccccabba"))