package 그래프;
import java.io.*;
import java.util.*;
/*
 ( 문제설명 ) 
- 준영이는 매일 서울에서 포천까지 출퇴근한다.
- K개의 도로를 포장하여 서울에서 포천까지 가는 시간을 단축하려 한다.
- N개의 도시가 주어지고 그 사이 도로와 이 도로를 통과할 때 걸리는 시간이 주어졌을 때 
    최소 시간이 걸리도록 하는 < K개의 이하의 도로를 포장 >
- 도로는 이미 있는 도로만 포장할 수 있고, < 포장하게 되면 > 도로를 지나는데 [ 걸리는 시간이 0이 된다. ] 
- 서울은 1번 도시, 포천은 N번 도시라 하고 < 1번에서 N번까지 항상 갈 수 있는 데이터만 > 주어진다.

( 주의할 점 )
- 단순히 모든 도로들 중 "K개의 도로들을 선택"하여 cost를 0으로 만든다면,
    모든 조합의 최대 수는 Combination(50000 , 20) 일 것이다. -> 이는 무조건 시간초과가 날 것.
- 주어진 걸리는 시간이 1,000,000 보다 작거나 같고, 
    도로의 수는 10,000 이므로최악의 경우 Integer의 범위를 넘어가기 때문에 distance 배열의 자료형을 long 형으로 해야한다.

( 문제풀이 )
 1. graph에  양방향 그래프 정보를 입력 받아 셋팅해 준다.
 2. 최단 거리테이블 distance를 2차원 배열로 선언 및 초기화 해준다. -> distance[정점][포장 횟수 ] 로 사용하기 위해서
        즉, 해당 정점에 도로 포장을 하고 간 경우와, 포장을 하지 않고 간 거리를 저장하며 가기 위해서
 3. 최종 정점 N까지 도로 포장을 하지 않은경우와 도로 포장을 한 경우 중 더 빠르게 도로를 통과하는 시간을 min 변수에 저장 및 출력한다.
    
 */
public class Main_G1_1162_도로포장 {
	public static class Node implements Comparable<Node> {
		int index_node, count;
		long distance;
		public Node(int index_node, long distance, int count) {
			this.index_node = index_node;
			this.distance = distance;
			this.count = count;
		}
		// 거리 짧은 순으로 정렬
		public int compareTo(Node n) {
			return Long.compare(this.distance, n.distance);
		}
	}
	static int K;
	static long INF = Long.MAX_VALUE;
	// 최단 거리 테이블 만들기
	static long[][] distance;
	// 각 노드에 연결되어 있는 [ 노드에 대한 정보를 담는 배열 ]
	static ArrayList<Node> [] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());  // 도시의 수 (정점)
		int M = Integer.parseInt(st.nextToken());  // 도로의 수 (간선)
		K = Integer.parseInt(st.nextToken()); 	   // 포장할 도로의 수
			
		distance = new long[N+1][K+1]; // [정점][포장 횟수 ]

		graph = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			Arrays.fill(distance[i], INF);
		}
		// 두 번째 줄 부터 도로 시작점, 끝점, [ 이 도로를 지나는데 필요한 시간 ] 입력. 
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 시작
			int b = Integer.parseInt(st.nextToken()); // 끝점
			int c = Integer.parseInt(st.nextToken()); // 필요한 시간
			
			// 문제에서 도로들은 양방향 도로라고 주어짐
			// 아래 부분이 a번 노드에서 b번 노드로 가는 거리 비용이 c라는 의미, 그리고 도로 포장 횟수는 0으로 넣어줌 
			graph[a].add(new Node(b, c, 0)); // 정점, 거리, 도로 포장 횟수를 매개변수로 넣어줌.
			graph[b].add(new Node(a, c, 0));
		}
		
		dijkstra(1);
	
		long min = INF;
		// 최종 노드까지 도로를 포장 안 한것과 도로를 포장한 것 중 최소 시간을 min 변수에 저장. 
	    for(long a : distance[N]) min = Math.min(min, a);
	    // 출력
	    System.out.println(min);
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0,0));
		distance[start][0] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.index_node;  // 현재 거쳐갈 노드
			long dist = node.distance;   // 현재 노드까지의 최소 거리
			int cnt = node.count;       // 도로 포장 횟수
			// 최단거리 테이블에 해당 거쳐갈 노드의 거리 값이 [ 이미 처리된 적이 있는 노드 ]라면 무시.  
			if(distance[now][cnt] < dist) continue;
			for (int i = 0; i < graph[now].size(); i++) {
				Node next = graph[now].get(i);

                //도로를 포장하지 않았을 경우 => next 노드의 거리(distance) 값 더한다.
				if(distance[next.index_node][cnt] > distance[now][cnt]+ next.distance) {
					distance[next.index_node][cnt] = distance[now][cnt]+ next.distance;
					pq.offer(new Node(next.index_node, distance[next.index_node][cnt], cnt ));
				}
				//도로를 포장했을경우 => next 노드의 (distance) 값을 더하지 않음
				if(K > cnt &&  distance[next.index_node][cnt+1] > distance[now][cnt]) {
					distance[next.index_node][cnt+1] = distance[now][cnt];
					pq.offer(new Node(next.index_node, distance[next.index_node][cnt+1], cnt+1 ));
				}
			}
		}
	}
}
