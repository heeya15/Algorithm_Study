import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static char map[][];
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int startR = 0;
		int startC = 0;

		map = new char[n][m];
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'S') {
					startR = i;
					startC = j;
					map[i][j] = '.';
				}
			}
		}

		// 쓰레기 개수가 적은 순, 같다면 쓰레기 옆을 지나는 개수가 적은 순으로 정렬된 우선순위큐
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(startR, startC, 0, 0));
		visited[startR][startC] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 목적지에 도착하면 출력하고 종료
			if (map[cur.r][cur.c] == 'F') {
				System.out.println(cur.trash + " " + cur.sideTrash);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				int sideTrash = checkSideTrash(nr, nc);

				// 다음 위치가 도착지점이라면 쓰레기 개수를 구하지않음(출발지와 도착지는 카운트 하지 않기때문)
				if (map[nr][nc] == 'F') {
					q.offer(new Node(nr, nc, cur.trash, cur.sideTrash));
					continue;
				}

				// 다음 위치가 쓰레기라면 쓰레기 개수만 추가, 인접 쓰레기는 해당 위치가 빈칸일 때만 적용
				if (map[nr][nc] == 'g') q.offer(new Node(nr, nc, cur.trash + 1, cur.sideTrash));
				else q.offer(new Node(nr, nc, cur.trash, cur.sideTrash + sideTrash));
			}
		}
	}

	// 인접한 곳에 쓰레기가 있다면 1 아니면 0을 리턴
	static int checkSideTrash(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
			if (map[nr][nc] == 'g') return 1;
		}
		return 0;
	}

	static class Node implements Comparable<Node> {
		int r;
		int c;
		int trash;
		int sideTrash;

		Node (int r, int c, int trash, int sideTrash) {
			this.r = r;
			this.c = c;
			this.trash = trash;
			this.sideTrash = sideTrash;
		}

		@Override
		public int compareTo(Node o) {
			if (trash == o.trash) return sideTrash - o.sideTrash;
			return trash - o.trash;
		}
	}
}