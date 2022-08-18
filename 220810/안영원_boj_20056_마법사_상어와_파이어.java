import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 안영원_boj_20056_마법사_상어와_파이어볼 {
    static int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
    
    static int N;
    static ArrayList<Fireball> map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new Fireball(m, d, s));
        }

        for (int i = 0; i < K; i++) move();

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball fb : map[i][j]) sum += fb.m;
            }
        }
        System.out.println(sum);
    }

    static void move() {
        // 이동 후의 파이어볼 배열
        ArrayList<Fireball> next[][] = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                next[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() >= 1) {
                    for (Fireball fb : map[i][j]) {
                        int distance = fb.s % N;
                        int r = i + dr[fb.d] * distance;
                        int c = j + dc[fb.d] * distance;
                        if (r >= N) r -= N;
                        else if (r < 0) r += N;
                        if (c >= N) c -= N;
                        else if (c < 0) c += N;
                        next[r][c].add(new Fireball(fb.m, fb.d, fb.s));
                    }
                }
            }
        }
        map = next;
        duplicate();
    }

    static void duplicate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() >= 2) {
                    int mSum = 0;
                    int sSum = 0;
                    boolean even = true, odd = true;
                    
                    for(Fireball fb : map[i][j]) {
                        mSum += fb.m;
                        sSum += fb.s;
                        if (fb.d % 2 == 0) odd = false;
                        else even = false;
                    }
                    int m = mSum / 5;
                    int s = sSum / map[i][j].size();
                    map[i][j].clear();
                    
                    // 질량이 0보다 크면 방향에 맞게 분리
                    if (m > 0) {
                        for (int d = 0; d < 4; d++) {
                            if (odd || even) map[i][j].add(new Fireball(m, 0 + 2 * d, s));
                            else map[i][j].add(new Fireball(m, 1 + 2 * d, s));
                        }
                    }
                }
            }
        }
    }

    static class Fireball {
        int m;
        int d;
        int s;

        Fireball (int m, int d, int s) {
            this.m = m;
            this.d = d;
            this.s = s;
        }
    }
}
