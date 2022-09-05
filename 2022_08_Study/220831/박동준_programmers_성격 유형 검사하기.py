def solution(survey, choices):
    answer = ''
    dic = {'A':0,'N':0,'R':0,'T':0,'C':0,'F':0,'J':0,'M':0  }
    point = [3,2,1,0,1,2,3]
    st = ["RT","CF","JM","AN"]
    
    for i in range(len(choices)):
        style = survey[i]
        choice_num = choices[i] -1
        
        if choice_num  > 3:
            # 동의
            dic[style[1]] += point[choice_num]
        elif choice_num < 3:
            dic[style[0]] += point[choice_num]
        
    
    for i in range(4):
        # 미리 정렬
        print(sorted(st[i]))
        
        a = dic[st[i][0]]
        b = dic[st[i][1]]
        print(a,b)
        if a < b:
            answer += st[i][1]
        elif a > b:
            answer += st[i][0]
        elif a == b:
            answer += st[i][0]
    
    return answer