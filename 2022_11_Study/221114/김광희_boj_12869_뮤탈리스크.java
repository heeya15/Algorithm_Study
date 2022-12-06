package 여러알고리즘활용;

import java.util.*;
import java.io.*;

/**
 * ( 문제 이해하기 위해 나만의 설명 )
 *  scv의 체력이 깍이는 경우의 수는 6가지
 *  -9 -3 -1 // -9 -1 -3 // -1 -3 -9  // -1 -9 -3 // -3 -1 -9  // -3 -9 -1
 *  
 * 예를 들어 scv의 체력이 각 0, 20, 0일 때 -> 0, 2, 0 이 될 경우 예시
 * (1). -1, -9, -3 을 하면 0, 2, 0 에 도착하기 위해 2번의 공격을 해야하는데
 * (2). -9, -3, -1 을 하면 0, 2, 0 에 6번 공격에 도착할 수 있다.
 * 
 * ( 문제 풀이 ) 
 * 1. dfs를 통하여 최소 공격 횟수를 구한다. -> 체력이 모두 0이 될 경우 ( 모든 scv를 파괴했을 경우 ) 최소 공격 횟수를 더 작은 공격 횟수로 갱신.
 * (1)-1. 3차원 배열의 값으로 3개의 scv 각 체력까지 온 공격횟수를 저장해준다.
 * (1)-2. dfs를 돌면서 해당 공격 횟수의 값이 dp에 저장된 공격 횟수보다 크다면 최솟값이 될 수 없으므로 return
 * (1)-3. 최소 공격 횟수 값 보다 "현재 공격 횟수가" 같거나 클 경우 return ( 최소 공격 횟수를 구해야 하는대 더 크면 볼 필요도 없기 때문 )
 * 
**/
public class Main_G4_12869_뮤탈리스크 {
	static int N;
	static int [][][] dp;
	static int[][] pattern = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-3,-9},{-1,-9,-3}};
	static int min_attack_count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());// SCV의 수
		int [] scv = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// SCV N개의 체력
		dp = new int[61][61][61];
		for (int i = 0; i < N; i++) scv[i] = Integer.parseInt(st.nextToken());
		min_attack_count = Integer.MAX_VALUE; // 최소 공격 횟수
		
		dfs(scv,0);
		System.out.println(min_attack_count);
	}

	public static void dfs(int [] scv ,int now_attack_cnt) {
	
		// 최소 공격 횟수 값 보다 "현재 공격 횟수가" 같거나 클 경우 중단
		if (min_attack_count <= now_attack_cnt) return;
		
		// 이미 해당 하는 공격 횟수를 누적 했지만, 누적된 공격 횟수 보다 "현재 공격 횟수"가 더 클 경우
		if(dp[scv[0]][scv[1]][scv[2]] != 0 && 
		   dp[scv[0]][scv[1]][scv[2]] <= now_attack_cnt) return;
		
		dp[scv[0]][scv[1]][scv[2]] = now_attack_cnt; // scv가 해당 체력까지 온 횟수를 저장.
		
		//	남은 scv의 체력들이 0이 될때
		if (scv[0] == 0 && scv[1] == 0 && scv[2] == 0) {
			min_attack_count = Math.min(min_attack_count, now_attack_cnt);
			return;
		}

		for (int i = 0; i < 6; i++) {
			int scv1 = Math.max(scv[0] + pattern[i][0], 0);
			int scv2 = Math.max(scv[1] + pattern[i][1], 0);
			int scv3 = Math.max(scv[2] + pattern[i][2], 0); 
			
			dfs(new int[] {scv1,scv2,scv3},now_attack_cnt+1);
		}
	}
}