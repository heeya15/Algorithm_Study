count = 0
answer = []
lions = 0
lions_arr = []
def recur(cur,num,arr,n,info):
    global count, answer,lions,lions_arr
    if num > n:
        return
    
    if cur == 11 and num == n:
        apeach, rion = 0,0
        for k in range(11):
            if arr[k] == 0 and info[k] == 0:
                continue
            elif arr[k] > info[k]:
                rion += 10 - k
            elif arr[k] <= info[k]:
                apeach += 10 -k
        
        if rion > apeach and count <= (rion - apeach):
            if count == (rion- apeach) and lions_arr:
                l_count = 0
                p_count = 0
                for z in range(10,-1,-1):
                    if lions_arr[z] == 0 and arr[z] == 0:
                        continue
                    
                    l_count += lions_arr[z]
                    p_count += arr[z]
                    if l_count == p_count:
                        continue
                    elif l_count > p_count:
                        break
                    else:
                        a = arr[:]
                        lions_arr = a
                        answer = a
                        lions = rion
                        
            elif count < rion-apeach:    
                a = arr[:]
                lions = rion
                answer = a 
                lions_arr = a
                count = rion - apeach
        return
    elif cur == 11 and num < n:
        return
    
    
    for i in range(n,-1,-1):
        arr[cur] = i
        recur(cur+1,num+i, arr,n,info)
        arr[cur] = 0
        
            

def solution(n, info):
    global answer
    check_number = 0
    arr = [0]*11
    print(arr)
    recur(0,0,arr,n,info)
    if count == 0:
        return [-1]
    
    return answer