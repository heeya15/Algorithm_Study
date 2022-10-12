import java.util.*;

class Solution {
    
    static long sum1 = 0, sum2 = 0;
    
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    public int solution(int[] queue1, int[] queue2) {

        int answer = 0, count = 0;
        
        for(int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i]; // queue1 합
            sum2 += queue2[i]; // queue2 합
            
            q1.offer(queue1[i]); // queue1 배열 요소 queue에 담기
            q2.offer(queue2[i]); // queue2 배열 요소 queue에 담기
        }
        
        // 합이 홀수일 경우 합이 같아질 수 없음
        if(sum1 % 2 != 0 || sum2 % 2 != 0) answer = -1;
        
        while(true) {
            // 최대로 순환 가능한 길이만큼 돌렸을 때도 합이 같아지지 않으면 -1
            if(count == queue1.length * 4) return -1;
            
            // queue1 합 < queue2 합
            if(sum1 < sum2) {
                int value = q2.poll(); // q2에 담긴 요소를 poll();
                sum1 += value; // queue1 합에 더해줌
                sum2 -= value; // queue2 합에 빼줌
                q1.offer(value); // q2에서 poll() 한 요소는 q1에 담음
                
            }
            // queue1 합 > queue2 합
            else if(sum1 > sum2) { 
                int value = q1.poll(); // q1에 담긴 요소를 poll();
                sum1 -= value; // queue1 합에 빼줌
                sum2 += value; // queue2 합에 더해줌
                q2.offer(value); // q1에서 poll() 한 요소는 q2에 담음
            } 
            // 합이 같아지면 break
            else {
                break;
            }
            
            count++;
        }
        
        answer = count;
        
        return answer;
    }
}