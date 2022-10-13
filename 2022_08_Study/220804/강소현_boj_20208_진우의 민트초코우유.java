package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 이동하면 체력이 1만큼 줄어든다.
// 민트초코우유를 마신다면 체력이 H만큼 증가
// 체력이 0이 되어 집으로 못 돌아가는 상황은 만들면 X

public class boj_20208 {

	static int N, M, H, result;
	static int[][] map;
	static boolean[] visited;
	
	static Point home;
	static ArrayList<Point> milk = new ArrayList<>();
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 민초마을 크기
		M = Integer.parseInt(st.nextToken()); // 진우의 초기 체력
		H = Integer.parseInt(st.nextToken()); // 체력의 양
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if(map[i][j] == 1) {
					// 1 : 진우의 집
					home = new Point(i, j); 
				}
				else if(map[i][j] == 2) {
					// 2 : 민트초코우유
					milk.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[milk.size()];
		
		result = 0;
		solve(home, M, 0);
		
		System.out.println(result);
	}
 	
 	static void solve(Point now, int hp, int cnt) {
 		
 		for(int i = 0; i < milk.size(); i++) {
 			if(!visited[i]) {
 				Point next = milk.get(i);

 				int dist = distance(now.x, now.y, next.x, next.y);

 	 			if(dist <= hp) {
 	 				visited[i] = true;
 	 				solve(next, hp + H - dist, cnt + 1); // 민트초코우유를 마시면 H만큼 체력 증가, 이동한 거리만큼 체력 감소
 	 				visited[i] = false;
 	 			}
 			}
 		}
 		
 		// 집으로 되돌아가기
 		int dist = distance(now.x, now.y, home.x, home.y);
 		
 		// 집으로 돌아갈 체력이 있으면 진우가 먹은 민트초코우유 개수 최댓값 반환
 		if(dist <= hp) {
 			result = Math.max(result, cnt);
 		}
 	}
 	
 	static int distance(int nowX, int nowY, int nextX, int nextY) {
 		return Math.abs(nowX - nextX) + Math.abs(nowY - nextY);
 	}
 	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
