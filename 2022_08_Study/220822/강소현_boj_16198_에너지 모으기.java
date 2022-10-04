package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* boj 16198번 에너지 모으기 */
public class boj_16198 {

	static int N, result = 0;

	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int w = Integer.parseInt(st.nextToken());
			
			list.add(w);
		}
		
		solve(0, 0);
		
		System.out.print(result);
	}
	
	private static void solve(int count, int sum) {
		
		// 첫번째와 마지막 에너지 구슬은 고를 수 없으므로 N - 2개까지 선택
		if(count == N - 2) {
			result = Math.max(result, sum);
			
			return;
		}
		
		for(int i = 1; i < list.size() - 1; i++) {
			int energy = list.get(i - 1) * list.get(i + 1); // 에너지모으기
			int marble = list.remove(i); // i번째 에너지 구슬 제거
			
			solve(count + 1, sum + energy); // i번째 구슬 제거 후, 재귀 호출
			
			list.add(i, marble); // 초기화
		}
	}
}
