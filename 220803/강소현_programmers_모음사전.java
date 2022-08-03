import java.util.*;

class Solution {
    
    static String[] vowels = {"A", "E", "I", "O", "U"};
    static ArrayList<String> list = new ArrayList<>();
    public int solution(String word) {    
        Solve("", 0);
    
        return list.indexOf(word) + 1;
    }
    
    // 부분집합
    static void Solve(String str, int cnt) {
        if(cnt == 5) {
            return;
        }
        
        for(int i = 0; i < vowels.length; i++) {
            list.add(str + vowels[i]);
            Solve(str + vowels[i], cnt + 1);
        }
    }
}