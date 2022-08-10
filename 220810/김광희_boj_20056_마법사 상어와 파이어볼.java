package BaekJoon_self;

import java.util.*;
import java.io.*;

/**
 * 1번 행은 N번과 연결되어 있고, 
 * 1번 열은 N번 열과 연결 인접한 칸 중에서 비어있는 칸이 가장 많은 칸인 (2, 2)이 4번 학생의 자리
 */
public class Main9 {
	public static class FireBall {
		int r, c, m, d, s;

		public FireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}

	static int N, M, K;
	// 0,2,4,6 = 상, 우, 하, 좌 , 1,3,5,7 = 오른쪽 대각선 위, 오른 쪽 대각선 아래, 왼쪽 대각선 아래, 왼쪽 대각선 위
	public static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static ArrayList<FireBall> list;
	public static ArrayList<FireBall>[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 맵 크기 및 ( 1번 부터 N번까지 번호 )
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // K번 이동 명령

		map = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 파이어볼의 정보
			// 파이어 볼의 위치
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향
			list.add(new FireBall(r, c, m, d, s));
		}

		// K번 이동
		for (int i = 0; i < K; i++) {
			move();
			checkFireball();
		}
	}

	public static void move() {
		for (FireBall f : list) {
			// 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
			f.r = (N + f.r + dx[f.d] * (f.s % N)) % N;
			f.c = (N + f.c + dy[f.d] * (f.s % N)) % N;

			// 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
			map[f.r][f.c].add(f);
		}
	}
	
	public static void checkFireball() {
		
	}
}