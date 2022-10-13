from collections import defaultdict

def solution(record):
    answer = []
    uid_dict = defaultdict(str)
    for r in record:
        arr = r.split(' ')

        if arr[0] == 'Enter':
            answer.append([arr[1], "님이 들어왔습니다."])
            uid_dict[arr[1]] = arr[2]
        elif arr[0] == 'Leave':
            answer.append([arr[1], "님이 나갔습니다."])
        else:
            uid_dict[arr[1]] = arr[2]

    for idx, ans in enumerate(answer):
        ans[0] = uid_dict[ans[0]]
        answer[idx] = ''.join(ans)
    return answer