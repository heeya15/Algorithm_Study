import java.io.*;
import java.util.*;

public class Main {
	static int L, R, C;
	static char[][][] map;

	// 동서남북상하
	static int[] dl = {0, 0, 0, 0, 1, -1};
	static int[] dr = {0, 0, 1, -1, 0, 0};
	static int[] dc = {1, -1, 0, 0, 0, 0};

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0) break;

			map = new char[L][R][C];
			Node start = null;

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String temp = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = temp.charAt(k);
						if (map[i][j][k] == 'S') start = new Node(i, j, k, 0);
					}
				}
				br.readLine();
			}
			
			int result = findEscaped(start);
			if (result == -1) {
				sb.append("Trapped!\n");
			} else {
				sb.append("Escaped in " + result + " minute(s).\n");
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static int findEscaped(Node start) {
		boolean[][][] visited = new boolean[L][R][C];
		Queue<Node> q = new LinkedList<>();
		q.offer(start);
		visited[start.l][start.r][start.c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (map[cur.l][cur.r][cur.c] == 'E') {
				return cur.cnt;
			}

			for (int d = 0; d < 6; d++) {
				int nl = cur.l + dl[d];
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nl < 0 || nr < 0 || nc < 0 || nl >= L || nr >= R || nc >= C || visited[nl][nr][nc]) continue;
				if (map[nl][nr][nc] != '#') {
					visited[nl][nr][nc] = true;
					q.offer(new Node(nl, nr, nc, cur.cnt + 1));
				}
			}
		}

		return -1;
	}

	static void print() {
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					System.out.print(map[i][j][k] + " "); 
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	static class Node {
		int l;
		int r;
		int c;
		int cnt;

		Node (int l, int r, int c, int cnt) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}