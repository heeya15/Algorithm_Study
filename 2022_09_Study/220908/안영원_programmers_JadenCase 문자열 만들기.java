import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        s = s.toLowerCase();
        
        char first = s.charAt(0);
        if (first != ' ' && first >= 'a' && first <= 'z') answer += s.substring(0, 1).toUpperCase();
        else answer += s.substring(0, 1);
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ' ') answer += " ";
            else if (s.charAt(i - 1) == ' ') {
                first = s.charAt(i);
                if (first != ' ' && first >= 'a' && first <= 'z') answer += s.substring(i, i + 1).toUpperCase();
                else answer += s.substring(i, i + 1);
            } else answer += s.substring(i, i + 1);
        }
        return answer;
    }
}