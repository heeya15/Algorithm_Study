def solution(n, t, m, p):
    result = ""
    # 진수에 맞는 숫자 구하기
    def division(num,n):
        # 모든 숫자 리스트
        num_list = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F']
        
        a = num%n # 나머지
        b = num//n # 몫
        # 몫이 0이 아니면 아직 더 나눌 수 있음
        # ex 3//2 1,1 -> 0, 1 -> 1,1 -> 11 이진법의 3
        if b != 0:
            return division(b,n) + num_list[a]
        else:
            return num_list[a]
    
    for i in range(100000):
        result += division(i,n)
        
        if i == t*m:
            break
    answer = ''
    for k in range(p-1,len(result),m):
        answer += result[k]
        
        if len(answer) == t:
            break
        
    
    return answer