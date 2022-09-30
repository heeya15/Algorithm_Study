package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* boj 1937번 욕심쟁이 판다 */
public class boj_1937 {

	static int n;
	static int[][] bamboo, memo;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      
	      n = Integer.parseInt(br.readLine());
	      
	      bamboo = new int[n][n];
	      memo = new int[n][n];
	      
	      for(int i = 0; i < n; i++) {
	    	  StringTokenizer st = new StringTokenizer(br.readLine());
	    	  for(int j = 0; j < n; j++) {
	    		  bamboo[i][j] = Integer.parseInt(st.nextToken());
	    	  }
	      }
	      
	      int answer = -1;
	      
	      for(int i = 0; i < n; i++) {
	    	  for(int j = 0; j < n; j++) {
	    		  answer = Math.max(answer, solve(i, j)); // 방문할 수 있는 칸의 최대 개수를 갱신
	    	  }
	      }
	      
	      System.out.print(answer);

	}
	
	private static int solve(int x, int y) {
		// 0이 아닌 위치는 이미 체크했기 때문에 다시 방문하지 않아도 됨.
		// 현재 위치에서 이동할 수 있는 칸 반환
		if(memo[x][y] != 0) return memo[x][y];
		
		// 방문 표시
		memo[x][y] = 1;
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 대나무를 먹고 자리를 옮기면 옮긴 지역이 그 전 지역보다 대나무가 많아야 됨.
			if(nx < 0 || nx >= n || ny < 0 || ny >= n || bamboo[x][y] >= bamboo[nx][ny]) continue;
			
			// memo[x][y] : 현재 위치에서 이동가능한 칸의 개수
			// solve(nx, ny) + 1: 이동가능한 방향에서 이동할 수 있는 칸 수 갱신
			if(memo[x][y] < solve(nx, ny) + 1) memo[x][y] = solve(nx, ny) + 1;
		}
		
		return memo[x][y];
		
	}
}
