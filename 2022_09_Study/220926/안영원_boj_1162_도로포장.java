import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 안영원_boj_1162_도로포장 {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Road>[] citys = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) citys[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 양방향
			citys[start].add(new Road(end, cost, 0));
			citys[end].add(new Road(start, cost, 0));
		}
		
		long[][] dijk = new long[N + 1][K + 1]; // [해당 도시까지 최솟값][포장 횟수]
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dijk[i], Long.MAX_VALUE);
		}
		
		PriorityQueue<Road> q = new PriorityQueue<>();
		q.offer(new Road(1, 0, K));
		dijk[1][0] = 0;
		
		while (!q.isEmpty()) {
			Road road = q.poll();
			// 현재지점에 저장된 최소시간이 현재 시간보다 적다면 굳이 탐색할 필요없음
			if (dijk[road.end][K - road.canCover] < road.cost) continue;
			
			for (Road cur : citys[road.end]) {
				// 포장안하고 가기
				if (dijk[cur.end][K - road.canCover] > road.cost + cur.cost) {
					dijk[cur.end][K - road.canCover] = road.cost + cur.cost;
					q.offer(new Road(cur.end, dijk[cur.end][K - road.canCover], road.canCover));
				}
				// 포장이 가능하다면 포장하기
				if (road.canCover > 0 && dijk[cur.end][K - road.canCover + 1] > road.cost) {
					dijk[cur.end][K - road.canCover + 1] = road.cost;
					q.offer(new Road(cur.end, dijk[cur.end][K - road.canCover + 1], road.canCover - 1));
				}
			}
		}
		// 여러 포장횟수 중 목적지까지 시간이 제일 최소인 값을 찾음
		long result = Long.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			result = Math.min(dijk[N][i], result);
		}
		System.out.println(result);
		
	}
	// 시간이 적게드는 순서대로 정렬
	static class Road implements Comparable<Road> {
		int end;
		long cost;
		int canCover; // 포장이 가능한 횟수
		
		Road (int end, long cost, int canCover) {
			this.end = end;
			this.cost = cost;
			this.canCover = canCover;
		}

		@Override
		public int compareTo(Road o) {
			// TODO Auto-generated method stub
			return (int) (cost - o.cost);
		}
	}
}
