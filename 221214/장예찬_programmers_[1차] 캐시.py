# https://school.programmers.co.kr/learn/courses/30/lessons/17680
# 예상 알고리즘: 큐
# 베스트 알고리즘: 큐
from collections import deque

def solution(cacheSize, cities):
    answer = 0
    cache = set([])
    cacheQueue = deque([])
    # cacheSize가 0이면 모든 도시에 대해 캐시 미스
    if cacheSize == 0:
        return len(cities) * 5
    
    # 캐시 크기가 0보다 크면
    for city in cities:
        city = city.lower() # 대소문자 구분 없음

        # 캐시에 존재하면
        if city in cache:
            answer += 1
            cacheQueue.remove(city) # 캐시 큐에서 제거 후
            cacheQueue.append(city) # 캐시 큐 마지막에 추가
        # 캐시에 존재하지 않으면
        else: 
            answer += 5
            if len(cache) == cacheSize:
                cache.remove(cacheQueue.popleft()) # 캐시 큐와 캐시셋에서 제거 후
            cache.add(city) # 새로운 시티를 캐시에 추가
            cacheQueue.append(city) # 새로운 시티 캐시 큐 마지막에 추가
    return answer


print(solution(3, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"])) # 50
print(solution(	3, ["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]))