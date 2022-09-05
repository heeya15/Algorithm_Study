from collections import deque

def solution(queue1, queue2):
    answer = -2
    total = sum(queue1) + sum(queue2)
    if total%2 == 1:
        return -1
    length = len(queue1)
    count = 0
    q1 = deque(queue1)
    q2 = deque(queue2)
    
    q1sum  = sum(q1)
    q2sum = sum(q2)
    while True:
        if count > length*3: 
            return -1
        
        if q1sum == q2sum:
            return count
        
        elif q1sum > q2sum:
            a = q1.popleft()
            q1sum -=a
            q2sum +=a
            q2.append(a)
        
        elif q1sum < q2sum:
            a = q2.popleft()
            q2sum -=a
            q1sum +=a
            q1.append(a)
        
        count +=1
    
    
    return answer