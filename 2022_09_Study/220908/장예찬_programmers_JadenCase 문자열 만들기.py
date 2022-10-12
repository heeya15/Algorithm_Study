# https://www.acmicpc.net/problem/13397
'''
문제 설명
JadenCase란 모든 단어의 첫 문자가 대문자이고, 그 외의 알파벳은 소문자인 문자열입니다. 단, 첫 문자가 알파벳이 아닐 때에는 이어지는 알파벳은 소문자로 쓰면 됩니다. (첫 번째 입출력 예 참고)
문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.

제한 조건
s는 길이 1 이상 200 이하인 문자열입니다.
s는 알파벳과 숫자, 공백문자(" ")로 이루어져 있습니다.
숫자는 단어의 첫 문자로만 나옵니다.
숫자로만 이루어진 단어는 없습니다.
공백문자가 연속해서 나올 수 있습니다.
입출력 예
s	return
"3people unFollowed me"	"3people Unfollowed Me"
"for the last week"	"For The Last Week"
'''

# 풀이1
# 일부러 map과 split을 활용해서 최대한 for문의 N개수를 줄여보려고 노력함
# 근데 결국 find 함수 때문에 O(N)이 되어버림
# 추가적인 연산 덕분에 더 비효율적인 알고리즘이 됨

def solution(s:str):
    temp = s.lstrip()
    lc = len(s) - len(temp)
    s = temp
    rc = len(s) - len(s.rstrip())
    sList = list(map(lambda x: [x[0], x[1:]], s.split()))
    
    L = len(sList)
    blankList = [0] * L
    blankList[0] = lc
    i = -1
    for b in range(1, len(blankList)):        
        i = s.find(' ', i + 1) + 1
        if not i:
            break
        count=1
        while True:
            if s[i] != ' ':
                break
            i += 1
            count += 1
        blankList[b] = count
    sList = list(map(lambda i, x: [' ' * blankList[i], x[0], *x[1:]], range(L), sList))
    sList.append(" " * rc)
    for i in range(L):
        sList[i][1] = sList[i][1].upper()
        sList[i][2] = sList[i][2].lower()
        sList[i] = ''.join(sList[i])
    return ''.join(sList)

print(solution("   3people unFollowed me"))


# 풀이2
# 전형적인 O(N) 풀이법
# 코드가 짧고 추가적인 연산이 적음
def solution(s:str):
    sList,ss=[], ""
    for one in s:
        if one==' ':
            sList.extend([ss.capitalize(), ' '])
            ss=""
        else:
            ss+=one
    return ''.join(sList) + ss.capitalize()

print(solution("           3people unFollowed me"))
