package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14890 {

	static int N, L;
	static int[][] rowMap, calMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		L = Integer.parseInt(st.nextToken()); // 길이
		
		rowMap = new int[N][N];
		calMap = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				rowMap[i][j] = calMap[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(check(rowMap[i])) cnt++;
			if(check(calMap[i])) cnt++;
		}
		
		System.out.print(cnt);
	}
	
	static boolean check(int[] map) {
		boolean[] flag = new boolean[N];
		
		for(int i = 1; i < N; i++) {
			// 1. 같은 높이 통과
			if(map[i- 1] == map[i]) continue;

			// 2. 낮은 칸과 높은 칸의 높이 차이는 1을 넘기면 설치 불가
			if(Math.abs(map[i - 1] - map[i]) > 1) return false;
			
			// 3. 오르막
			if(map[i - 1] < map[i]) {
				
				// 범위 체크
				if(0 > i - L - 1) return false;
				
				for(int j = i - 1; j >= i - L; j--) {
					
					if(map[i - 1] == map[j] && !flag[j]) {
						flag[j] = true;
						
					}else {
						return false;
					}
				}
				
			}
			// 4. 내리막
			else if(map[i - 1] > map[i]) { 
				
				// 범위 체크
				if(N < i + L) return false;
				
				for(int j = i; j < i + L; j++) {
					if(map[i] == map[j] && !flag[j]) {
						flag[j] = true;
						
					}else {
						return false;
					}
				}
			}
		}

		return true;
	}

}
