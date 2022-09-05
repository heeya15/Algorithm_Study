import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 안영원_boj_17144_미세먼지_안녕 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        ArrayList<Dust> list = new ArrayList<>();
        int[][] map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != -1 && map[i][j] != 0) list.add(new Dust(i, j));
            }
        }

        for (int t = 0; t < T; t++) {
            // 미세먼지 확산
            int[][] cpMap = new int[R][C];

            for (Dust dust : list) {
                int amount = map[dust.r][dust.c] / 5;
                cpMap[dust.r][dust.c] = map[dust.r][dust.c];

                for (int d = 0; d < 4; d++) {
                    int nr = dust.r + dr[d];
                    int nc = dust.c + dc[d];

                    if (nr >= R || nr < 0 || nc >= C || nc < 0 || map[dust.r][dust.c] == -1) continue;
                    cpMap[nr][nc] = amount;
                    cpMap[dust.r][dust.c] -= amount;
                }
            }
            
            // 공기청정기 작동
            boolean isFirst = true;
            for (int i = 0; i < R; i++) {
                if (isFirst && map[i][0] == -1) {
                    cpMap[i][0] = -1;

                    for (int j = i - 1; j > 0; j--) cpMap[j][0] = cpMap[j - 1][0];
                    for (int j = 0; j < C - 1; j++) cpMap[0][j] = cpMap[0][j + 1];
                    for (int j = 0; j < i; j++) cpMap[j][C - 1] = cpMap[j + 1][C - 1];
                    for (int j = C - 1; j > 1; j--) map[i][j] = map[i][j - 1];
                    cpMap[i][1] = 0;

                    isFirst = false;
                } else if (!isFirst && map[i][0] == -1) {
                    cpMap[i][0] = -1;

                    for (int j = i + 1; j < R - 1; j++) cpMap[j][0] = cpMap[j + 1][0];
                    for (int j = 0; j < C - 1; j++) cpMap[R - 1][j] = cpMap[R - 1][j + 1];
                    for (int j = R - 1; j > i; j--) cpMap[j][C - 1] = cpMap[j - 1][C - 1];
                    for (int j = C - 1; j > 1; j--) cpMap[i][j] = cpMap[i][j - 1];
                    cpMap[i][1] = 0;
                }
            }

            // 맵 복사
            list.clear();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] = cpMap[i][j];
                    if (map[i][j] != -1 && map[i][j] != 0) list.add(new Dust(i, j));
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) result += map[i][j];
            }
        }
        System.out.println(result);
    }
    static class Dust {
        int r;
        int c;

        Dust (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
