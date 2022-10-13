package 구현;

import java.util.*;
import java.io.*;
/**
 * ( 문제 풀이 )
 * 1. 1~4번 톱니바퀴의 상태를 2차원배열 gear에 입력받는다.
 * 2. 각 톱니바퀴의 방향 상태를 저장해 두기 위하여, 회전 횟수에 따라 매번 arr_dir를 초기화 시켜준다.
 * 3. 현 상태에서 모든 톱니바퀴 체크
 * 4. 모든 톱니바퀴의 회전 방향들을 arr_dir 각 톱니바퀴 인덱스에 저장한 후
 *    해당 톱니바퀴 인덱스에게 < 해당 톱니바퀴 인덱스의 회전 방향 >으로 돌려준다.
 */
public class Main_G5_14891_톱니바퀴 {
	static int [][] gear;
	static final int size = 8;
	static int [] arr_dir;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new int[4][size];
		// 1~4번 톱니바퀴의상태가 주어진다.
		for (int i = 0; i < 4; i++) {
			char [] temp = br.readLine().toCharArray();
			for (int j = 0; j < size; j++) {
				gear[i][j] = temp[j] - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());// 회전 횟수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int number = Integer.parseInt(st.nextToken()) -1; // 톱니바퀴의 번호
			int dir = Integer.parseInt(st.nextToken());    // 방향
			arr_dir = new int[4];
			arr_dir[number] = dir;
			// 톱니바퀴 번호는 1~4 이지만 -> 배열 인덱스는 0~3
            solution(number, dir);
		}
		
		int answer = 0;
		int [] score = {1, 2, 4, 8 };
		
		for(int i = 0; i < 4 ; i++) if(gear[i][0] == 1) answer += score[i];
		
		System.out.println(answer);
	}
	 
    static void solution(int number, int dir) {
    	// 현 상태에서 모든 톱니바퀴 체크
        left_check(number - 1, dir * -1);
        right_check(number + 1, dir * -1);
        // 회전 방향 모두 설정한 후 한 꺼번에 회전
        for (int j = 0; j < 4; j++) rotate(j, arr_dir[j]);  
    }
   //기준 톱니바퀴의 좌측 톱니바퀴 회전 방향 검사
    public static void left_check(int number, int dir) {
		if(number < 0) return;	
		// 기준 톱니바퀴와, 기준 톱니바퀴의 왼쪽 톱니바퀴 서로 맞닿는 부분의 극이 다를경우 
		if( gear[number + 1][6] != gear[number][2]) {
			arr_dir[number] = dir; // 해당 톱니바퀴의 회전은 기준 톱니바퀴 방향의 "반대 방향"으로 돌려준다
			left_check(number - 1, dir * -1);
		}		
	}
    
    //기준 톱니바퀴의 우측 톱니바퀴 회전 방향 검사
	public static void right_check(int number, int dir) {
		if(number > 3) return;	
		// 기준 톱니바퀴와, 기준 톱니바퀴의 오른쪽 톱니바퀴 서로 맞닿는 부분의 극이 다를경우 
		if( gear[number - 1][2] != gear[number][6]) {
			arr_dir[number] = dir; // 해당 톱니바퀴의 회전은 기준 톱니바퀴 방향의 "반대 방향"으로 돌려준다
			right_check(number + 1, dir * -1);
		}		
	}
	
	// dir = 1 시계방향, dir = -1 반시계 방향
    static void rotate(int idx, int dir) {
    	int temp = 0;
        if(dir == 1) {
            temp = gear[idx][7]; // 해당 톱니바퀴의 제일 마지막 원소의 값을 저장.
            for (int i = size - 1; i > 0; i--) gear[idx][i] = gear[idx][i - 1];
            gear[idx][0] = temp;
        } 
        if(dir == -1){
            temp = gear[idx][0]; // 해당 톱니바퀴의 제일 첫 원소의 값을 저장.
            for (int i = 0; i < size - 1; i++) gear[idx][i] = gear[idx][i + 1];
            gear[idx][7] = temp;
        }
    }	
}