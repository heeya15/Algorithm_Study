import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			boolean[] solo = new boolean[n + 1];
			
			// 인접리스트 생성
			ArrayList<Integer>[] list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) list[i] = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				int next = Integer.parseInt(st.nextToken());
				if (i == next) solo[i] = true;
				else list[i].add(next);
			}

			int result = 0;

			// i에서 출발해서 다시 돌아올 수 있는지 확인
			// 돌아오지 못하면 팀이 없는 것
			for (int i = 1; i <= n; i++) {
				if (solo[i]) continue;

				boolean isCan = false;
				Queue<Integer> q = new LinkedList<>();
				ArrayList<Integer> team = new ArrayList<>();

				q.offer(i);
				while (!q.isEmpty()) {
					int cur = q.poll();
					team.add(cur);
					// System.out.println(cur);

					for (int j = 0; j < list[cur].size(); j++) {
						int next = list[cur].get(j);
						if (next == i) isCan = true;
						else q.offer(next);	
					}
				}
				if (!isCan) result++;
				else {
					for (int member : team) solo[member] = true;
				}
			}
			sb.append(result + "\n");
		}
		System.out.println(sb);
	}
}