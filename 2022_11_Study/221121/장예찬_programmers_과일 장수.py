# https://school.programmers.co.kr/learn/courses/30/lessons/135808
# 예상 알고리즘: 정렬
# 베스트 알고리즘: 정렬

def solution(k, m, score):
    return sum([appleScore * m for idx, appleScore in enumerate(sorted(score, reverse=True), 1) if idx%m == 0])


print(solution(3, 4, [1, 2, 3, 1, 2, 3, 1]))

print(solution(4, 3, [4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2]))