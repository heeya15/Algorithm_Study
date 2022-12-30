import java.io.*;
import java.util.*;

public class Main {
	// 북 동 남 서
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] nDir = {3, 0, 1, 2}; // 왼쪽 방향

	// init
	static int n, m;
	static int[][] map;
	static boolean[][] cleaned;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cleaned = new boolean[n][m];
		simulation(robot);
		System.out.println(result);
	}

	static void simulation(Robot robot) {
		// 1 : 청소하기
		if (!cleaned[robot.r][robot.c]) result++;
		cleaned[robot.r][robot.c] = true;

		// 2 : 왼쪽 방향부터 차례대로 탐색 진행
		int dir = robot.dir;
		for (int d = 0; d < 4; d++) {
			// 왼쪽 방향 좌표
			int nr = robot.r + dr[nDir[dir]];
			int nc = robot.c + dc[nDir[dir]];

			// 2-2 : 청소하지 않은 공간이 있다면 한 칸 전진 후 1번부터 진행
			if (map[nr][nc] == 0) {
				if (!cleaned[nr][nc]) {
					simulation(new Robot(nr, nc, nDir[dir]));
					return;	
				}
			}
			dir = nDir[dir];
		}
		

		// 후진했을 때 좌표
		int nr = robot.r - dr[robot.dir];
		int nc = robot.c - dc[robot.dir];

		// 2-4 : 종료
		if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1) return;

		// 2-3 : 2번으로 돌아가기
		simulation(new Robot(nr, nc, robot.dir));
	}

	static class Robot {
		int r;
		int c;
		int dir;

		Robot (int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
