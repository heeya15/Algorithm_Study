import java.io.*;
import java.util.*;

public class Main {
	static int[] damage = {9, 3, 1};
	static int answer;
	static int n;
	static ArrayList<int[]> wayList;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		int[] scv = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}

		answer = Integer.MAX_VALUE;
		result = new int[n];
		visited = new boolean[n];
		wayList = new ArrayList<>();
		findWay(n, 0); // n개일 때 공격가능한 순서(조합) 찾기
		System.out.println(attack(scv));
	}

	static int attack(int[] scv) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(scv, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (checkDestroy(node.scv)) return node.cnt;

			for (int[] way : wayList) {
				int[] curScv = node.scv.clone();
				for (int i = 0; i < n; i++) {
					curScv[way[i]] -= damage[i];
				}
				q.offer(new Node(curScv, node.cnt + 1));
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