#수열로 풀기
def solution(price, money, count):
    answer = ((count + 1)*price) * count / 2 - money
    return 0 if answer < 0 else answer