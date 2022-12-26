# https://school.programmers.co.kr/learn/courses/30/lessons/133502
# 예상 알고리즘: 스택
# 베스트 알고리즈: 스택

recipes = [1, 2, 3, 1]

def solution(ingredient):
    answer = 0
    tableStack = []
    for i in ingredient:
        tableStack.append(i)

        if tableStack[-4:] == recipes:
            for _ in range(4):
                tableStack.pop()
            answer += 1
    return answer

print(solution([2, 1, 1, 2, 3, 1, 2, 3, 1]))