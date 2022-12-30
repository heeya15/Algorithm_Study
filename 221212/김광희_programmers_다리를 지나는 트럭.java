package Programmers;
import java.util.*;
/**
 * ( 문제 풀이 )
 * 1. 큐를 활용하여 처음 큐가 비었을때 하나의 트럭 을 Queue에 넣어주고 시간(time) 초 증가 후 while문 탈출
 * 2. 큐가 비어있지 않았을 경우 
 *    -> 먼저, 큐의 사이즈가 다리에 올라갈 수 있는 트럭 수와 같다면 트럭 최대 무게 합(sum) 에 가장 먼저 다리 위에 있는 트럭 무게를 뺀다.
 *    -> 현재 다리 위에 올라간 트럭과, 새로운 트럭의 무게 합이 다리가 견딜 수 있는 무게 합 보다 작다면 다리위에 새로운 트럭이 같이 올라갈 수 있다.
 *       따라서, 새로운 트럭을 Queue에 넣고, sum을 증가 후 다리를 건너는 시간(time) 1초 추가 후 while 탈출
 *    -> 만약 새로운 다리를 건너려는 트럭과, 기존 다리위에 있는 트럭 무게 합이 다리가 견딜 수 없는 무게가 된다면
 *       기존 다리위에 있는 트럭이 나가기 위해 Queue에 0을 넣어주고 다리를 건너는 시간(time) 1초 추가
 * 3. 최종적으로 다리를 건너는 시간 및 다리 길이를 더해줘야 모든 트럭이 다리를 건너는 최소 시간이 구해질 수 있다.
 **/
public class Lv2_다리를지나는트럭 {
	public static void main(String[] args) {
		int[] truck_weights = { 7,4,5,6 };	
		System.out.println(solution(2, 10, truck_weights));
	}
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0; 
        Queue<Integer> q = new LinkedList<>();
        int sum = 0; // 다리 위에 올라갈 수 있는 트럭 최대 무게 합
        for(int i = 0; i < truck_weights.length; i++){
            while(true){
                if(q.isEmpty()){
                    q.offer(truck_weights[i]);
                    sum+= truck_weights[i];
                    time++;
                    break;
                }else{
                    if(q.size() == bridge_length){
                        sum -=q.poll();
                    }
                    if(sum + truck_weights[i] <= weight){
                        q.offer(truck_weights[i]);
                        sum+=truck_weights[i];
                        time++;
                        break;
                    }else{
                        q.offer(0);
                        time++;
                    }
                }
            }
        }
        return time + bridge_length;
    }
}