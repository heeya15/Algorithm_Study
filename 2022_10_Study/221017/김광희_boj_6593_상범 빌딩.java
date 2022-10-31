package BFS;

import java.io.*;
import java.util.*;
/**
 *( 문제 설명 )
 * 탈출하는 가장 빠른 길은 무엇?
 * 상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다.
 * 정육면체는 "금으로 이루어져 있어" < 지나갈 수 없거나, 비어있어서 지나갈 수 있게 > 되어있다.
 * 
 * 대각선으로 이동하는 것을 불가능
 * 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.
 * 
 * ( 문제 풀이 )
 * 1. 처음 시작 층에서 'S'인 시작 좌표를 (sx, sy, sz) 에 저장한다.
 * 2. sx, sy, sz 좌표를 기준으로 bfs 탐색을 해준다.
 *    - 여기서 'E(도착지점)'에 도착하면 bfs 탐색을 종료하고 이동한 최단거리를 출력 양식에 맞게 출력해 준다. -> "Escaped in (시간) minute(s)."
 *    - 모든 탐색을 다 했지만 'E(도착지점)'에 도착하지 못한다면 "Trapped!" 출력 (줄 바꿈 해줘야 함!)
 **/
public class Main_G5_6593_상범빌딩 {
	public static class Point{
		int x, y, z, time;
		public Point(int x, int y, int z, int time) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.time = time;
		}
	}
	static int L, R, C;
	static int[] dx = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static char[][][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// S 미포함 아래층으로 이동할 때도 1분 추가 됨
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken()); // 빌딩의 층 수
			R = Integer.parseInt(st.nextToken()); // 한 층의 행
			C = Integer.parseInt(st.nextToken()); // 한 층의 열
			
			if(L == 0 && R== 0 && C==0) { // 입력의 끝인 경우
				System.out.println(sb.toString());
				return;
			}
			map = new char[L][R][C];
			int sx = 0, sy = 0, sz =0; // 시작 지점 좌표 저장할 변수
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					char [] temp = br.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = temp[k];
						if(map[i][j][k] == 'S') {
							sx = i;
							sy = j;
							sz = k;
						}	
					}
				}
				br.readLine(); // 다음 층으로 가는 공백 입력받기.
			}	
			bfs(sx, sy, sz);
		}
	}
	public static void bfs(int sx, int sy, int sz) {
		Queue<Point > q = new LinkedList<>();
		boolean [][][] visited = new boolean[L][R][C];
		q.add(new Point(sx,sy,sz,0));	
		visited[sx][sy][sz] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			int z = p.z;
			int time = p.time;
			if(map[x][y][z] == 'E') {
				sb.append("Escaped in " + time+ " minute(s).").append("\n");
				return;
			}
			for(int i = 0; i <6; i++) {
				int nx = dx[i] + x;
				int ny = dy[i] + y;
				int nz = dz[i] + z;
				// 경계값 체크
				if(nx < 0 || ny < 0 || nz < 0 || nx >= L || ny >= R || nz >= C) continue;
				if(visited[nx][ny][nz] || map[nx][ny][nz] == '#' ) continue;
				
				if(map[nx][ny][nz] == '.' || map[nx][ny][nz] == 'E' ) {
				  visited[nx][ny][nz] = true;
				  q.add(new Point(nx,ny,nz,time+1));
				}
			}
		}
		sb.append("Trapped!").append("\n");
	}
}
