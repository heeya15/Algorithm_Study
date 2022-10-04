package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* boj 15683번 감시 */
public class boj_15683 {

	static int N, M, blank = 0;
	static int[][] office;
	static boolean[][] visited;
	
	static ArrayList<CCTV> cctvList = new ArrayList<>();
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[][][] cctvDir = {
			{{0}},
			{{0}, {1}, {2}, {3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{0, 1, 2}, {0, 1, 3}, {1, 2, 3}, {2, 3, 0}},
			{{0, 1, 2, 3}}
	};
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				
				int type = office[i][j];
				
				if (type == 0) blank++;
				else if(type >= 1 && type <= 5) cctvList.add(new CCTV(type, i, j));
				
			}
		}
	}
	
	private static void dfs(int index) {
		// 기저 조건
		if(index == cctvList.size()) {
			// 사각 지대 최소 크기
			
			return;
		}
		
		int type = cctvList.get(index).type; // CCTV 타입
		int x = cctvList.get(index).x; // 방향 x
		int y = cctvList.get(index).y; // 방향 y
		
		// 방향 탐색
		for(int d = 0; d < cctvDir[type].length; d++) {
			int watched = cctvScan(cctvDir[type][d], x, y); // 스캔 가능한 횟수
			
		}
	}
	
	// CCTV 스캔
	private static int cctvScan(int[] cctvDir, int x, int y) {
		int cnt = 0;
		
		for(int d = 0; d < cctvDir.length; d++) {
			
		}
		
	}
	

	static class CCTV {
		int type, x, y;
		
		public CCTV(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
	}

}
