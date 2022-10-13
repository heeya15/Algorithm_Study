def solution(word):
    arr = ["A",'E','I','O',"U"]
    result = []    
    recur(0, arr,"", result)
    return result.index(word)+1

def recur(n,arr,spell, result):
    if n  == 5:
        return
    
    for i in range(5):
        spell += arr[i]
        result.append(spell)
        recur(n+1,arr,spell,result)
        spell = spell[:len(spell)-1]
