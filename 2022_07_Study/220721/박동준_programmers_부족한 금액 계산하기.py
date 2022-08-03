def solution(price, money, count):
    answer = -1
    # 돌려서 뺀다
    for i in range(1,count+1):
        money -= i*price
    
    # 음수면 해당값 양수로 바꾸고 리턴
    if money < 0:
        answer = abs(money)
    else:
        answer = 0

    return answer