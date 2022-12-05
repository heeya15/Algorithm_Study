# https://school.programmers.co.kr/learn/courses/30/lessons/131704
# 예상 알고리즘: 스택
# 베스트 알고리즘: 스택

def solution(order):
    answer = 0
    stack = [0]
    curr = 1
    for o in order:
        if o > curr:
            for i in range(curr, o):
                stack.append(i)
            curr = o + 1
        elif o == curr:
            curr += 1
        elif o == stack[-1]:
            stack.pop()
        else:
            return answer           
        answer += 1
    return answer

print(solution([2, 1, 6, 7, 5, 8, 4, 9, 3, 10]))