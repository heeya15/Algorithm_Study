package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* boj 14503번 로봇 청소기 */
public class boj_14503 {

    static int N, M, r, c, d;
    static int[][] robot;

    // 북 동 남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        robot = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                robot[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        solve(r, c, d);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(robot[i][j] == 2) {
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }
    private static void solve(int r, int c, int d) {

        robot[r][c] = 2; // (r, c)는 청소했음

        // 0 : 북쪽
        // 1 : 동쪽
        // 2 : 남쪽
        // 3 : 서쪽
        int dir = d;

        for(int i = 0; i < 4; i++) {
            // 3 0 1 2
            dir = (dir + 3) % 4; // 현재 방향 기준으로 왼쪽 방향 탐색

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if(robot[nr][nc] == 0) {
                solve(nr, nc, dir);
                return;
            }
        }

        // 왼쪽 방향에 청소할 공간이 없으면 방향 전환
        int sr = r - dr[d];
        int sc = c - dc[d];

        // 방향 전환을 했을 때 범위를 넘거나 벽이 있으면 종료
        if(sr < 0 || sr >= N || sc < 0 || sc >= M || robot[sr][sc] == 1) return;

        solve(sr, sc, d);
    }
}
