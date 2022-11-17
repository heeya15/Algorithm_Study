# https://school.programmers.co.kr/learn/courses/30/lessons/17687
# 예상 알고리즘: 수학
# 베스트 알고리즘: 수학

def solution(n, t, m, p):
    # p번째 사람이 말해야 하는 단어들을 저장
    answer = []
    # 0부터 n진법으로 변환한 수를 저장
    converted = ''
    # 0부터 t * m까지 n진법으로 변환한 수를 저장
    for i in range(t * m):
        converted += convert(i, n)

    # p번째 사람이 말해야 하는 단어들을 저장
    for i in range(p - 1, t * m, m):
        answer.append(converted[i])

    return ''.join(answer)

# 10진법으로 표현된 수를 n진법으로 변환
def convert(num, n):
    # 변환된 수를 저장
    converted = ''
    # 10진법으로 표현된 수를 n진법으로 변환
    while num > 0:
        # 10진법으로 표현된 수를 n으로 나눈 몫과 나머지를 저장
        num, remainder = divmod(num, n)

        # 나머지가 10보다 작으면 그대로 저장
        if remainder < 10:
            converted = str(remainder) + converted
        # 나머지가 10보다 크면 A부터 F까지의 문자로 변환하여 저장
        else:
            converted = chr(remainder + 55) + converted
    converted = converted if converted else '0'
    
    return converted

# 테스트
print(solution(2, 4, 2, 1)) # "0111"
print(solution(16, 16, 2, 1)) # "02468ACE11111111"
print(solution(16, 16, 2, 2)) # "13579BDF01234567"