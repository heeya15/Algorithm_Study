# https://school.programmers.co.kr/learn/courses/30/lessons/133501
# 베스트 알고리즘: 정렬
# 예상 알고리즘: 정렬

def solution(distance, scope, times):
    for i in range(len(scope)):
        start, end = sorted(scope[i])
        workTime, restTime = times[i]
        for time in range(start, end+1):
            repeatTime = time % (workTime + restTime)
            if 0 < repeatTime <= workTime:
                distance = min(distance, time)
                break
    return distance

print(solution(10, [[3, 4], [5, 8]], [[2, 5], [4, 3]]))