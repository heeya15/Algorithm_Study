package 그리디;

import java.io.*;
import java.util.*;
/**
 * ( 문제 풀이 )
 * 1. 물이 새는 곳의 위치를 저장 한 뒤 오름차순으로 정렬
 * 2. 처음 위치에 테이프 길이 만큼 붙여준다.  -> 이 때 테이프 개수를 1로 셋팅, 이미 테이프를 처음 위치 물이 세는 곳의 위치에 붙여줬기 때문
 * 3. 처음 테이프 위치를 붙인 곳 보다 더 큰 범위의 물이 세는 곳의 위치가 들어왔을경우, 
 *    - 처음 테이프 위치 붙인 범위 값을 더 큰 범위의 물이 세는 곳의 위치에 테이프를 붙인 범위로 갱신을 시켜준다.
 *    - 테이프의 개수를 증가시켜준다.
 */
public class Main_S3_1449_수리공항승 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수
		int L = Integer.parseInt(st.nextToken()); // 테이프의 길이
		st = new StringTokenizer(br.readLine(), " ");
		int[] loc = new int[N]; // 물이 새는 곳의 위치 저장.

		for (int i = 0; i < N; i++) {
			loc[i] = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 위치 저장.
		}
		Arrays.sort(loc); // 오름 차순으로 정렬.
		int count = 1;
		//     0.5~ 2.5  테이프 붙인상황 따라서 2.5 보다 초과될 경우 
		//     처음 테이프 위치 붙이는 
		double location = ((loc[0] + L) - 0.5);
		for (int i = 1; i < N; i++) {
			double next_loc = loc[i] + 0.5;
			// 처음 위치 테이프를 붙인 범위보다 큰 위치가 들어올 경우
			if(location < next_loc) {
				// 현재 위치에서 테이프를 붙인 범위로 갱신
				location=(next_loc + L)-0.5;
				count++;
			}
		}
//				출력
		System.out.println(count);
	}
}
