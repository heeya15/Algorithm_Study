N = int(input())
arr = list(map(int, input().split()))
max_num = 0
def recur(cur,arr,num):
    global max_num
    
    if cur == N-2:
        max_num = max(max_num,num)
        return
        
    for i in range(len(arr)):
        # 첫째자리와 끝자리는 못뺌
        if i == 0 or i == len(arr)-1 :
            continue
        
        copy = arr[:]
        num  += copy[i-1]*copy[i+1]
        a = copy.pop(i)
        recur(cur +1, copy, num)
        copy.insert(i,a)
        num  -= copy[i-1]*copy[i+1]

        
recur(0,arr,0)

print(max_num)