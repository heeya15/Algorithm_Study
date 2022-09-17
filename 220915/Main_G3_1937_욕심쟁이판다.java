package 여러알고리즘활용;

import java.util.*;
import java.io.*;
/**
 * 
 * 이 판다는 매우 욕심이 많아서 [ 대나무를 먹고 자리를 옮기면 ] 그 옮긴 지역에 < 그 전 지역보다 > 대나무가 "많이 있어야" 한다.
 * 어떤 지점에 처음에 풀어 놓아야 하고, 어떤 곳으로 이동을 시켜야 [ 판다가 최대한 많은 칸을 방문 ] 할 수있는지 고민에 빠져있다.
 * 이 판다가 [ 최대한 많은 칸을 이동 ]하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.
 * 
 * ( 문제 풀이 )
 * 1. 대나무 숲의 크기를 저장하는 2차원 배열과, 각 지점에서 갈 수 있는 "최대 거리를 저장"해 놓는 2차원 배열을 초기화 및 사용
 * 2. 0,0 부터 dfs 탐색을 돌리며, 처음 방문한 경우 해당 좌표는 1로 초기화 하고 시작
 * 3. 만약 4방으로 이동한 방향(map[nr][nc]의 대나무 숲의 크기가, 이전 대나무 숲의 크기(prev) 보다 크다면 이동 하며
 *    최대 거리를 저장하는 배열의 < 기존 값과, 새로 깊이 탐색을 한 값 중 > "더 큰 값으로 갱신"
 * 4. 더 이상 4방 탐색을 할 수 없는 경우 재귀가 종료되고 나서 처음 탐색한 좌표에 저장된 최대 깊이 탐색한 거리를 return 하여
 *    max 값을 갱신 하여, 해당 max 값을 출력하도록 해결.
 *    
 * ( 예시 설명 ) 
 * 9로 예시를 들면 상,하,좌,우 순인대 위로는 갈 수 없고 아래쪽 방향으로 더 큰 값이 있어 탐색 가능하다.
 * 따라서 아래 쪽 방향으로 깊이 탐색을 시작한다. 9 -> 11 -> 15 즉 dp[0][1] 은 < 3이라는 거리 >가 저장
 * 그 다음 오른쪽 방향으로 갈 수 있기 때문에 깊이 탐색을 시작. 9 -> 12 즉, < 2라는 거리 >가 나옴.
 * dp[0][1] 은 현재 3이라는 값인대 "오른쪽 방향" 으로 깊이 탐색을 한 경우는 2이기 때문에 [ dp 배열에 갱신할 필요가 없음  ]
 * dp[1][3] 은 4 -> 5 -> 11 -> 15로 총 4라는 거리가 나옴으로, 판다가 이동할 수 있는 칸의 수의 최댓 값이 된다.
 */
public class Main_G3_1937_욕심쟁이판다 {
	static int N,count, max;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	static int[][] map, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 대나무 숲의 크기
		map = new int[N][N];// 대나무 숲의 크기를 저장하는 2차원 배열 초기화
		dp = new int[N][N]; // 각 지점에서 갈 수 있는 "최대 거리를 저장"해 놓는 2차원 배열 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());// 일반적인 배열
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, dfs(i,j, map[i][j]));
			}
		}
		System.out.println(max);
	}
	// 9 -> 12로 가는 최대 깊이는 2
	// 9 -> 15로 가는 최대 깊이는 3 -> 따라서 3의 깊이를 선택
	public static int dfs(int x, int y, int prev) {
		if(dp[x][y] == 0) { //갱신되지 않은 위치라면
			dp[x][y] = 1;   // 처음 위치 나무를 다 먹어 치워버리기 때문에 "1로 초기화"
			for (int i = 0; i < 4; i++) {
				int nr = dr[i] + x;
				int nc = dc[i] + y;
				if(nr < 0 || nc < 0 || nr >=N || nc >=N )continue;
				// "옮긴 위치가" [ 그 전지역보다 ] 대나무가 더 많이 있다면 -> 대나무를 먹어치우러 간다.
				if(prev < map[nr][nc]) {
					// 기존의 값과, 새로 깊이 탐색을 한 값 중 더 큰 값으로 갱신
					dp[x][y] = Math.max(dp[x][y], dfs(nr,nc,map[nr][nc]) + 1);
				} 
			}
		}
		return dp[x][y];
	}
}