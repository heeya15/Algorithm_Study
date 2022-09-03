from itertools import product

def solution(word):
    wordList = ['A', 'E', 'I', 'O', 'U']
    perList = []
    for i in range(6):
        perList.extend([''.join(x) for x in product(wordList, repeat=i)])
    perList.sort()
    return perList.index(word)


print(solution("AAAAE"))