import java.io.*;
import java.util.*;

public class Main {
	static int confirm;
	static int[] want; // 원하는 팀원
	static boolean[] visited; // 이미 탐색한 인원
	static boolean[] dontGo; // 팀 구성이 안되는 사람(한번더 탐색 방지)

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			want = new int[n + 1];
			visited = new boolean[n + 1];
			dontGo = new boolean[n + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				want[i] = Integer.parseInt(st.nextToken());
			}

			confirm = 0;
			for (int i = 1; i <= n; i++) {
				check(i);
			}
			sb.append(n - confirm + "\n");
		}
		System.out.println(sb);
	}

	static void check(int cur) {
		if (visited[cur]) return;

		visited[cur] = true;

		// 방문하지 않았다면 끝까지 확인
		if (!visited[want[cur]]) check(want[cur]);
		// 이미 방문했던 곳을 재 방문하는 경우
		// 사이클이 생성된 것이기 때문에 사이클(팀 인원)을 확인하고 수를 더해줌
		else {
			if (!dontGo[want[cur]]) {
				confirm += 1;
				for (int i = want[cur]; i != cur; i = want[i]) confirm += 1;
			}
		}

		// 모든 확인이 끝난 학생이기 때문에 체크
		dontGo[cur] = true;
	}
}
