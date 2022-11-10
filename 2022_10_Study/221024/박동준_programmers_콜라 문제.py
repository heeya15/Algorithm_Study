import math
def solution(a, b, n):
    answer = 0
    number = n
    while True:
        mox = math.floor( number / a)
        na = number % a
        number = number - mox*a + mox*b
        answer += mox*b
        
        if number <a:
            break

    
    return answer