def solution(id_list, reports, k):
    # 신고를 누구로부터 받았는지 확인하기위한 dict 생성   
    dic_user = dict()
    for i in id_list:
        dic_user[i] = []
    
    # 정지된 아이디를 체크하기 위한 빈 배열
    answer = [0] * len(id_list)
    
    # set을 이용하여 동일한 신고결과 제거 후 데이터 가공
    for report in set(reports):
        report = report.split(' ')
        dic_user[report[1]].append(report[0])
    
    for i in dic_user:
        # 해당 유저가 신고받은 횟수 == list의 길이
        a = dic_user[i]
        if len(a) >= k:
            # 신고한 유저에게 메일을 송부
            for get_mail_user in a:
                answer[id_list.index(get_mail_user)] += 1

    return answer