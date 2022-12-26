import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        String buger = "1231";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredient.length; i++) {
            sb.append(ingredient[i]);
            
            // 햄버거로 만들 수 있는 최소 재료의 개수일 때
            if (sb.length() < 4) continue;
            
            // 순서대로 재료가 쌓여있다면
            if (buger.equals(sb.substring(sb.length() - buger.length(), sb.length()))) {
                sb.delete(sb.length() - buger.length(), sb.length());
                answer += 1;
            }
        }
        
        return answer;
    }
}