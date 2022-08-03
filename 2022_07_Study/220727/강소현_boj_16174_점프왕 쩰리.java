// BFS

package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16174_BFS {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	// 쩰리가 이동 가능한 방향은 오른쪽과 아래
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	
	static Queue<Node> queue = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(solve()) System.out.println("HaruHaru");
		else System.out.println("Hing");
	}
	
	static boolean solve() {
		
		queue.offer(new Node(0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Node n = queue.poll();
			int nr = n.r;
			int nc = n.c;
			
			for(int d = 0; d < 2; d++) {
				// 쩰리가 현재 밟고 있는 칸에 쓰여 있는 수 만큼 이동
				int nx = nr + dx[d] * map[nr][nc];
				int ny = nc + dy[d] * map[nr][nc];
				
				if(nx < 0 || nx >=N || ny < 0 || ny >= N || visited[nx][ny]) continue;
				
				if(map[nx][ny] == -1) return true;
				
				visited[nx][ny] = true;
				queue.offer(new Node(nx, ny));
				
			}
		}
		return false;
	}
	
	static class Node {
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}

// DFS

package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16174_DFS {

	static int N;
	static boolean flag = false;
	static int[][] map;
	static boolean[][] visited;
	
	// 쩰리가 이동 가능한 방향은 오른쪽과 아래
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0);
		
		if(flag) System.out.print("HaruHaru");
		else System.out.print("Hing");

	}
	
	static void solve(int x, int y) {
		
		visited[0][0] = true;
		
		for(int d = 0; d < 2; d++) {
			// 쩰리가 현재 밟고 있는 칸에 쓰여 있는 수 만큼 이동
			int nx = x + dx[d] * map[x][y];
			int ny = y + dy[d] * map[x][y];
			
			if(nx < 0 || nx >=N || ny < 0 || ny >= N || visited[nx][ny]) continue;
			
			if(map[nx][ny] == -1) {
				flag = true;
				break;
			}
			
			visited[nx][ny] = true;
			solve(nx, ny);
		}
	}
}
