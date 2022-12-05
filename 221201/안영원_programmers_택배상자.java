import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Queue<Integer> fBelt = new LinkedList<>();
        Stack<Integer> sBelt = new Stack<>();
        
        for (int i = 1; i <= order.length; i++) {
            fBelt.offer(i);
        }
        
        int idx = 0;
        
        // 바로 실을 수 있을 때는 싣고 안된다면 두 번째 컨테이너 벨트에 옮김
        // 두 번째 컨테이너 벨트에도 짐이 있다면 그걸로 실을 수 있는지도 확인
        while (!fBelt.isEmpty()) {
            if (order[idx] != fBelt.peek()) {
                if (!sBelt.isEmpty() && sBelt.peek() == order[idx]) {
                    sBelt.pop();
                    answer++;
                    idx++;
                } else sBelt.push(fBelt.poll());
            } else {
                fBelt.poll();
                answer++;
                idx++;
            }
        }
        
        // 첫 번째 컨테이너 벨트에서 가능한 경우의 수를 탐색 완료했다면
        // 두 번째 컨테이너 벨트도 마저 탐색
        while (!sBelt.isEmpty()) {
            if (sBelt.pop() == order[idx]) {
                answer++;
                idx++;
            } else break;
        }
        
        return answer;
    }
}