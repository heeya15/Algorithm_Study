import math
from collections import deque
def solution(progresses, speeds):
    answer = []    
    need_times = [math.ceil((100-p)/s) for p, s in list(zip(progresses,speeds))]

    q = deque(need_times)
    prev = q.popleft()
    count = 1
    while True:
        if not len(q):
            answer.append(count)
            break
        elif prev < q[0]:
            prev = q.popleft()
            answer.append(count)
            count = 1
        else:
            q.popleft()
            count += 1
    return answer