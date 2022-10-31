package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* boj 1715번 카드 정렬하기 */
public class boj_1715 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sum, result = 0;
        while(pq.size() > 1) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            sum = num1 + num2; // 큐에 담아 줄 묶음 합
            result += sum; // 누적

            pq.offer(sum);
        }

        System.out.println(result);
    }
}
