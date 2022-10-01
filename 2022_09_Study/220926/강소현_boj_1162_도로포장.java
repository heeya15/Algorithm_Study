import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* boj 1162번 도로포장 */
public class boj_1162 {

    static int N, M, K;
    static final long INF = Long.MAX_VALUE; // 도시의 수 * 도로의 수 * 포장할 도로의 수

    static ArrayList<Node>[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 수
        M = Integer.parseInt(st.nextToken()); // 도로의 수
        K = Integer.parseInt(st.nextToken()); // 포장할 도로의 수

        list = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 도로들은 양방향 도로
            list[to].add(new Node(from, cost, 0));
            list[from].add(new Node(to, cost, 0));
        }

        System.out.println(solve());
    }

    private static long solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        long[][] graph = new long[N + 1][K + 1]; // [도시][포장 횟수]

        for(int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        graph[1][0] = 0;

        pq.add(new Node(1, 0, 0));

        while(!pq.isEmpty()) {
            Node n = pq.poll();

            // 현재 도시 최소 비용 < 현재 최소 비용
            if(graph[n.to][n.road] < n.cost) continue;

            for(Node next : list[n.to]) {

                // 도로를 포장하지 않고 탐색
                if(graph[next.to][n.road] > graph[n.to][n.road] + next.cost) {
                    graph[next.to][n.road] = graph[n.to][n.road] + next.cost;
                    pq.offer(new Node(next.to, graph[next.to][n.road], n.road));
                }

                // 도로를 포장하면서 탐색
                // 도로 포장 할 수 있는 횟수가 K개 이하일 때
                if(n.road + 1 <= K && graph[next.to][n.road + 1] > graph[n.to][n.road]) {
                    graph[next.to][n.road + 1] = graph[n.to][n.road];
                    pq.offer(new Node(next.to, graph[next.to][n.road + 1], n.road + 1));
                }

            }
        }

        return Arrays.stream(graph[N]).min().getAsLong();
    }

    static class Node implements Comparable<Node> {
        int to, road; // 도달점, 포장 도로 횟수
        long cost; // 비용

        public Node(int to, long cost, int road) {
            this.to = to;
            this.cost = cost;
            this.road = road;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
