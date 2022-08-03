def solution(s):
    # answer의 최대 크기
    answer = len(s)
    # s 길이 절반보다 크게 압축할 수는 없다
    for i in range(1, len(s) // 2 + 1):
        # 단위별로 합쳐서 out에 저장
        out = [s[k:k + i] for k in range(0, len(s), i)]

        # compress = 가장 앞부분, line = 완성품
        compress, count, line = out[0], 1, ""
        for o in range(1, len(out)):
            # compress가 out[index]와 같은 경우
            if compress == out[o]:
                count += 1
            else:  # 다른 경우
                # 문자열 앞에 count 현재까지의 숫자를 이어붙임. 1이면 생략됨.
                line += sumString(count, compress)
                compress, count = out[o], 1
                # 맨 마지막에 실행 맨마지막이 compress와 같아지는 경우
        if compress == out[o]:
            line += sumString(count, compress)
        answer = min(answer, len(line))
    return answer


def sumString(count, string):
    return ("" if count == 1 else str(count)) + string