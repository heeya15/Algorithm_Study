import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 안영원_boj_15683_감시 {
    static int N, M;
    static int[][] map;
    static int[] dir;
    static ArrayList<Node> cctvList;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvList = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp != 00 && temp != 6) {
                    cctvList.add(new Node(i, j, temp));
                } else map[i][j] = temp;
            }
        }

        // CCTV의 개수만큼 가능한 조건을 모두 검색하기
        int cnt = cctvList.size();
        dir = new int[cnt];
        find(0, cnt);
        System.out.println(min);
    }

    static void find(int cur, int end) {
        if (cur == end) {
            // 사각 지대의 크기 계산하기
            min = Math.min(min, cal());

            return;
        }

        // 현재 CCTV를 4방향으로 돌려서 모든 경우의 수 찾기
        for (int i = 0; i < 4; i++) {
            dir[cur] = i;
            find(cur + 1, end);
        }
    }

    static int cal() {
        // dir 에 따른 방향
        // 0 오른쪽     왼오        위오        왼위오
        // 1 아래       위아래      오아래      위오아래
        // 2 왼쪽       왼오        왼아래      오아래왼
        // 3 위쪽       위아래      위왼        아래왼위

        boolean[][] see = new boolean[N][M];
        for (int i = 0; i < cctvList.size(); i++) {
            if (cctvList.get(i).type == 1) {
                if (dir[i] == 0) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 1) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                } else if (dir[i] == 2) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 4) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                }
            } else if (cctvList.get(i).type == 2) {
                if (dir[i] == 0 || dir[i] == 2) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                }
            } else if (cctvList.get(i).type == 3) {
                if (dir[i] == 0) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 1) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 2) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                } else if (dir[i] == 3) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                }
            } else if (cctvList.get(i).type == 4) {
                if (dir[i] == 0) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 1) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                    
                    nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                } else if (dir[i] == 2) {
                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc++;
                        if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }
                } else if (dir[i] == 3) {
                    int nr = cctvList.get(i).r;
                    while (true) {
                        nr++;
                        if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }

                    int nc = cctvList.get(i).c;
                    while (true) {
                        nc--;
                        if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                        see[cctvList.get(i).r][nc] = true;
                    }

                    nr = cctvList.get(i).r;
                    while (true) {
                        nr--;
                        if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                        see[nr][cctvList.get(i).c] = true;
                    }
                }
            } else {
                int nr = cctvList.get(i).r;
                while (true) {
                    nr++;
                    if (nr >= N || map[nr][cctvList.get(i).c] == 6) break;
                    see[nr][cctvList.get(i).c] = true;
                }

                int nc = cctvList.get(i).c;
                while (true) {
                    nc--;
                    if (nc < 0 || map[cctvList.get(i).r][nc] == 6) break;
                    see[cctvList.get(i).r][nc] = true;
                }

                nr = cctvList.get(i).r;
                while (true) {
                    nr--;
                    if (nr < 0 || map[nr][cctvList.get(i).c] == 6) break;
                    see[nr][cctvList.get(i).c] = true;
                }

                nc = cctvList.get(i).c;
                while (true) {
                    nc++;
                    if (nc >= M || map[cctvList.get(i).r][nc] == 6) break;
                    see[cctvList.get(i).r][nc] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 6) continue;
                if (!see[i][j]) cnt++;
            }
        }

        return cnt;
    }

    static class Node {
        int r;
        int c;
        int type;

        Node (int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }
}
