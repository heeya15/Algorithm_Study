package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* boj 17144번 미세먼지 안녕 */
public class boj_17144 {

	static int R, C, T;
	static int[] airCleaner;
	static int[][] air;
	
	static Queue<Node> queue = new LinkedList<>();
	
	static int[] dr = {-1, 0, 0, 1}; // 하 우 좌 상
	static int[] dc = {0, 1, -1, 0};
	
	static int[] clock = {1, 3, 2, 0}; // 시계 방향
	static int[] rclock = {1, 0, 2, 3}; // 반시계 방향
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		air = new int[R][C];
		airCleaner = new int[2]; // 위쪽 공기 청정기, 아래쪽 공기 청정기 2개 존재
		
		int index = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				air[i][j] = Integer.parseInt(st.nextToken());
				
				if(air[i][j] == -1) {
					// 공기청정기 위치
					airCleaner[index++] = i;
				}
			}
		}
		
		// T초만큼 돌리기
		while(T-->0) {	
			spread(); // 미세먼지 확산
			up(); // 위쪽 공기청정기 순환
			down(); // 아래쪽 공기청정기 순환
		}
		
		System.out.println(dustAmount());
	}
	

	static void spread() { // 미세먼지 확산

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				// 미세먼지가 있는 위치만 Queue에 넣기
				if(air[i][j] != 0 && air[i][j] != -1) {
					queue.offer(new Node(i, j, air[i][j]));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int spreadDust = n.amount / 5; // 확산되는 양 : air[r][c] / 5
			
			int dustCount = 0; // 미세먼지가 확산된 방향의 개수
			
			for (int d = 0; d < 4; d++) {
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산 X
				if(!isRange(nr, nc) || air[nr][nc] == -1) continue;

				dustCount++; // 미세먼지가 확산 할 수 있는 방향이면 카운트
				
				air[nr][nc] += spreadDust; // 확산된 미세먼지 양 더해줌
			}
			// 미세먼지 확산이 끝났으면 기존 자리에 있던 미세먼지 양을 계산
			air[n.r][n.c] -= (spreadDust * dustCount); 
		}
	}
	

	
	static private void up() { // 위쪽 공기청정기 작동
		
		int[][] airCopy = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				airCopy[i][j] = air[i][j];
			}
		}
		
		int cleanerR = airCleaner[0]; // 위쪽 공기 청정기 위치
		int cleanerC = 1; // 공기 청정기 순환하는 방향 바로 첫 위치 
		
		air[cleanerR][cleanerC] = 0; // 공기청정기에 부는 바람은 미세먼지가 없음
		
		for (int d = 0; d < 4; d++) {
			while(true) {
				// 반시계 방향
				int nr = cleanerR + dr[rclock[d]];
				int nc = cleanerC + dc[rclock[d]];
				
				// 벽을 만나거나 공기청정기에 도달했을 때 탐색을 멈춤
				if(!isRange(nr, nc) || air[nr][nc] == -1) break;
				
				air[nr][nc] = airCopy[cleanerR][cleanerC]; // 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
				
				// 이동한 방향 위치 저장
				cleanerR = nr; 
				cleanerC = nc;
			}
		}
	}
	
	static private void down() { // 아래쪽 공기청정기 작동
		
		int[][] airCopy = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				airCopy[i][j] = air[i][j];
			}
		}
		
		int cleanerR = airCleaner[1]; // 아래쪽 공기청정기 위치
		int cleanerC = 1; // 공기 청정기 순환하는 방향 바로 첫 위치 
		air[cleanerR][cleanerC] = 0; // 공기청정기에 부는 바람은 미세먼지가 없음
				
		for (int d = 0; d < 4; d++) {
			while(true) {
				// 시계 방향
				int nr = cleanerR + dr[clock[d]];
				int nc = cleanerC + dc[clock[d]];
						
				// 벽을 만나거나 공기청정기에 도달했을 때 탐색을 멈춤
				if(!isRange(nr, nc) || air[nr][nc] == -1) break;
						
				air[nr][nc] = airCopy[cleanerR][cleanerC]; // 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
				
				// 이동한 방향 위치 저장
				cleanerR = nr;
				cleanerC = nc;
			}
		}
	}
	
	static private int dustAmount() {
		int sum = 0;
		
		// 탐색이 끝난 후 방에 남아있는 미세먼지 양 출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				
				if(air[i][j] != -1) sum += air[i][j];
				
			}
		}
		
		return sum;
	}
	
	static private boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static class Node{
		int r, c, amount;
		public Node(int r, int c, int amount) {
			this.r = r;
			this.c = c;
			this.amount = amount;
		}
	}
}