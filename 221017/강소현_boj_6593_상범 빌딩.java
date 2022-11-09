package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* boj 6593번 상범 빌딩 */
public class boj_6593 {

    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;

    static Queue<Node> queue = new LinkedList<>();

    // 동, 서, 남, 북, 상, 하
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {1, -1, 0, 0, 0, 0};
    static int[] dc = {0, 0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken()); // 상범 빌딩의 층 수
            R = Integer.parseInt(st.nextToken()); // 한 층의 행
            C = Integer.parseInt(st.nextToken()); // 한 층희 열

            // 0 0 0 입력 시, 종료
            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {

                    map[l][r] = br.readLine().toCharArray();

                    for(int c = 0; c < C; c++) {
                        if(map[l][r][c] == 'S') {
                            visited[l][r][c] = true;
                            queue.offer(new Node(l, r, c, 0));
                        }
                    }
                }
                br.readLine(); // 빈 라인 제거
            }

            System.out.println(solve());
        }
    }

    private static String solve() {
        int min = Integer.MAX_VALUE;
        String result = "Trapped!";

        while(!queue.isEmpty()) {
            Node n = queue.poll();

            if(map[n.l][n.r][n.c] == 'E') {
                min = Math.min(min, n.time);
                result = String.format("Escaped in %d minute(s).", min);
            }

            for(int d = 0; d < 6; d++) {
                int nl = n.l + dl[d];
                int nr = n.r + dr[d];
                int nc = n.c + dc[d];

                if(nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if(visited[nl][nr][nc] || map[nl][nr][nc] == '#') continue;

                visited[nl][nr][nc] = true;
                queue.offer(new Node(nl, nr, nc, n.time + 1));
            }
        }
        return result;
    }

    private static class Node {
        int l, r, c, time;

        public Node(int l, int r, int c, int time) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
