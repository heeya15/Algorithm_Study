def solution(skill, skill_trees):
    answer = -1
    arr =  []
    for i in skill_trees:
        word = ""
        for j in i:
            if j in skill:
                word += j
        arr.append(word)
    count = 0
    for k in arr:
        if k in skill and skill[:len(k)] == k:
            count += 1

    return count