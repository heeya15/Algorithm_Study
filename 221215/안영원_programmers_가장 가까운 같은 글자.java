import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, -1);
        
        for (int i = 0; i < s.length(); i++) {
            int curAlpha = s.charAt(i) - 'a';
            if (alphabet[curAlpha] == -1) {
                answer[i] = alphabet[curAlpha];
            } else {
                answer[i] = i - alphabet[curAlpha];
            }
            
            alphabet[curAlpha] = i;
        }
        
        return answer;
    }
}