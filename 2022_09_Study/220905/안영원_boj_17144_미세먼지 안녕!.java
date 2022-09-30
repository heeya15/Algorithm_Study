import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int[][] map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            // 미세먼지 확산
            int[][] cpMap = new int[R][C];

            // 확산된 미세먼지를 cpMap 배열에 계산
            for (int r = 0; r < R; r++) {
                for (int c = 0 ; c < C; c++) {
                    if (map[r][c] == 0) continue; // 미세먼지가 없으면 건너뜀
                    if (map[r][c] == -1) { // 공기청정기가 있다면 새로운 맵에 옮겨줌.
                        cpMap[r][c] = -1;
                        continue;
                    }

                    int cnt = 0;
                    int amount = map[r][c] / 5;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                        if (map[nr][nc] == -1) continue;

                        cpMap[nr][nc] += amount;
                        cnt++;
                    }
                    cpMap[r][c] += map[r][c] - amount * cnt;
                }
            }
            map = cpMap;
            
            // 공기청정기 작동
            for (int i = 0; i < R; i++) {
                if (map[i][0] == -1) {
                    for (int j = i - 1; j > 0; j--) map[j][0] = map[j - 1][0];
                    for (int j = 0; j < C - 1; j++) map[0][j] = map[0][j + 1];
                    for (int j = 0; j < i; j++) map[j][C - 1] = map[j + 1][C - 1];
                    for (int j = C - 1; j > 1; j--) map[i][j] = map[i][j - 1];
                    map[i][1] = 0;

                    i++;
                
                    for (int j = i + 1; j < R - 1; j++) map[j][0] = map[j + 1][0];
                    for (int j = 0; j < C - 1; j++) map[R - 1][j] = map[R - 1][j + 1];
                    for (int j = R - 1; j > i; j--) map[j][C - 1] = map[j - 1][C - 1];
                    for (int j = C - 1; j > 1; j--) map[i][j] = map[i][j - 1];
                    map[i][1] = 0;
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) result += map[i][j];
            }
        }
        System.out.println(result);
    }
}
