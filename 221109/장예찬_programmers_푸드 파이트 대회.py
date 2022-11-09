# https://school.programmers.co.kr/learn/courses/30/lessons/134240
# 예상 알고리즘: 문자열
# 베스트 알고리즘: 문자열

def solution(food):
    foodStrings = ''.join(map(lambda x: str(x[0])*(x[1]//2), enumerate(food)))
    answer = foodStrings + '0' + foodStrings[::-1]
    return answer

print(solution([1, 3, 4, 6]))