package BFS;

import java.util.*;
import java.io.*;

public class Main_S1_12761_돌다리 {
	/**
	 * ( 문제 설명 )
	 *  돌의 번호는 0 부터 100,000 까지 존재하고 
	 *  동규는 (N)번 돌 위에, 주미는 (M)번 돌 위에 위치하고 있다. 
	 *  < 동규는 주미가 너무 보고싶기 때문에 > 최대한 빨리 주미에게 가기 위해 
	 *  (A, B) 만큼의 힘을 가진 스카이 콩콩을 가져왔다.
	 *  동규가 정한 다리를 건너는 규칙은 < 턴 방식 >인데, < 한 턴에 이동할 수 있는 거리 >는 이러하다. 
	 *  (1). [ 현 위치에서 +1칸, -1칸을 이동 ]할 수 있고, 
	 *  (2). 스카이 콩콩을 이용해 현 위치에서 (A)나 (B)만큼 [ 좌우로 점프 ]할 수 있으며, -> A, -A, B, -B
	 *  (3). < 순간적으로 힘을 모아 현 위치의 (A)배나 (B)배의 위치로 이동 >을 할 수 있다. 
	 *  예를 들어 [ 지금 동규가 7번 돌 위에 ] 있고 < 스카이 콩콩의 힘이 8이면 > "그냥 점프를 해서 15번 돌"에 갈 수도 있고, 
	 *  "순간적으로 힘을 모아 56번 돌에 갈 수도 있다는 것"이다. 
	 *  주어진 8가지의 방법 중 < 적절한 방법을 골라서 최대한 빨리 동규가 [ 주미를 만날 수 있게 ] 도와주자. > 
	 *  
	 *  ( 문제 풀이 )
	 *  (1). bfs를 수행하는대 큐에는 < 동규의 현재 위치 값을 > 넣어준다.
	 *  (2). < 주어진 8가지 방법 중 사용하여 이동 할 수 있다면 > < 이동 횟수를 stones 배열에 저장 >하고 
	 *       큐에 동규가 이동한 현재 위치 값을 저장해 줌
	 *  (3). 방문 했던 좌표는 배열 값이 0이 아닌 경우에 해당해서 [ 따로 방문 체크 배열을 쓰지 않음 ]
	 *  (4). 최종 적으로 위와 같은 과정을 반복하다 "동규 현재 위치가 주미의 위치와 같을 경우" 
	 *       "해당 주미 위치 값인" stones 배열 인덱스에 최소 이동 횟수가 담겨 있어서 출력해 준다.
	 */
	static int A,B,N,M;
	static Queue<Integer> q;
	static int[] stones;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 스카이 콩콩의 힘 A, B
		A = Integer.parseInt(st.nextToken()); 
		B = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(st.nextToken());// 동규의 현재 위치
		M = Integer.parseInt(st.nextToken());// 주미의 현재 위치
		
		bfs();
	}
	static void bfs() {
		stones = new int[100001];
		q = new LinkedList<Integer>();
		q.offer(N); // 동규 현재 위치를 넣는다.
		
		while (!q.isEmpty()) {
			int x = q.poll();
			if (x == M) { // 동규 현재 위치가 주미의 위치와 같을 경우 
				System.out.println(stones[M]); // 해당 위치 인덱스에 < 최소 이동 횟수를 출력 >해 준다.
				return;
			}
			move(x); // 8가지 방법을 사용하여 이동.
		}
	}

	static void move(int n) {
		// 1. 현 위치에서 -1, +1 
		if(n - 1 >= 0 && stones[n - 1] == 0 ) {
			stones[n - 1] = stones[n] + 1;
			q.offer(n - 1);
		}
		if(n + 1 <=100000 && stones[n + 1] == 0 ) {
			stones[n + 1] = stones[n] + 1;
			q.offer(n + 1);
		}
		// 2. 스카이 콩콩을 이용해 현 위치에서 (A)나 (B)만큼 [ 좌우로 점프 ] ex) A, -A, B, -B
		if(n + A <=100000 && stones[n + A] == 0 ) {
			stones[n + A] = stones[n] + 1;
			q.offer(n + A);
		}
		
		if(n - A >= 0 && stones[n - A] == 0 ) {
			stones[n - A] = stones[n] + 1;
			q.offer(n - A);
		}
		
		if(n + B <=100000 && stones[n + B] == 0 ) {
			stones[n + B] = stones[n] + 1;
			q.offer(n + B);
		}
		
		if(n - B >= 0 && stones[n - B] == 0 ) {
			stones[n - B] = stones[n]+1;
			q.offer(n - B);
		}
		
		// 3. < 순간적으로 힘을 모아 현 위치의 (A)배나 (B)배의 위치로 이동 >
		if(n * A <=100000 && stones[n * A] == 0 ) {
			stones[n * A] = stones[n] + 1;
			q.offer(n * A);
		}
		if(n * B <=100000 && stones[n * B] == 0 ) {
			stones[n * B] = stones[n] + 1;
			q.offer(n * B);
		}	
	}
}