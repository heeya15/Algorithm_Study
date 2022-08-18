def recur(cur,end,arr,combi,visit):
    if cur == end:
        a = arr[:]
        combi.append(a)
    
    for i in range(end):
        if visit[i] == 1:
            continue
        
        visit[i] = 1
        arr.append(i)
        recur(cur+1,end,arr,combi,visit)
        arr.pop()
        visit[i] = 0
    

def solution(k, dungeons):
    answer = -1
    # 순서가 있는 조합
    combi =[]
    end = len(dungeons)
    visit = [0]*end
    recur(0,end,[],combi,visit)
    
    for order in combi:
        piro = k
        cnt = 0
        for num in order:
            if dungeons[num][0] > piro:
                continue
            else:
                piro -= dungeons[num][1]
                cnt += 1
        answer = max(answer,cnt)
    return answer