package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* boj 1449번 수리공 항승 */
public class boj_1449 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물이 새는 곳 개수
        int L = Integer.parseInt(st.nextToken()); // 테이프의 길이

        int[] pipe = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pipe[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pipe); // 정렬

        // 그 위치의 좌우 0.5만큼 주어야 됨. -> 그래서 -1 빼줌
        double tape = pipe[0] - 1; // 테이프 길이 초기
        int count = 1; // 테이프 수

        for (int p : pipe) {
            // 물이 새는 곳 위치가 테이프 길이보다 크면 테이프 추가
            if(p > tape + L) {
                count++;
                tape = p - 1;
            }
        }

        System.out.println(count);
    }
}
