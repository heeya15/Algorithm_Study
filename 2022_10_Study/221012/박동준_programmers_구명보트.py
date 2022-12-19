def solution(people, limit):
    answer = 0
    
    # people 숫자 작은 순으로 정렬
    people.sort()
    length = len(people) -1
    l,r = 0,length
    sumofpeople = 0
    while l <= r:
        answer += 1
        if people[l] + people[r] <= limit:
            l += 1
        r -= 1
    return answer