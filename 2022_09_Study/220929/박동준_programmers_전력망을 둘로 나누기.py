def solution(n, wires):
    answer = -1

    par = list(range(n+1))
    rank = [0 for _ in range(n+1)]
    

    def find(x):
        if par[x] ==x:
            return x
        else:
            par[x] = find(par[x])
            return par[x]
    
    def union(x,y):
        x = find(x)
        y = find(y)
        if x != y:
            par[x] = y
        
        # if rank[x] < rank[y]:
        #     par[x] = y
        # elif rank[x] > rank[y]:
        #     par[y] = x
        # else:
        #     par[x] = y
        #     rank[y] += 1
    answer = 99999999999999999999
    result = 99999999999999999999
    for i in range(len(wires)):
        par = list(range(n+1))
        rank = [0 for _ in range(n+1)]
        for a,b in (wires[:i] + wires[i+1:]):
            if find(a) != find(b):
                union(a,b)
    
        dic = {}
        for k in range(1, n+1):
            a  = find(k)
            if dic.get(a) == None:
                dic[a] = 1
            else:
                dic[a] += 1
    return result
