import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            
            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                if (cur == stack.peek()) stack.pop();
                else stack.push(cur);
            }
        }

        if (stack.isEmpty()) answer = 1;
        return answer;
    }
}