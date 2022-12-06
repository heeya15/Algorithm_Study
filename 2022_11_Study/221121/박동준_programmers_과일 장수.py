def solution(k, m, score):
    answer = 0
    
    score.sort(key = lambda x : -x)
    
    for i in range(0,len(score),m):
        a = score[i:i+m]
        if len(a) < m:
            break
        min_num = min(a)
        answer += m*min_num
    return answer