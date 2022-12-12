def solution(bridge_length, weight, truck_weights):
    answer = 0
    
    # 스택/큐를 이용하여, truckweights의 값을 하나씩 스택에 이동,
    # 스택 조건, 길이만큼 확인
    stack= [0] *bridge_length
    cnt = 0
    bg_weight = 0
    while truck_weights:
        # 모든 트럭이 빠질때 까지, 그럼 그 길이만큼 마지막에 더해주면됨
        a = stack.pop(0)
        cnt += 1
        bg_weight -= a
        check = bg_weight + truck_weights[0]
        if check <= weight:
            stack.append(truck_weights[0])
            truck_weights = truck_weights[1:]
            bg_weight = check
        else:
            stack.append(0)

    answer = cnt + len(stack)
    return answer