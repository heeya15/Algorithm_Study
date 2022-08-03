package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2615 {

	static int N = 19;
	static int[][] omok = new int[N][N];
	
	static int[] dx = {-1, 0, 1, 1}; // 우상, 우, 우하, 하
	static int[] dy = {1, 1, 1, 0};
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				omok[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = false;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 바둑알이 존재할때
				if(omok[i][j] != 0) {
					if(check(i, j, omok[i][j])) {
						flag = true;
					}
				}
			}
		}
		
		if(!flag) sb.append("0");
		
		System.out.print(sb.toString());
		
	}
	
	static boolean check(int x, int y, int color) {
		
		for(int d = 0; d < 4; d++) {
			
			int rx = x - dx[d];
			int ry = y - dy[d];				
			
			if(isRange(rx, ry) && omok[rx][ry] == color) continue; // 이동했는데 비교하고자 하는 바둑돌 색과 같으면 검사하지 않음
			
			
			int nx = x, ny = y;
			int cnt = 0;
			
			while(true) {
				
				nx += dx[d];
				ny += dy[d];
				
				cnt++;
				
				if(!isRange(nx, ny) || omok[nx][ny] == color) break;
			}
			
			// 바둑알 5개 일 때,
			if(cnt == 5) {
				sb.append(omok[x][y]).append("\n").append(x + 1).append(" ").append(y + 1);
				return true;
			}
		}
		return false;
	}
	
	static boolean isRange(int x, int y) {
		return (x >= 0 && x < 19 && y >= 0 && y < 19);
	}
}
