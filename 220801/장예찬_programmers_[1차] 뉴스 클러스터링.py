from collections import defaultdict

def solution(str1, str2):
    str1, str2 = str1.upper(), str2.upper()

    str1List, str2List = slicing(str1), slicing(str2)

    eq1Dict, eq2Dict = defaultdict(int), defaultdict(int)
    for s in str1List:
        eq1Dict[s] += 1
    for s in str2List:
        eq2Dict[s] += 1

    if len(eq1Dict) > len(eq2Dict):
        largeDict = eq1Dict #참조복사
        smallDict = eq2Dict
    else:
        largeDict = eq2Dict  # 참조복사
        smallDict = eq1Dict

    eqCount, totalCount = 0, 0
    for s in largeDict:
        if s in smallDict:
            eqCount += smallDict[s] if smallDict[s] < largeDict[s] else largeDict[s]
            totalCount += smallDict[s] if smallDict[s] > largeDict[s] else largeDict[s]
            del smallDict[s]
        else:
            totalCount += largeDict[s]
    totalCount += sum(smallDict.values())
    if not totalCount:
        return 65536
    answer = int(eqCount * 65536 / totalCount)
    return answer


def slicing(s):
    return [s[i:i + 2] for i in range(len(s) - 1) if s[i:i + 2].isalpha()]

#print(solution("E=M*C^2", "	e=m*c^2"))