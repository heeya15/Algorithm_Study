from collections import defaultdict
def solution(survey, choices):
    Leng = len(survey)
    choices = list(map(lambda x:x-4, choices))
    case = defaultdict.fromkeys(["RT","TR","CF","FC","JM","MJ","AN","NA"], 0)
    cases = [["RT","TR"], ["CF","FC"], ["JM","MJ"], ["AN","NA"]]

    for i in range(Leng):
        case[survey[i]] += choices[i]

    answer = ""
    for l, r in cases:
        temp = case[l] - case[r]
        if temp > 0:
            answer += r[0]
        else:
            answer += l[0]
    return answer
# 테스트
# print(solution(["AN", "CF", "MJ", "RT", "NA"],[5, 3, 2, 7, 5]))