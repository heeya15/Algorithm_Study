package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* boj 20056번 마법사 상어와 파이어볼 */
public class boj_20056 {

	static int N, M, K;
	static ArrayList<FireBall>[][] fireBalls;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0, 1, -1, 1, -1};
	static int[] dc = {0, 0, -1, 1, 1, 1, -1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명렁 횟수
		
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				fireBalls[i][j] = new ArrayList<>();
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 위치
			int c = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 위치
			int m = Integer.parseInt(st.nextToken()); // 질량
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 속력
			
			
			fireBalls[r][c].add(new FireBall(r, c, m, d, s));
		}
		
		while(K --> 0) {
			move();
		}
	}
	
	static void move() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				for(int k = 0; k < fireBalls[i][j].size(); k++) {
					FireBall f = fireBalls[i][j].get(k);
					
					int r = f.r;
					int c = f.c;
					int m = f.m;
					int d = f.d;
					int s = f.s;
					
					if(visited[r][c]) continue;
					
					visited[r][c] = true;
					
					// 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
					int nr = (r + dr[d] * s);
					int nc = (c + dc[d] * s);
					
					
					fireBalls[nr][nc].add(new FireBall(nr, nc, m, d, s));
					
				}
			}
		}
	}
	
	static void fire() {
		
	}
	
	static class FireBall {
		int r, c, m, d, s;
		
		public FireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
}
