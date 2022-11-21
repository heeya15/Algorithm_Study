import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : score) pq.offer(i);
        
        while (pq.size() >= m) {
            for (int i = 0; i < m - 1; i++) {
                pq.poll();
            }
            int p = pq.poll();
            answer += p * m;
        }
        
        return answer;
    }
}
