
def solution(s):
    result =[]
    
    if len(s) == 1:
        return 1
    
    # 절반을 넘어가면 이미 압축이 아님
    for i in range(1,len(s)//2+1):
        # 자른다. i 만큼,
        word = ''
        count = 1
        w = s[:i]
        for j in range(i,len(s),i):
            if w == s[j:j+i]:
                count += 1
            else:
                if count != 1:
                    word += str(count) + w
                else:
                    word += w
                w = s[j:j+i]
                count = 1
        if count != 1:
            word += str(count) + w
        else:
            word += w
        
        result.append(len(word))
                
    
    return min(result)