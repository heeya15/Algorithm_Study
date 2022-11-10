import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;
            while (count-- > 0) {
                sb.append(Integer.toString(i));
            }
        }
        
        answer += sb.toString();
        answer += "0";
        answer += sb.reverse().toString();
        
        return answer;
    }
}
