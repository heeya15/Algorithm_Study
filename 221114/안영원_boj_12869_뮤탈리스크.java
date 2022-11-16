import java.io.*;
import java.util.*;

public class Main {
	static int[] damage = {9, 3, 1};
	static int answer;
	static int n;
	static ArrayList<int[]> wayList;
	static int[] result;
	static boolean[] visited;
	static boolean[][][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		result = new int[n];
		visited = new boolean[n];
		wayList = new ArrayList<>();

		int[] scv = new int[n];
		check = new boolean[61][61][61];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}

		answer = Integer.MAX_VALUE;
		findWay(n, 0); // n개일 때 공격가능한 순서(조합) 찾기
		System.out.println(attack(scv)); // 찾은 조합으로 최소 횟수 찾기
	}

	static int attack(int[] scv) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(scv, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			
			// 전부 파괴되었다면 횟수를 리턴함(BFS라서 먼저 찾은 값이 최소값)
			if (checkDestroy(node.scv)) return node.cnt;

			for (int[] way : wayList) {
				int[] curScv = node.scv.clone();
				for (int i = 0; i < n; i++) {
					curScv[way[i]] -= damage[i];
					if (curScv[way[i]] < 0) curScv[way[i]] = 0;
				}
				// 남은 HP가 이미 탐색해본 것이라면 추가로 탐색하지 않음
				// n이 3보다 작으면 뒤에 0을 추가해줌
				if (n == 3) {
					if (!check[curScv[0]][curScv[1]][curScv[2]]) {
						check[curScv[0]][curScv[1]][curScv[2]] = true;
						q.offer(new Node(curScv, node.cnt + 1));
					}
				} else if (n == 2) {
					if (!check[curScv[0]][curScv[1]][0]) {
						check[curScv[0]][curScv[1]][0] = true;
						q.offer(new Node(curScv, node.cnt + 1));
					}
				} else {
					if (!check[curScv[0]][0][0]) {
						check[curScv[0]][0][0] = true;
						q.offer(new Node(curScv, node.cnt + 1));
					}
				}
			}
		}

		return 0;
	}

	static boolean checkDestroy(int[] scv) {
		boolean isDestroy = true;

		for (int i = 0; i < n; i++) {
			if (scv[i] > 0) isDestroy = false;
		}

		return isDestroy;
	}

	static void findWay(int end, int cur) {
		if (cur >= end) {
			wayList.add(result.clone());
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				result[cur] = i;
				visited[i] = true;
				findWay(end, cur + 1);
				visited[i] = false;
			}
		}
	}

	static class Node {
		int[] scv;
		int cnt;

		Node (int[] scv, int cnt) {
			this.scv = scv;
			this.cnt = cnt;
		}
	}
}
