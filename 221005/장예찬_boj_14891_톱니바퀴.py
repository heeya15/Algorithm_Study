# https://www.acmicpc.net/problem/14891
# 예상 알고리즘: 구현
# 베스트 알고리즘

from sys import stdin
GEAR_NUM, TIK_NUM = 4, 8
RIGHT_POS, LEFT_POS = 2, 6
GEARS_LEFT = set([1, 2, 3])
GEARS_RIGHT = set([0, 1, 2])

def rotate(gear, direction):
    bit = int('0b' + ''.join(map(str, gear)), 2)
    answer = []
    if direction == 1:
        bit = bit >> 1
        answer = list(map(int, list(format(bit, 'b').zfill(len(gear)))))
        answer[0] = gear[TIK_NUM - 1]
    else:
        bit = (bit << 1) & 255
        answer = list(map(int, list(format(bit, 'b').zfill(len(gear)))))
        answer[TIK_NUM - 1] = gear[0]
    for i in range(TIK_NUM):
        gear[i] = answer[i]

def rotateDirect(gearNum, gearIndexes, leftRight, stop, direction, gears):
    realGearNum = gearNum
    directs = (RIGHT_POS, LEFT_POS) if leftRight == -1 else (LEFT_POS, RIGHT_POS)
    
    while True: 
        gearNum += leftRight
        if gearNum == stop or gearNum not in gearIndexes or gears[gearNum][directs[0]] == gears[realGearNum][directs[1]]:
            print(gearNum, stop)
            print(gearNum, gearIndexes)
            print(gearNum, realGearNum)
            print(directs)
            print(gears[gearNum][directs[0]], gears[realGearNum][directs[1]])
            break
        
        print("응애")
        direction *= -1 
        rotate(gears[gearNum], direction)
        realGearNum += leftRight

def calculateScore(gears):
    score = 0
    for i, gear in enumerate(gears):
        if gear[0] == 1:
            score += 2**i
    return score



def solution(gears, rotates):
    for gearNum, direction in rotates:
        realGear = gearNum - 1
        print("////////////////")

        rotateDirect(realGear, GEARS_RIGHT, -1, -1, direction, gears)
        # rotateDirect(realGear, GEARS_LEFT, 1, GEAR_NUM, direction, gears)
        rotate(gears[realGear], direction)

        
        for gear in gears:
            print(gear)
        print()

    answer = calculateScore(gears)
    return answer


def solutionInput():
    gears = [list(map(int, list(stdin.readline().strip()))) for _ in range(GEAR_NUM)]
    k = int(stdin.readline())
    rotates = [list(map(int, stdin.readline().split())) for _ in range(k)]
    return gears, rotates

print(solution(*solutionInput()))

# print(-1 % 8)