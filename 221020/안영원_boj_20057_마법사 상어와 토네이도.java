import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] next = {2, 3, 1, 0}; // 다음 진행방향

	// 상하좌우 순서로 배치
	static int[][] sandR = {
		{0, -1, 0, 1, -2, -1, 0, 1, 0, -1},
		{0, 1, 0, -1, 2, 1, 0, -1, 0, 1},
		{-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
		{2, 1, 1, 1, 0, -1, -1, -1, -2, 0}
	};
	static int[][] sandC = {
		{2, 1, 1, 1, 0, -1, -1, -1, -2, 0},
		{-2, -1, -1, -1, 0, 1, 1, 1, 2, 0},
		{0, -1, 0, 1, -2, -1, 0, 1, 0, -1},
		{0, 1, 0, -1, 2, 1, 0, -1, 0, 1}
	};
	static int[] persent = {2, 10, 7, 1, 5, 10, 7, 1, 2}; // sand 인덱스별 모래가 흩날리는 퍼센트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(getOutSandAmout());
		
	}

	static int getOutSandAmout() {
		int outSandSum = 0;

		// 중앙에서 시작
		int curR = n / 2;
		int curC = n / 2;
		int dir = 2;

		int moveCnt = 1;
		while (true) {
			if (curR == 0 && curC == 0) break;

			// 같은 거리로 두번 이동하고 이동거리 1씩 증가(달팽이 문제)
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < moveCnt; j++) {
					if (curR == 0 && curC == 0) break;
					curR += dr[dir];
					curC += dc[dir];

					// 모래 흩날리기
					outSandSum += spredSandAndGetOutSandAmout(curR, curC, dir);
				}
				dir = next[dir]; // 방향 전환
			}
			moveCnt++;
		}

		return outSandSum;
	}

	static int spredSandAndGetOutSandAmout(int r, int c, int dir) {
		int outSandSum = 0; // 밖으로 나간 모래양
		int sand = map[r][c]; // 현재 칸의 모래양
		int left = sand; // 남은 모래양

		for (int i = 0; i < 9; i++) {
			int nr = r + sandR[dir][i];
			int nc = c + sandC[dir][i];

			int curSand = (int) Math.floor(sand * (persent[i] * 0.01));
			
			if (canGo(nr, nc)) {
				map[nr][nc] += curSand;
			} else {
				outSandSum += curSand;
			}
			left -= curSand;
		}
		
		int nr = r + sandR[dir][9];
		int nc = c + sandC[dir][9];

		if (canGo(nr, nc)) map[nr][nc] += left;
		else outSandSum += left;

		map[r][c] = 0;

		return outSandSum;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && c >= 0 && r < n && c < n;
	}
}