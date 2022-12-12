import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 현재 타임에 더 이상 트럭을 올리지 못하면 0을 추가
        // q 크기랑 bridge_length가 같아지면 트럭을 내리고 무게를 낮춰주기
        
        int bridgeWeight = 0; // 현재 다리에 올라간 트럭의 무게
        
        Queue<Integer> q = new LinkedList<>();
        for (int curWeight : truck_weights) {
            // 현재 트럭을 넣을 수 있을 때까지 반복
            while (true) {
                answer += 1; // 시간은 반복문이 돌아갈 때마다 증가함
                
                if (q.isEmpty()) {
                    q.offer(curWeight);
                    bridgeWeight += curWeight;
                    break; // 넣었다면 다음 트럭으로
                } else {
                    // 다리 크기만큼 넣어져있다면 먼저 빼기
                    if (q.size() == bridge_length) {
                        bridgeWeight -= q.poll();
                    }
                    // 추가할 수 있다면
                    if (curWeight + bridgeWeight <= weight) {
                        q.offer(curWeight);
                        bridgeWeight += curWeight;
                        break; // 추가했으므로 다음 트럭
                    } else q.offer(0);
                }
            }
        }
        
        // 마지막꺼까지 넣어진 상태에서 for문 종료됬으므로 해당 트럭이 건너는 시간을 +해야함
        answer += bridge_length;
        return answer;
    }
}