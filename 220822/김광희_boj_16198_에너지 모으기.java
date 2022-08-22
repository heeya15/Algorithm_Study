package 백트래킹;
import java.util.*;
import java.io.*;
/**
 * ( 문제 풀이 )
 * 1. 에너지를 모으는 방법을 그대로 적용하여 구현
 * 2. dfs를 통하여 해당 i번째 값 구슬 무게를 제거하고, 모을 수 있는 에너지 양을 누적(energy)한다.
 * 3. 만약 N-2 깊이 만큼 에너지 양을 누적하였을 경우, 모을 수 있는 에너지 양(result)이 "새로 구한 energy 에너지 양" 보다 작다면 
 *    < 더 큰 에너지 양으로 갱신 > 해 준다.
 * 4. 그 후, 다시 list에 사용했던 에너지를 원래 있던 index 위치에 넣어준다.
 *    2 ~ 4번 과정을 반복하여 해결.
 */
public class Main_S1_16198_에너지모으기 {
	static int N, result ;
	static ArrayList<Integer>list;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구슬의 개수 입력
		
		// 리스트 및 모을 수 있는 에너지 최댓값을 저장할 변수 초기화
		list = new ArrayList<>();
		result = Integer.MIN_VALUE;
		
		// 에너지 구슬의 무게를 list에 넣어줌.
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) list.add(Integer.parseInt(st.nextToken()));
		
		dfs(0,0);
		System.out.println(result);
	}
	public static void dfs(int depth, int energy) {
		if(depth == N-2) {
			result = Math.max(result, energy);
			return;
		}
		for (int i = 1; i < list.size()-1; i++) {
			int x = list.get(i); // 제거할 에너지 구슬의 번호 저장 
//			3. (n-1 * n+1) 에너지를 모을 수 있다
			int sum = list.get(i-1) * list.get(i+1);
//		    2. 에너지 구슬(x)을 제거 한다.
			list.remove(i);
			dfs(depth+1, energy +sum );
//			다시 사용했던 에너지를 원 위치 인덱스에 넣어준다.
			list.add(i, x);	
		}
	}
}
