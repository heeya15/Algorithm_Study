import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
    static Queue<Integer> truck = new LinkedList<>();
    static Queue<Integer> bridge = new LinkedList<>();
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // truck 큐에 트럭 무게 담기
        for (int t : truck_weights) {
            truck.offer(t);
        }
        
        // bridge 큐에 다리 길이 만큼 0으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        
        int time = 0, currentWeight = 0;
        
        while (!bridge.isEmpty()) {
            currentWeight -= bridge.poll(); // 지나간 트럭의 무게는 뺀다.
        
            if (!truck.isEmpty()) {
                
                // 지나가는 트럭의 무게가 다리가 견딜 수 있는 무게 (weight) 를 넘지 않을 때
                if (currentWeight + truck.peek() <= weight) {
                    int truckWeight = truck.poll(); // 현재 지나가는 트럭

                    currentWeight += truckWeight;
                    bridge.offer(truckWeight);
                } else {
                    // 다리가 견딜 수 있는 무게가 아닌 경우 0
                    bridge.offer(0);
                }
            }
            time++;
        }
        
        return time;
        
    }
}