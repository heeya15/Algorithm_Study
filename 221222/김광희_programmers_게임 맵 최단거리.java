package BFS;

import java.util.*;
/**
 * ( 문제 풀이 ) 
 * 1. 단순 DFS/BFS 문제이다. 
 * 2. 그냥 0,0 부터 상대팀 진영 오른쪽 대각선 끝 즉 maps[maps.length - 1][maps[0].length - 1]
 *    까지 가장 적은 칸으로 이동가능한 칸의 개수를 return
 *    -> 벽(1) 이 아니거나, maps 공간 밖이 아닌경우 방문 처리 및 칸의 개수 증가 시켜줌.
 */
public class Lv2_게임맵최단거리 {
	public static class Pos {
		int r, c, time;
		Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	// 델타 배열 : 상, 하, 좌, 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int answer = Integer.MAX_VALUE;
	static int end_r, end_c;

	public static void main(String[] args) throws Exception {
		int maps[][] = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, 
						 { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 },
						 { 0, 0, 0, 0, 1 } };
		System.out.println(solution(maps));
	}

	public static int solution(int[][] maps) {
		end_r = maps.length - 1;
		end_c = maps[0].length - 1;
		visited = new boolean[end_r + 1][end_c + 1];
		bfs(0, 0, maps);
		// 도착지 이동 불가능 할때 -1
		if (answer == Integer.MAX_VALUE) answer = -1;
		return answer; // 도착지에 도착하였을때 최단 거리 출력
	}

	private static void bfs(int sr, int sc, int maps[][]) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sr, sc, 1));
		visited[sr][sc] = true;
		while (!q.isEmpty()) {
			Pos temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int time = temp.time;
			if (r == end_r && c == end_c) {
				answer = Math.min(answer, time);
			}
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr > end_r || nc > end_c) continue;

				if (maps[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Pos(nr, nc, time + 1));
				}
			}
		}
	}
}
