def solution(s):
    answer = -1
    # 자 앞에서 부터 차례대로 넣어줘요
    
    stack = []
    
    for i in range(len(s)):
        # 만약 스택에 값이 없거나, 맨뒤의 값이랑 지금 값이 다르다면 스택에 추가
        # 아니라면 -> 쌍이 지어졌다는거니까 pop
        if not stack or stack[-1] != s[i]:
            stack.append(s[i])
        else:
            stack.pop()
    if stack:
        return 0
    else:
        return 1