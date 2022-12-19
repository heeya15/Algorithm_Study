def solution(number, k):
    answer = number[0]
    
    # k개의 수를 제거했을 때
    # 자 answer에 하나씩 숫자를 더해줌,
    # 그리고 다음 숫자가 만약 크면 제거 후 제거 한 숫자의 갯수 k 카운트
    # 다음 숫자가 나보다 작으면 일단 넣음
    # 같으면 pass
    num  = 0
    for i in range(1,len(number)):
        if int(answer[-1]) < int(number[i]):
            while answer and int(answer[-1]) < int(number[i]):
                answer = answer[:-1]
                k -= 1
                if k == 0: 
                    break
            answer += number[i]
        else:
            answer += number[i]
        
        if k == 0:
            num = i
            break
    if k == 0:
        answer += number[i+1:]
    else:
        answer = answer[:len(number)-k]
    return answer