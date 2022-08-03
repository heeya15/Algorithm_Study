
arr  = [[] for _ in range(9) ]

count = 0
zero = []
# 배열 반영
for i in range(9):
    a = input()
    for num in range(9):
        if int(a[num]) == 0:
            count += 1
            zero.append([i,num])
        arr[i].append(int(a[num]))
        
def rowcolCheck(x,y,num):
    for i in range(9):
        if arr[x][i] == num:
            return False
    
    for j in range(9):
        if arr[j][y] == num:
            return False
    
    return True


def boxCheck(x,y,num):
    nx = (x//3)*3
    ny = (y//3)*3
    for i in range(3):
        for j in range(3):
            if arr[nx+i][ny+j] == num:
                return False
    return True


# 백트래킹

def recur(num):
    # 종료조건에 도달하면 바로 끝,
    if num == count:
        for x in range(9):
            for y in range(9):
                print(arr[x][y], end="")
            print()
        exit()

    row, col = zero[num]
    for n in range(1,10):
        if rowcolCheck(row,col,n) and boxCheck(row,col,n):
            arr[row][col] = n
            recur(num + 1)
            arr[row][col] = 0

recur(0)
