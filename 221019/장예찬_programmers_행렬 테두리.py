# https://school.programmers.co.kr/learn/courses/30/lessons/77485
# 예상 알고리즘: 구현
# 베스트 알고리즘: 구현

def solution(rows, columns, queries):
    answer = []
    board = initBoard(rows, columns)
    for query in queries:
        query = list(map(lambda x: x-1, query))
        answer.append(minValueWithRotate(*query, board))
    return answer

dxy = [(0, 1), (1, 0), (0, -1), (-1, 0)]

def initBoard(R, C):
    board = [[c + r*C for c in range(1, C+1)] for r in range(R)]
    return board

def minValueWithRotate(x1, y1, x2, y2, board):
    currX, currY = x1, y1
    dIdx = 0
    tempSwapValue = board[x1][y1]
    minValue = 10001
    while True:
        dx, dy = dxy[dIdx]
        newX, newY = currX + dx, currY + dy
        if newX == x1 - 1:
            break
        if newX == x2 + 1 or newY == y1 -1 or newY == y2 + 1:
            dIdx = (dIdx + 1)%4
            continue
        temp = board[newX][newY]
        minValue = min(tempSwapValue, minValue)
        board[newX][newY] = tempSwapValue
        
        tempSwapValue = temp
        currX, currY = newX, newY
    return minValue

print(solution(6, 6, [[2, 2, 5, 4], [3, 3, 6, 6], [5, 1, 6, 3]]))