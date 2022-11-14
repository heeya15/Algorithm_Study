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
 * 때문에 위에서 (1) 번 방법으로 더 적은 횟수로 도착하는 경우의 dp 값을 변경해 주면 된다.
 * 모든 순회를 다 하고 0,0,0에 저장된 값을 출력해 준다.
 * 
**/
public class Main_G4_12869_뮤탈리스크 {
	static int N;
	static int[] scv;
	static int[][] pattern = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-3,-9},{-1,-9,-3}};
	static int min_attack_count = Integer.MAX_VALUE; // 최소 공격 횟수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());// SCV의 수
		scv = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// SCV N개의 체력
		for (int i = 0; i < N; i++) scv[i] = Integer.parseInt(st.nextToken());

		System.out.println(min_attack_count);
	}

	public static void dfs(int[] scv, int cnt) {
		if (min_attack_count <= cnt) return;

//		남은 scv의 체력들이 0이 될때
		if (scv[0] == 0 && scv[1] == 0 && scv[2] == 0) {
			min_attack_count = Math.min(min_attack_count, cnt);
			return;
		}

		for (int i = 0; i < 6; i++) {
			scv[0] = Math.max(scv[0] + pattern[i][0], 0);
			scv[1] = Math.max(scv[1] + pattern[i][1], 0);
			scv[2] = Math.max(scv[2] + pattern[i][2], 0);
			dfs(scv, cnt + 1);
		}
	}

}
