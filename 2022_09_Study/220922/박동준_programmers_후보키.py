## index의 조합을 찾는다


def solution(relation):
    import itertools
    
    result = 0
    # 컬럼의 수
    colom_length = len(relation[0])
    # 총 데이터의 수
    data_length = len(relation)

    hubo_list = []
    for i in range(colom_length):
        check_arr = [[] for _ in range(colom_length)]
        
        # 한개짜리로 가능한 후보키를 먼저 뽑아낸다.
        if i == 0:
            for k in range(colom_length):
                for j in range(data_length):
                # j번째 행의 i번째 값, i == colum이기때문에 열별 연산가능
                    check_arr[k].append(relation[j][k])
            for zz in range(colom_length):
                if len(set(check_arr[zz])) == data_length:
                    hubo_list.append((zz,))
            
        else:
            combination_arr = []
            combi=[]
            def recur(start,n):
                if n == i+1 and len(combi) == i+1:
                    copy_arr = combi[:]
                    combination_arr.append(copy_arr)
                    return

                for z in range(start,colom_length):
                    combi.append(z)
                    recur(z+1,n+1)
                    combi.pop()
                        

            recur(0,0)
            # 각 조합이 나옴 
            for combis in combination_arr:
                hap = [[] for _ in range(data_length)]
                for col in combis:
                    for j in range(data_length):
                        hap[j].append(relation[j][col])
                
                
                hap = list(map(tuple, hap))
                if len(hap) == len(set(hap)):
                    bubun = []
                    for x in range(1,len(combis)+1):
                        bubun.append(list(itertools.combinations(combis, x)))

                    bubububu = []
                    for xxxx in bubun:
                        for yyy in xxxx:
                            bubububu.append(yyy)
                            
                    for huhu in bubububu:
                        if huhu in hubo_list:
                            break
                    else:
                        hubo_list.append(tuple(combis))
                    
                    
                    
    result = len(hubo_list)
    return result