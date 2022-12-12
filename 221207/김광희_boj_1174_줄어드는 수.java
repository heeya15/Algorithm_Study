package BaekJoon_self;

import java.util.*;
import java.io.*;

/**
 * ( 문제풀이)
 * 1. 9부터 0을 내림차순으로 저장한 배열을 사용 -> 만들어진 수가 줄어드는 수 인지 따로 확인할 필요가 없기 때문
 * 2. 줄어드는 수를 만드는 방법은 ( 현재 배열의 값 선택 or 선택하지 않느냐 )
 *    즉, desc_num_arr 배열의 현재 수를 선택 하느냐, 선택하지 않느냐 두 가지 경우만 존재하므로 각각의 경우 모두 dfs(재귀호출) 해주면 된다.  
 *    -> 재귀를 돌면서 list에 내림차순 숫자가 저장되어 있지 않다면 넣어준다.
 *    -> 현재 배열의 값을 선택한다면  (만든 수에 * 10)을 한 뒤 desc_num_arr을 내림차순 숫자에 더해주면 무조건 숫자는 내림차순
 *       선택하지 않는다면 배열의 인덱스만 증가시켜주고 재귀호출 하면 된다.
 *    -> 내림차순으로 저장한 배열 모든 인덱스 탐색이 끝났다면 재귀 종료.
 * 3. 숫자는 겹치게 선택할 수 없다 -> 왼쪽에서 부터 자리수가 감소한다고 하였기 때문에 9876543210이 가장 큰 내림차순 숫자 이다.
 *    따라서 여기에서 10개의 숫자를 선택하냐 안하냐의 모든 경우의 수는 1024개 이기 때문
 *    -> 1023 "이상"이면 왼쪽에서부터 자리가 감소하는 숫자가 없어서 < -1 을 return >
 *    -> 1024 경우의 수 안이라면 list에 저장한 < n번째 숫자를 return >
 */
public class Main_G5_1174_줄어드는수 {
	static int[] desc_num_arr= {9,8,7,6,5,4,3,2,1,0}; // 내림차순 배열
	static ArrayList<Long> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dfs(0,0);
		Collections.sort(list); // n번째 수를 찾기 위해 정렬
		
		if(N > 1023) System.out.println(-1);
		else System.out.println(list.get(N-1));
	}

	private static void dfs(long number, int index) {
		// number이 이전에 나온적이 없는 줄어드는 숫자라면 list에 저장
		if(!list.contains(number)) list.add(number);
		
		// 내림차순 배열로 모두 탐색했다면 재귀호출 종료
		if(index >= 10) return;
		
		dfs((number * 10) + desc_num_arr[index],index + 1); // 현재 수를 선택하는 경우-> 만든 수에 * 10을 한 뒤 desc_num_arr 을 뒤에 더해주면 무조건 숫자는 내림차순
		dfs(number, index+1); 				                // 현재 수를 선택하지 않는 경우
	}
}
