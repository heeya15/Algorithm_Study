package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_2239 {

	static int SIZE = 9;
	static int[][] map = new int[SIZE][SIZE];
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < SIZE; i++) {
			String input = br.readLine();
			for(int j = 0; j < SIZE; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		sudoku(0, 0);
	}
	
	static void sudoku(int row, int col) {
		
		// 열 끝에 왔을 때
		if(col == SIZE) {
			// 다음 행부터 탐색
			sudoku(row + 1, 0);
			return;
		}
		
		// 탐색 완료
		if(row == SIZE) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}
		
		// 0은 스도쿠 판의 빈칸을 의미
		if(map[row][col] == 0) {
			// 스도쿠 숫자 1~9까지
			for(int i = 1; i <= 9; i++) {
				// 중복 검사
				if(check(row, col, i)) {
					map[row][col] = i;
					sudoku(row, col + 1);
				}
			}
			
			map[row][col] = 0;
			return;
		}
		
		sudoku(row, col + 1);
	}
	
	static boolean check(int row, int col, int num) {
		// 9x9 확인
		for(int i = 0; i < SIZE; i++) {
			// 행에 같은 숫자가 존재하면 안 된다.
			if(map[row][i] == num) return false;
			
			// 열에 같은 숫자가 존재하면 안 된다.
			if(map[i][col] == num) return false;
		}
		
		
		// 3x3 확인
		int setRow = (row / 3) * 3;
		int setCol = (col / 3) * 3;
		
		
		for(int i = setRow; i < setRow + 3; i++) {
			for(int j = setCol; j < setCol + 3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}
