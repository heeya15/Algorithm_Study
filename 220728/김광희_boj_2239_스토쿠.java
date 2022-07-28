package BaekJoon_self;

import java.io.*;

public class Main {
	/**
	 * < 이 문제에서 유망성 판단 2가지 경우 > 
	 * 가로, 세로에 동일한 숫자가 있으면 안된다. 
	 * 해당 블럭(3x3)내에 동일한 숫자가 있으면 안된다.
	 */
	static int[][] map;
	static final int size = 9;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[size][size];
		for (int i = 0; i < size; i++) {
			char temp[] = br.readLine().toCharArray();
			for (int j = 0; j < size; j++) {
				map[i][j] = temp[j] - '0';
			}
		}
		sudoku(0, 0); // 스토쿠 만들기 ( 백 트래킹 )
	}

	static void sudoku(int x, int y) {
		// y가 9가 되면 다음 row로 재귀함수 호출
		if (y > 8) {
			sudoku(x + 1, 0);
			return;
		}

		// 모든 row에 대해 스도쿠를 풀면 출력 후 종료
		if (x > 8) {
			stoku_print();
			System.exit(0); // 이것을 안 해주니 모든 9 * 9 보드판에 0일 경우 계속 출력되는 문제로  ( 출력 초과 문제 발생...)
		}

		// 보드 좌표에 값이 0일 경우
		if (map[x][y] == 0) {
			for (int i = 1; i <= size; i++) { // 1 ~ 9 숫자에 대해 유효성 체크
				if (Duplicate_check(x, y, i)) { // 행, 열 모두 중복이 없을 경우 .
					map[x][y] = i; // 현재 시점에서 스도쿠판에 i를 넣을 수 있으면 map[x][y] = i
					sudoku(x, y + 1); // 다음 칸으로 재귀함수 호출
				}
			}
			// 스도쿠를 채워나가면서 계속 값이 바뀌는데 위 반복문으로 맞는 값을 찾을 수 없는 경우
			// map[x][y]를 0으로 만들고 return하여 "이전 빈칸"부터 재귀함수를 다시 호출한다.
			map[x][y] = 0;
			return;
		}
		sudoku(x, y + 1);
	}
	
	// 스토쿠 행, 열 , 3 * 3 중복 체크 확인 함수
	static boolean Duplicate_check(int x, int y, int number) {
		// row check (해당 행의 중복성 체크) --> ex) 0,1,1 이 매개변수로 넘겨 받았을 때, map[0][0] 좌표의 값과 number이 같으므로 중복 되서 false
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == number) return false;
		}

		// col check (해당 열의 중복성 체크)
		for (int j = 0; j < 9; j++) {
			if (map[j][y] == number) return false;
		}

		// 스토쿠 3 *3 안에 중복이 없는지 체크
		int sr = (x / 3) * 3; // 시작 행
		int sc = (y / 3) * 3; // 시작 열
		int er = sr + 3; // 끝 행
		int ec = sc + 3; // 끝 열
		for (int i = sr; i < er; i++) {
			for (int j = sc; j < ec; j++) {
				if (map[i][j] == number) return false;
			}
		}
		return true;
	}
	
	// 스토쿠 퍼즐 출력 메소드
	static void stoku_print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}