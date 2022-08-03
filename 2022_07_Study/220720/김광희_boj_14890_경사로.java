package BaekJoon_self;

import java.util.*;
import java.io.*;

public class Main {
	/**
	 * ( 문제 설명 )
	 * 오늘은 이 지도에서 < 지나갈 수 있는 길이 몇 개 > 있는지 알아보려고 한다. 
	 * 길이란 한 행 또는 한 열 전부를 나타내며, < 한쪽 끝에서 다른쪽 끝까지 지나가는 것 >이다. 
	 * 길을 지나갈 수 있으려면 < 길에 속한 모든 칸의 높이가 모두 같아야 > 한다. 
	 * 또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다. 
	 * < 경사로는 높이가 "항상 1" >이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다. 
	 * 
	 * ( 조건 )
	 * 경사로는 "낮은 칸"과 "높은 칸"을 연결하며, 아래와 같은 조건을 만족해야한다.
	 * - 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
	 * - 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
	 * - 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
	 * 
	 * (일반 배열 )
	 * 3 3 3 3 3 3
	 * 2 3 3 3 3 3
	 * 2 2 2 3 2 3
	 * 1 1 1 2 2 2
	 * 1 1 1 3 3 1
	 * 1 1 2 3 3 2
	 * 
	 * ( 거꾸로 배열 )
	 * [3, 2, 2, 1, 1, 1] 
	 * [3, 3, 2, 1, 1, 1] 
	 * [3, 3, 2, 1, 1, 2] 
	 * [3, 3, 3, 2, 3, 3] 
	 * [3, 3, 2, 2, 3, 3]
	 * [3, 3, 3, 2, 1, 2]
	 * 
	 * ( 문제 풀이 ) 
	 * - 시뮬레이션, 구현 알고리즘.
	 * - 가로, 세로 방향을 각각 확인하면서 주어진 조건에 따라 처리하고
	 * - 경사로를 놓을 수 있으면 경사로의 개수 1씩 추가
	 */
	static int N, L;
	static int[][] map, reversemap;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 지도 한변의 크기
		L = Integer.parseInt(st.nextToken()); // 경사로의 길이.
		map = new int[N][N];
		reversemap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());// 일반적인 배열
				reversemap[j][i] = map[i][j]; // 행과 열을 바꾼 배열
			}
		}
		// 경사로 길이 X, 높이 1
		for (int i = 0; i < N; i++) {
			// 가로방향
			if (build_count(map, i))count++;
			// 세로방향
			if (build_count(reversemap, i))count++;
		}
		// 활주로를 건설할 수 있는 경우의 수를 계산하시오.
		sb.append(count).append("\n");
		System.out.println(sb);
	}

	// 가로, 세로 방향에 대한 맵 정보 , 인덱스를 매개변수로 받음.
	private static boolean build_count(int[][] map, int index) {
		// 322232 경우 "이전 경사로랑 겹치는 부분 처리" 해주기 위해 사용.
		boolean[] isCheck = new boolean[N]; // 해당 열에 경사로 설치를 했는지 체크.
		for (int i = 0; i < N - 1; i++) {
			int prev = map[index][i];
			int next = map[index][i + 1];
			// 경사로의 높이는 1이하만 차이가 남. --> 그대로 or 1차이.--> 무조건 한 칸의 경사로 차이.
			if (Math.abs(prev - next) <= 1) {
				// 평지거나 이미 "같은 높이 지형"이 있다면 패스
				if (isCheck[i + 1] || prev == next) continue;

				if (prev > next) { // (이전 값이 다음값 보다 클 경우 )내려갈 때
					// 현재 낮아지는 구간부터 경사로 지형 설치 --> 경사로 길이 만큼.
					for (int j = i + 1; j <= i + L; j++) {
						// 경계값을 넘어가거나, 경사로를 설치한 부분의 값과 일치하지 않다면(평지가 아니거나),
						// 이미 지형이 경사로를 설치 했다면 false.
						if (j == N || map[index][j] != next || isCheck[j]) return false; // 지형의 마지막은 값 그대로 가져가기 위한 if문
						isCheck[j] = true; // 지형 설치 체크
					}
				} else { // (이전 값이 다음값 보다 작을 경우 ) 올라갈 때
					// 현재 낮은 경사로에서, 왼쪽으로 경사로 지형 길이만큼 줄여감.
					for (int j = i; j > i - L; j--) {
						// 경계값 초과, 현재 값과, 이전 값이 같지 않다면 경사로의 높이차이가 2가 됨.
						// 이미 방문 했었던 경사로 열쪽이면 겹쳐서 x
						if (j < 0 || map[index][j] != prev || isCheck[j]) return false;
						isCheck[j] = true;
					}
				}
			} else return false; // 차이가 2 이상 날 경우 return false;
		}
		return true;
	}
}