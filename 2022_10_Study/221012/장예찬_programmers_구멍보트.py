# https://school.programmers.co.kr/learn/courses/30/lessons/42885
# 예상 알고리즘: 그리디
# 베스트 알고리즘: 그리디

def solution(people, limit):
    people.sort()
    i, j = 0, len(people)-1
    while i<j:
        if people[i]+people[j] <= limit:
            i+=1
        j-=1
    return len(people) - i


print(solution([70, 50, 80, 50], 100))