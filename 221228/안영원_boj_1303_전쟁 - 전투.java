import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static char[][] map;
	static boolean[][] visited;
	static int whiteStrength, blueStrength;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		// 위력 확인
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) checkStrength(i, j);
			}
		}
		sb.append(whiteStrength + " " + blueStrength);
		System.out.println(sb);
	}

	static void checkStrength(int r, int c) {
		char color = map[r][c]; // 현재 확인하는 병사의 색
		int strength = 0; // 현재 병사들의 위력

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			strength += 1;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc]) continue;

				// 같은 팀일경우
				if (map[nr][nc] == color) {
					visited[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
		}

		// 뭉쳐있을 때 n^2으로 강해진다 !
		if (strength > 1) strength = (int) Math.pow(strength, 2);

		if (color == 'W') whiteStrength += strength;
		else blueStrength += strength;
	}

	static class Node {
		int r;
		int c;

		Node (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}