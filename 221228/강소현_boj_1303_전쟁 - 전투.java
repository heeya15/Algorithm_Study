package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* boj 1303번 전쟁 - 전투 */
public class boj_1303 {

    static int N, M, white, blue;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        white = blue = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 'W') {
                    white += Math.pow(Solve(i, j), 2);
                } else if (!visited[i][j] && map[i][j] == 'B') {
                    blue += Math.pow(Solve(i, j), 2);
                }
            }
        }

        System.out.printf("%d %d", white, blue);

    }

    private static int Solve(int y, int x) {
        int count = 1;

        visited[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if (nx < 0 || nx >= N|| ny < 0 || ny >= M) continue;
            if (visited[ny][nx] || map[ny][nx] != map[y][x]) continue;
            
            count += Solve(ny, nx);
        }

        return count;
    }
}
