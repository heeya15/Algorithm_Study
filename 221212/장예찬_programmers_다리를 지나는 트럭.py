# https://school.programmers.co.kr/learn/courses/30/lessons/42583
# 예상 알고리즘: 큐
# 베스트 알고리즘: 큐

from collections import deque

def solution(bridge_length, weight, truck_weights):
    truck_weights = deque(truck_weights)
    bridge = deque([0] * bridge_length)
    time = 0
    currWeight = 0
    while bridge:
        time += 1
        currWeight -= bridge.popleft()
        if truck_weights:
            if currWeight + truck_weights[0] <= weight:
                bridge.append(truck_weights.popleft())
                currWeight += bridge[-1]
            else:
                bridge.append(0)
    return time

print(solution(2, 10, [7,4,5,6]))