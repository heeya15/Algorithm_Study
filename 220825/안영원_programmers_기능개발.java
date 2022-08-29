import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            // 배포 가능한 날짜를 계산
            q.offer(
                (100 - progresses[i]) % speeds[i] == 0 ?
                (100 - progresses[i]) / speeds[i] :
                (100 - progresses[i]) / speeds[i] + 1
            );
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int prev = q.poll();
        int num = 1;
        
        // 몇 개의 기능이 한 번에 배포되는지 계산
        while (!q.isEmpty()) {
            int current = q.poll();
            // 앞 기능이 뒤늦게 개발이 완료되면 뒤쪽 기능과 함꼐 배포됨
            if (prev >= current) {
                num++;
            } else { // 아니면 바로 배포
                list.add(num);
                num = 1;
                prev = current;
            }
        }
        list.add(num);
        
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}