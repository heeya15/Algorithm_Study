# https://school.programmers.co.kr/learn/courses/30/lessons/12973
# 예상 알고리즘: 스택
# 베스트 알고리즘: 스택

def solution(arr):
    stack = []
    for curr in arr:
        if stack and stack[-1] == curr:
            stack.pop()
        else:
            stack.append(curr)
    return 1 if not stack else 0


print(solution("baabaa"))
print(solution("cdcd"))