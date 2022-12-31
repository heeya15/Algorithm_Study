package DFS;

import java.util.*;
import java.io.*;

/**
 * ( 문제풀이)
 * 1. 좌표에 방문하지 않았을 경우 dfs를 탐색한다.
 *    dfs의 매개변수는 행, 열, 해당 좌표의 병사들의 옷색을 넘겨준다
 *    -> 해당 좌표의 병사들의 옷색과 같고, 해당 좌표에 방문하지 않았을 경우 count 증가와, 방문처리 및 dfs 깊이 탐색을 추가로 해준다.
 * 2. dfs 탐색이 끝나고 해당 좌표의 색깔이 B인지, W인지에 따라 count의 ^2을 색깔에 맞는 합의 변수에 저장한다.
 */
public class Main_S1_1303_전쟁전투 {
	static char map [][];
	static boolean visited[][];
	// 하, 좌,우,상
	static int dr[] = {1,0,0,-1};
	static int dc[] = {0,-1,1,0};
	static int N,M;
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken());// 가로
		N = Integer.parseInt(st.nextToken());// 세로
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		int W_SUM = 0;
		int B_SUM = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					count = 1;
					visited[i][j] = true;
					dfs(i,j,map[i][j]);
					if(map[i][j] == 'W') W_SUM += Math.pow(count, 2);
					else if(map[i][j] == 'B') B_SUM += Math.pow(count, 2);
				}		
			}
		}
		System.out.println(W_SUM + " " + B_SUM);
	}

	private static void dfs(int x, int y, char color) {
		 for (int i = 0; i < 4; i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			if(nr < 0 || nc < 0 || nr>=N || nc >= M || map[nr][nc] != color || visited[nr][nc])continue;
			else if(map[nr][nc] == color && !visited[nr][nc]) {
				count++;
				visited[nr][nc] = true;
				dfs(nr,nc, map[nr][nc]);
			}
		}
	}
}