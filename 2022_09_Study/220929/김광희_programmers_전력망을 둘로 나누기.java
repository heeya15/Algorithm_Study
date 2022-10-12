package Programmers;

import java.util.*;
/**
두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록
( 문제 풀이 )
1. graph에  양방향 그래프 정보를 입력 받아 셋팅해 준다.
2. bfs를 통해 v1을 기준으로 다음으로 가는 노드가 매개변수로 < 주어진 v2 송전탑 연결된 부분이 아니며 >, 
     방문하지 않았을 경우 큐에 넣어준다 -> 그래야 v2 전선을 끊고 해당 v1 노드를 기준으로 송전탑 개수를 세어줄 수 있음
   -> 즉, v1에서 v2로 가는 경우를 제거해준다는 의미이다.
3. 2번 과정을 모든 간선에 대하여 완전탐색을 하여 ( 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)가 ) 가장 적게 나는 것을 return 시켜줌
**/
public class Lv2_전력망을둘로나누기 {
	public static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws Exception {
		int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
		System.out.println(solution(9, wires));
	}
	public static int solution(int n, int[][] wires) {
		int answer = Integer.MAX_VALUE;
		graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < wires.length; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			// 양방향 그래프 생성
			graph[a].add(b);
			graph[b].add(a);
		}
		// 하나씩 끊어본다
		for (int i = 0; i < wires.length; i++) {
			int a = wires[i][0];
			int b = wires[i][1];
			int cnt1 = bfs(a, b, n);
			int cnt2 = bfs(b, a, n);
			answer = Math.min(answer, Math.abs(cnt1 - cnt2));
		}
		return answer;
	}

	public static int bfs(int v1, int v2, int n) {
		int count = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		q.add(v1);
		visited[v1] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			count++;
			for (int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				// 다음으로 가는 노드가 매개변수로 주어진 v2 송전탑 연결된 부분이 아니며, 방문하지 않았을 경우
				if (next != v2 && visited[next] == false) {
					q.add(next); // 큐에 넣어준다 -> 그래야 v2 전선을 끊고 해당 v1 노드를 기준으로 송전탑 개수를 세어줄 수 있음
					visited[next] = true;
				}
			}
		}
		return count;
	}
}