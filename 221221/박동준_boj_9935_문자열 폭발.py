# 100만 반복문 1번으로 처리 

# 맨 끝 문자가 나왔을 때 길이 -번째까지 일치하면 제거 해주기

word = list(input())
boom = list(input())
stack = []
count = 0
# for i in range(len(word)-len(boom)+1):
#     if word[i:i+len(boom)] == boom:
#         count += 1

for i in range(len(word)):
    spell = word[i]
    # 추가 해 주고
    print(spell)
    stack.append(spell)
    if spell == boom[-1]:
        print(stack,'들어옴')
        check = stack[-len(boom):]
        print(check,'끝에숫자가 같아용')
        if check == boom:
            for _ in range(len(boom)):
                stack.pop()


if stack:
    print("".join(stack))
else:
    print("FRULA")