# https://school.programmers.co.kr/learn/courses/30/lessons/49993
# 예상 알고리즘: 큐
# 베스트 알고리즘: 큐

from collections import deque
def solution(skill, skill_trees):
    answer = 0
    for st in skill_trees:
        queue = deque(list(skill))
        for s in st:
            if s in skill:
                if s != queue.popleft():
                    break
        else:
            answer += 1
    return answer