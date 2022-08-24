dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def checkCrossLine(x, y, place):
    for j in range(4): #사방탐색
        for i in range(1, 3):  # 사방별 멘헤튼 거리
            nx = x + dx[j] * i
            ny = y + dy[j] * i
            if 0 <= nx < 5 and 0 <= ny < 5:
                if place[nx][ny] == 'P':
                    return False
                if place[nx][ny] == 'X':
                    break
    return True


dx2 = [-1, 1, -1, 1]
dy2 = [-1, -1, 1, 1]

def checkDiagonal(x, y, place):
    for i in range(4): #사방탐색
        nx = x + dx2[i]
        ny = y + dy2[i]
        if 0 <= nx < 5 and 0 <= ny < 5:
            if place[nx][ny] == 'P': #만약 사람이 있다면
                if not(place[x][ny] == 'X' and place[nx][y] == 'X'):
                    return False
    return True

def solution(places):
    answer = []
    for place in places:
        for x in range(5):
            for y in range(5):
                if place[x][y] == 'P':
                    if not(checkCrossLine(x, y, place) and checkDiagonal(x, y, place)):
                        break
            else:
                continue
            break
        else:
            answer.append(1)
            continue
        answer.append(0)
    return answer





print(solution([["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]))