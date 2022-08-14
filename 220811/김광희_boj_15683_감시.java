package 구현;

import java.util.*;
import java.io.*;

/**
 * ( 문제 설명 )
 * CCTV는 벽(6)을 통과할 수 없다. 
 * CCTV가 감시할 수 없는 영역은 사각지대
 * 지도에서 
 * 0 = 빈 칸, 
 * 6 = 벽, 
 * 1~5 = CCTV의 번호이다. 
 * 
 * 0의 빈칸의 개수 세어주기.-> 사각 지대 개수가 됨
 * 
 * ( 문제 풀이 )
 * 1. cctv 개수만큼 중복 순열을 구한다
 * 2. 사무실 정보를 copy_Map 2차원 배열에 복사한다.
 * 3. 해당 탐색 (중복 순열) 방향으로 각 번호 cctv 방법의 화살표 방향 개수에 맞추어 탐색하여 이동
 * 4. 사각 지대 개수를 세어준 다음, 여러 방법 중 사각 지대의 "최소" 크기를 Min 변수에 저장.
 */
public class Main_G5_15683_감시 {
	public static class CCTV {
		int num, x, y;
		public CCTV(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌 시계방향 순서
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<CCTV> cctvList;
	static int[][] map, copy_Map;
	static int Min = Integer.MAX_VALUE;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 사무실의 세로 크기 N과 가로 크기 M 입력
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		map = new int[N][M];
		cctvList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int area = Integer.parseInt(st.nextToken());
				// CCTV 정보 1~ 5번 CCTV
				if (area > 0 && area < 6) cctvList.add(new CCTV(area, i, j));
				map[i][j] = area;
			}
		}
		numbers = new int[cctvList.size()];
		per(0);
		// 결과 출력
		System.out.println(Min);
	}
	// 중복 순열.
	public static void per(int depth) {
		if (depth == cctvList.size()) {
			copy_Map = new int[N][M];
			copyMap();
			for (int i = 0; i < cctvList.size(); i++) {
				// 해당 탐색 방향으로 각 번호 cctv의 화살표 방향 개수에 맞추어 탐색하여 이동
				searchCCTV(numbers[i], cctvList.get(i));
			}
			checkSquareAreaCount(); // 사각지대 개수 구하기.
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			numbers[depth] = i;
			per(depth + 1);
		}
	}
	// 사무실 맵 정보 복사.
	public static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_Map[i][j] = map[i][j];
			}
		}
	}
	// 해당 cctv 감시 방향이 dir 일때 각 CCTV 의 방향 개수에 맞춰 감시하는 함수
	public static void searchCCTV(int dir, CCTV cctv) {
		if (cctv.num == 1) {
			Move(dir, cctv);
		}
		if (cctv.num == 2) {
			if (dir == 1 || dir == 3) {// 우, 좌
				Move(1, cctv);
				Move(3, cctv);
			} else { // 상, 하
				Move(0, cctv);
				Move(2, cctv);
			}
		}
		if (cctv.num == 3) {
			if (dir == 0) {// 상, 우
				Move(0, cctv);
				Move(1, cctv);
			}
			if (dir == 1) {// 우, 하
				Move(1, cctv);
				Move(2, cctv);
			}
			if (dir == 2) {// 하, 좌
				Move(2, cctv);
				Move(3, cctv);
			}
			if (dir == 3) {// 좌, 상
				Move(3, cctv);
				Move(0, cctv);
			}
		}
		if (cctv.num == 4) {
			if (dir == 0) {// 좌, 상, 우
				Move(3, cctv);
				Move(0, cctv);
				Move(1, cctv);	
			}
			if (dir == 1) {// 상,우, 하
				Move(0, cctv);
				Move(1, cctv);
				Move(2, cctv);
			}
			if (dir == 2) {// 우, 하, 좌
				Move(1, cctv);
				Move(2, cctv);
				Move(3, cctv);
			}
			if (dir == 3) {// 하, 좌, 상
				Move(2, cctv);
				Move(3, cctv);
				Move(0, cctv);
			}
		}
		if (cctv.num == 5) { // 상, 우, 하, 좌
			Move(0, cctv);
			Move(1, cctv);
			Move(2, cctv);
			Move(3, cctv);
		}
	}
	// 감시 이동
	public static void Move(int dir, CCTV cctv) {
		int nx = cctv.x;
		int ny = cctv.y;
		while (true) {
			// 범위를 넘거나, 벽을 만났을 경우 while 탈출.
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || copy_Map[nx][ny] == 6 ) break;
			copy_Map[nx][ny] = -1;
			nx += dx[dir];
			ny += dy[dir];
		}
	}

	public static void checkSquareAreaCount() {
		int count = 0;
		// 사각지대 개수 세어주기.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy_Map[i][j] == 0)count++;
			}
		}
		// 사각지대 최소 크기 저장.
		Min = Math.min(count, Min);
	}
}