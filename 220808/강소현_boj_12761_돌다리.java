package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* boj 12761번 돌다리 */
public class boj_12761 {

	static int A, B, N, M;
	static int[] move = new int[8];
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken()); // 스카이 콩콩의 힘 A
		B = Integer.parseInt(st.nextToken()); // 스카이 콩콩의 힘 B
		N = Integer.parseInt(st.nextToken()); // 동규의 현재 위치
		M = Integer.parseInt(st.nextToken()); // 주미의 현재 위치
		
		visited = new boolean[100002];
		solve();
	}
	
	static void solve() {
		Queue<Point> queue = new LinkedList<>();
		
		queue.offer(new Point(N, 0)); // 동규 현재 위치
			
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			// 주미를 만나면 종료
			if(now.x == M) {
				System.out.print(now.cnt);
				return;
			}
			
			move[0] = now.x + 1; // 1. 현위치 + 1
			move[1] = now.x - 1; // 2. 현위치 - 1
			move[2] = now.x + A; // 3. 현위치 + A
			move[3] = now.x - A; // 4. 현위치 - A
			move[4] = now.x + B; // 5. 현위치 + B
			move[5] = now.x - B; // 6. 현위치 - B
			move[6] = now.x * A; // 7. 현위치 * A
			move[7] = now.x * B; // 8. 현위치 * B
			
			for(int d = 0; d < 8; d++) {
				int nx = move[d];
				if(nx < 0 || nx > 100001 || visited[nx]) continue;
				queue.offer(new Point(nx, now.cnt + 1));
				visited[nx] = true;
			}
		}
	}
	
	static class Point {
		int x, cnt;
		
		public Point(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}
}
