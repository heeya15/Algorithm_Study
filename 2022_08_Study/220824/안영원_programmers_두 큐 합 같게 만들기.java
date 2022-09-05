import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        int length = queue1.length;
        long sum = 0;
        long q1Sum = 0;
        long q2Sum = 0;
        
        // 값을 큐에 넣어주면서 각각의 합을 구함.
        for (int i = 0; i < length; i++) {
            long curQ1 = queue1[i];
            long curQ2 = queue2[i];
            
            sum += curQ1 + curQ2;
            q1Sum += curQ1;
            q2Sum += curQ2;
            
            q1.add(curQ1);
            q2.add(curQ2);
        }
        
        if (sum % 2 == 1) return -1; // 절반으로 나눠지지 않으면 두 큐를 같게 만들 수 없다.
        
        // 큐 중 값이 큰 것에서 작은 것으로 이동시켜줌
        int cnt = 0;
        while (true) {
            if (cnt >= length * 3) break;
            
            if (q1Sum > q2Sum) {
                long moveNumber = q1.poll(); // 옮길 수
                q1Sum -= moveNumber;
                q2.add(moveNumber);
                q2Sum += moveNumber;
                cnt++;
            } else if (q2Sum > q1Sum) {
                long moveNumber = q2.poll(); // 옮길 수
                q2Sum -= moveNumber;
                q1.add(moveNumber);
                q1Sum += moveNumber;
                cnt++;
            }
            
            if (q1Sum == q2Sum) return cnt;
        }
        
        return -1;
    }
}