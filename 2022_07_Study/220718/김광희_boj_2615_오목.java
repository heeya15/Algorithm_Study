package BaekJoon_self;

import java.util.*;
import java.io.*;

public class Main {
	public static class Point {
		int r, c, cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[][] map = new int[21][21];
	// 하, 오른쪽 대각선 아래, 오른쪽, 위쪽 대각선 위
	static int[] dr = { 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(findFive());
	}

	public static String findFive() {
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if (map[i][j] != 0) { // 흑돌 또는 흰돌 인 경우.
					for (int d = 0; d < 4; d++) {
						// 행, 열, 방향, 흰돌 or 흑돌 색깔 
						if (bfs(i, j, d, map[i][j]) == 5) {
							return map[i][j] + "\n" + i + " " + j ;
						}
					}
				}
			}
		}
		return "0"; // 승부가 결정되지 않았을 경우 0을 출력.
	}

	public static int bfs(int x, int y, int dir, int color) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y, 1));
		int max = Integer.MIN_VALUE;
		while (!q.isEmpty()) {
			Point p = q.poll();
			max = Math.max(p.cnt, max);
			int nr = p.r + dr[dir];
			int nc = p.c + dc[dir];
			if (nr < 1 || nc < 1 || nr > 19 || nc > 19 || map[nr][nc] != color)continue;
			q.add(new Point(nr, nc, p.cnt + 1));
		}
		// 같은 색 바둑 돌이 5개를 넘어가는 경우를 체크
		if (max == 5) {
			int nx = x - dr[dir];
			int ny = y - dc[dir];
			if (nx >= 1 && nx < 20 && ny >= 1 && ny < 20 && map[nx][ny] == color) max++;
		}
		return max;
	}
}