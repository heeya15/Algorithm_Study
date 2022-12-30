import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i : ingredient) {
            stack.push(i);
            
            if (stack.peek() == 1 && stack.size() >= 4) {
                
                int b1 = stack.pop(); // 빵 1
                int v1 = stack.pop(); // 야채
                int m1 = stack.pop(); // 고기
                int b2 = stack.pop(); // 빵 2
                
                if (b1 == 1 && v1 == 3 && m1 == 2 && b2 == 1) {
                    answer++;
                } else {
                    stack.push(b2);
                    stack.push(m1);
                    stack.push(v1);
                    stack.push(b1);
                }
            }
        }
    
        return answer;
    }
}