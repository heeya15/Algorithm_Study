package BaekJoon_self;

import java.util.*;
import java.io.*;

public class Main {
	/**
	 * 여기에서 체력(M)은 진우가 이동할 수 있는 거리를 나타낸다. 
	 * 진우는 지도상에서 상, 하, 좌, 우로 1칸씩 이동할 수 있으며 "이동하면 체력이 1만큼" 줄어든다. 
	 * 마을을 돌아다니다가 민트초코우유를 마신다면 "체력이 H 만큼 증가하며" 진우의 체력이 초기체력 이상으로 올라갈 수 있다.
	 * 체력이 0이 되는 순간 진우는 이동할 수 없다.
	 * 마을 한복판에서 체력이 0이 되어 집으로 못 돌아가는 상황은 만들어져서는 안된다.
	 * 진우가 얼마나 많은 [ 민트초코우유를 마시고 집으로 돌아올 수 있는지 ] 알아보자.
	 * 
	 * ( 입력 )
	 * 지도상 진우 집은 : 1
	 * 민트 초코 우유는 : 2
	 * 빈 땅 : 0
	 * 
	 * ( 문제 풀이 )
	 * 1. 진우의 집 좌표는 (houseX,houseY) 변수에 저장 
	 *    민트 초코우유 좌표 정보는 ArrayList<int[]> mint_milk 에 담아둔다.
	 * 2. 민트 초코우유 좌표 정보 개수 만큼 -> numbers, visited 배열 사이즈 초기화
	 * 3. 민트 초코우유가 있는 좌표 정보를 "순열로 구한 ArrayList 인덱스"에 따라 사용한다.
	 * 4. 현 좌표에서 다음 민트 초코우유 까지의 거리와, 진우 집에서 다음 민트 초코우유 까지의 거리를 구한다.
	 *    그 후 현 좌표에서 다음 민트 초코유 까지의 거리가  -> 진우 hp 보다 작거나 같다면 마시러 갈 수 있고, 마시러 갈 수 있는 민트 초코우유의 개수(count)증가.
	 *    < 집에서 다음 민트 초코우유 까지의 거리 > 또한 "hp 보다 작거나 같다면" 
	 *    마시러 갈 수 있는 민트 초코우유의 개수(count)와 최종 결과(result)를 비교하여 더 큰 값으로 갱신 시켜준다.
	 */
	static class Pos{
		int x,y;
		public Pos(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	static int N,M,H;
	static int result;
	static int[][] map;
	static int houseX, houseY;
	static ArrayList<Pos>mint_milk;
	static int [] numbers;
	static boolean [] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		result = 0;
		N = Integer.parseInt(st.nextToken());  // 마을 크기
		M = Integer.parseInt(st.nextToken());  // 진우 초기 체력
		H = Integer.parseInt(st.nextToken());  // 민트초코우유 마실때 마다 증가하는 체력 양
		map = new int[N][N];
		mint_milk = new ArrayList<>();
		
		//  민초마을 지도 정보
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					houseX = i;
					houseY = j;
				}
				else if(map[i][j] == 2) mint_milk.add(new Pos(i,j));
			}
		}
		visited = new boolean[mint_milk.size()];
		numbers = new int[mint_milk.size()];

		per(0);
		// 출력
		System.out.println(result);
	}
	
	public static void per(int depth) {
		// 민트 초코 우유 개수만큼 순열이 완성 되었다면
		if(depth == mint_milk.size()) {
			Solution(numbers); // 집을 나와 다시 집으로 돌아올 때 까지 마실 수 있는 민트 초코우유의 개수 구하는 함수 실행.
			return;
		}
		
		for(int i = 0; i < mint_milk.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				numbers[depth] = i; // 민트 초코 우유를 마시러 가는 인덱스 순열 담기. 
				per(depth+1);
				visited[i] = false;
			}
		}
	}
	
	public static void Solution(int [] arr) {
		int hp = M;
		// 현재 좌표 초기화.
		int x = houseX;
		int y = houseY;
		int count = 0; // 민트초코우유 마신 갯수.
		for (int i = 0; i < arr.length; i++) {
			int idx = arr[i];
			// 현재 좌표에서 민트 초코 우유 마시러 가는 거리.
			int dist = Math.abs(x - mint_milk.get(idx).x) + Math.abs(y - mint_milk.get(idx).y);
			// 현 좌표에서 집 까지 돌아오는 거리 
			int back_home = Math.abs(houseX - mint_milk.get(idx).x) + Math.abs(houseY - mint_milk.get(idx).y );
			// 초기 체력보다 '이동 거리'가 크지 않다면
			if(hp >= dist ) {
				count++;
				hp -= dist; // 체력을 거리만큼 쓴다. 
				hp += H;   // 체력 추가 됨.
				if(hp >= back_home) result = Math.max(result, count);
				x = mint_milk.get(idx).x;
				y = mint_milk.get(idx).y;
			}
			else return; // 마시러 갈 수 없는 거리 케이스는 더 이상 사용할 수 없는 순열로 return 시킴.
		}
	}
}