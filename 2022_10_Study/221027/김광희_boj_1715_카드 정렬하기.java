package 그리디;

import java.io.*;
import java.util.*;

/**
 * 컴퓨터 수가 모자라게 된 것이다.
 * 이런 사태를 도저히 용납할 수 없었던 준하는 곧 전역하는 선임을 설득해 민원을 넣도록 하는 데 성공
 * 컴퓨터를 증설하기로 했다. 또한, 컴퓨터 간의 [ 사용률에 따라 ] 다른 성능의 컴퓨터를 설치하고자 한다.
 * 모든 사람이 항상 정해진 시간에 싸지방을 이용한다는 사실을 발견
 * 컴퓨터가 있는 자리에는 1번부터 순서대로 번호가 매겨져 있다.
 * 싸지방에 들어왔을 때 비어있는 자리 중에서 번호가 가장 작은 자리에 앉는 것이 규칙
 * 모든 사람이 기다리지 않고 [ 싸지방을 이용할 수 있는 컴퓨터의 최소 개수 ]와 [ 자리별로 몇 명의 사람이 사용했는가 ]를 구하시오.
 * 
 * ( 문제 풀이 )
 * 1. 문제에서 두 묶음식 골라 서로 합쳐나간다와 최소한 몇번의 비교가 필요한지를 구하는 것을 보고
 *    우선 순위 큐를 활용하여 가장 낮은 숫자 부터 묶음으로 합쳐 나아가면 해결할 수 있다고 생각
 * 2. 따라서, N개의 숫자 카드 묶음의 크기를 우선 순위 큐에 넣어줌
 * 3. 우선 순위 큐의 크기가 1인경우 묶음을 만들 수 없기 때문 while 탈출 조건
 *    -> 크기가 1이 아니라면, 가장 낮은 숫자부터 두개의 묶음의 합을 count 변수에 누적해주며, 묶음의 합을 우선순위 큐에 넣어준다.
 */
public class Main_G4_1715_카드정렬하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 숫자 카드 묶음 수
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) pq.offer(Long.parseLong(br.readLine()));
		//  N이 주어진다. (1 ≤ N ≤ 100,000)  -> 1~ 100,000 다 더해주면 int의 범위 초과 됨 
		// -> 그래서 long타입 변수에 비교횟수 누적해줌.
		long count = 0; 
		while(true) {
			if(pq.size() == 1) break; // 우선 순위 큐 안에는 2개이상 있어야 묶음으로 만들 수 있다.
			long temp1 = pq.poll();
			long temp2 = pq.poll();
			// 앞에 두개의 묶음을 비교횟수에 누적 ( 가장 낮은 값부터 먼저 더해줌 )
			count += temp1 + temp2;
			// 앞에 두개의 묶음 비교횟수를 우선 순위 큐에 넣어줌. 
			pq.offer(temp1 + temp2);
		}
		// 최소 비교 횟수 출력
		System.out.println(count);
	}
}
