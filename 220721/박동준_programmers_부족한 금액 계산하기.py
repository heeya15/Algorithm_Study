def solution(price, money, count):
    answer = -1
    for i in range(1,count+1):
        money -= i*price
    
    if money < 0:
        answer = abs(money)
    else:
        answer = 0
    
    return answer