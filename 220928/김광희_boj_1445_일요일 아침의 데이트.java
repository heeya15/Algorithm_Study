package BFS;

import java.util.*;
import java.io.*;
/**
 * 한 가지는 쓰레기를 < 통과해서 지나가는 것 >을 정말 싫어하는 것
 * 쓰레기를 따라 < 옆을 지나가는 것 >도 정말 불편하게 느낀다는 것이다.
 * S: 형택이와 여자친구의 데이트 시작장소
 * F: 꽃이 있는 위치
 * g: 쓰레기가 있는 위치 
 * .: 아무것도 없는 깨끗한 칸이다.
 * 형택이의 목표 : S에서 F까지 가는데, 쓰레기로 차있는 칸을 되도록이면 적게 지나가는 것
 * 형택이와 여자친구는 < 한 번에 한 칸 움직일  수 있다. -> 가로 or 세로로 한 칸 움직일 수 있다
 * 만약 되도록 적게 지나가는 경우의 수가 여러개라면, "쓰레기 옆을 지나가는 칸의 개수를 최소"로 해서 지나려고 한다. 
 * S와 F는 세지 않는다.
 * 
 * ( 문제 풀이 )
 * 1. 쓰레기 밟은 횟수가 가장 적은 것을 꺼내준다, 만약 동일하다면 쓰레기 옆을 지나가는 칸의 개수가 적은 것을 꺼내줌
 * 2. 4방 탐색으로 이동할 칸에 쓰레기가 존재한다면 쓰레기 밟은 횟수를 증가해서 넣어준다.
 *    존재 하지 않다면 해당 칸에 또 4방 탐색으로 이웃한 곳에 쓰레기가 있는지 검증을 해 주고 쓰레기 옆을 지나간다면 
 *    < 쓰레기 옆을 지나가는 칸의 개수를 증가 >시켜 주고 맨 밖의 쓰레기가 존재하는지의 4방 탐색 for문으로 바로 이동시켜준다.
 * 3. 쓰레기도 밟지 않고, 쓰레기 옆을 지나가지도 않았다면 개수 증가 없이 "우선 순위 큐에 " 넣어줌
 * 4. 꽃이 있는 위치에 도착했다면 현재까지 쓰레기 밟은 횟수, 쓰레기 옆을 지나가는 칸의 개수를  
 *     min_garbage_count, min_next_count 변수에 넣어주고, while문 탐색 종료 후 해당 변수를 출력해 준다.
 */
public class Main_G2_1445_일요일아침의데이트 {
	public static class Node implements Comparable<Node>{
		int x, y, step_count, next_count; // 좌표, 쓰레기 밟은 횟수, 쓰레기 옆을 지나가는 칸의 개수
		public Node(int x, int y, int step_count, int next_count) {
			this.x = x;
			this.y = y;
			this.step_count = step_count;
			this.next_count = next_count;
		}
		public int compareTo(Node n) {
			if(this.step_count != n.step_count) {
				return this.step_count - n.step_count;
			}
			return this.next_count - n.next_count;
		}
	}
	// 상,하,좌,우
	static int[] dr = { -1,1,0,0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M, flower_x, flower_y, now_x, now_y;
	static int min_garbage_count, min_next_count;
	static char [][] map;
	static boolean [][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			char [] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp[j];
				if(map[i][j] == 'F') {
					flower_x = i;
					flower_y = j;
				}
				if(map[i][j] == 'S') {
					now_x = i;
					now_y = j;
				}
			}
		}
		bfs();
		System.out.println(min_garbage_count +" " + min_next_count);
	}
	public static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(now_x, now_y, 0, 0));
		
		top: while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(visited[node.x][node.y]) continue;
			visited[node.x][node.y] = true;
			
			top2:for (int i = 0; i < 4; i++) {
				int nx = node.x + dr[i];
				int ny = node.y + dc[i];
				if(nx < 0 | ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
				if(nx == flower_x && ny == flower_y) {
					min_garbage_count = node.step_count; // 쓰레기의 최소 개수
					min_next_count = node.next_count;    // 지나가는 쓰레기의 최소 개수
					break top;
				}
				if(map[nx][ny] == 'g') { // 현재 위치가 쓰레기여서, 쓰레기를 밟을경우
					pq.add(new Node(nx,ny, node.step_count+1, node.next_count));
				}else { // 쓰레기가 아닌 경우
					// 4방 탐색을 통해 쓰레기가 있는지 확인 -> 이웃한 곳에 쓰레기가 있다면 < 쓰레기 옆을 지나가는 칸의 개수 증가>
					for (int j = 0; j <4; j++) {
						int nr = nx + dr[j];
						int nc = ny + dc[j];
						if(nr < 0 | nc < 0 || nr >= N || nc >= M ) continue;
						if(map[nr][nc] == 'g') {
							pq.add(new Node(nx,ny,node.step_count, node.next_count+1));
							continue top2; // 그 후 다시 맨 밖의 쓰레기를 탐색하는 4방 탐색하는 for문으로 이동
						}
					}	
				}
				// 여기 까지 왔다면 쓰레기도 밟지 않고, 쓰레기 옆을 지나가지도 않았다는 의미여서 개수 증가 없이 "우선 순위 큐에 " 넣어줌
				if(map[nx][ny] == '.') pq.add(new Node(nx,ny, node.step_count, node.next_count));	
			}
		}
	}
}