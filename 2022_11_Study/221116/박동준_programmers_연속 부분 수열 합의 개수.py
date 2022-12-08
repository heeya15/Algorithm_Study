def solution(elements):
    arr = set()
    length = len(elements)
    elements += elements
    
    for i in range(length):
        a = 0
        for j in range(length):
            a += elements[i+j]
            arr.add(a)
    
    return len(arr)