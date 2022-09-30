package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* boj 1445번 일요일 아침의 데이트 */
public class boj_1445 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                // 데이트 시작 장소
                if(map[i][j] == 'S') {
                    visited[i][j] = true;
                    pq.offer(new Node(i, j, 0, 0));
                }
            }
        }
        solve();
    }

    static void solve() {

        while(!pq.isEmpty()) {
            Node n = pq.poll();

            if(map[n.x][n.y] == 'F') {
                System.out.println(n.g + " " + n.gc);
                return;
            }

            for(int d = 0; d < 4; d++) {
                int nx = n.x + dx[d];
                int ny = n.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

                if(check(n.x, n.y)) n.gc++; // 쓰레기 옆을 지나가는 경우 체크
                else if(map[nx][ny] == 'g') n.g++; // 쓰레기가 있을 경우 체크

                visited[nx][ny] = true;
                pq.offer(new Node(nx, ny, n.g, n.gc));
            }
        }
    }

    static boolean check(int x, int y) {
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            // 쓰레기 옆을 지나가는 경우
            if(map[x][y] == '.' && map[nx][ny] == 'g') return true;
        }
        return false;
    }

    static class Node implements Comparable<Node> {
        int x, y, g, gc; // 좌표, 쓰레기 수, 쓰레기 근처 수

        public Node(int x, int y, int g, int gc) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.gc = gc;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.g, o.g);
        }
    }
}
