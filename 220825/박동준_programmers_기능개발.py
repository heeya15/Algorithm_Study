def solution(progresses, speeds):
    answer = []
    num = 0
    while num < len(progresses):
        # 일단 하루 체크
        for i in range(num,len(progresses)):
            progresses[i] += speeds[i]
        
        cnt = 0
        for k in range(num,len(progresses)):
            if progresses[num] >= 100:
                cnt += 1
                num += 1
            else:
                break
        
        if cnt:
            answer.append(cnt)
    return answer