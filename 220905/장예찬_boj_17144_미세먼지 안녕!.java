import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static int R, C, T, room[][], copy[][];
	static LinkedList<int[]> dustPos;
	static int condiBot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		T = Integer.parseInt(input[2]);
		room = new int[R][C];
		copy = new int[R][C];
		dustPos = new LinkedList<>();

		StringTokenizer st;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] > 0) 
					dustPos.offer(new int[] { i, j });// row,col이 들어감.
				else if (room[i][j] == -1) {
					condiBot = i; // 덮어져서 아래쪽이 위치가 저장됨.
				}
			}
		}
		
		for(int i=0;i<T; i++) {
			spread();
			rotate();
			dustqueue();
		}
		int sum=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {					
					sum += room[i][j];
				}
			}
		}
		System.out.println(sum);
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void spread() {
		for(int i = 0; i<R; i++) {
			copy[i] = Arrays.copyOf(room[i], room[i].length);
		}
		while (!dustPos.isEmpty()) {
			int[] dustRC = dustPos.poll();
			int row = dustRC[0];
			int col = dustRC[1];
			if(copy[row][col] < 5)
				continue;
			
			int cnt = 0;
			for (int di = 0; di < 4; di++) {
				int nr = row + dr[di];
				int nc = col + dc[di];
				if (nr < 0 || nr == R || nc < 0 || nc == C || room[nr][nc] == -1)
					continue;
				room[nr][nc] += copy[row][col] / 5;
				cnt++;
			}
			room[row][col] -= (copy[row][col] / 5) * cnt;
		}
	}
	
	private static void rotate() {
		int condiTop = condiBot -1;
		int lastC = C-1;
		int lastR = R-1;
		int r = condiTop;
		int c = 0;
		//윗냉장고
		while(r > 0) {
			room[r][0] = room[r-1][0];
			r--;
		}
		while(c < lastC) {
			room[0][c] = room[0][c+1];
			c++;
		}
		r = 0;
		while(r < condiTop) {
			room[r][lastC] = room[r+1][lastC];
			r++;
		}
		c = lastC;
		while(c > 1) {
			room[condiTop][c] = room[condiTop][c-1];
			c--;
		}
		//아래 냉장고
		
		r = condiBot;
		c = 0;
		while(r < lastR) {
			room[r][0] = room[r+1][0];
			r++;
		}
		while(c < lastC) {
			room[lastR][c] = room[lastR][c+1];
			c++;
		}
		r = lastR;
		while(r > condiBot) {
			room[r][lastC] = room[r-1][lastC];
			r--;
		}
		c = lastC;
		while(c > 1) {
			room[condiBot][c] = room[condiBot][c-1];
			c--;
		}
		room[condiTop][1] = 0;
		room[condiBot][1] = 0;
		room[condiTop][0]=-1;
		room[condiBot][0]=-1;
	}
	
	private static void dustqueue() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] == 0 || room[i][j] == -1)
					continue;
				dustPos.add(new int[] {i, j});
			}
		}
	}
}