import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안영원_boj_16174_점프왕_쩰리 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static boolean flag;

    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        flag = false;
        jump();

        if (flag) System.out.println("HaruHaru");
        else System.out.println("Hing");
    }

    static void jump() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (map[node.r][node.c] == -1) {
                flag = true;
                break;
            }

            for (int d = 0; d < 2; d++) {
                int moveCnt = map[node.r][node.c];
                if (moveCnt == 0) continue;

                int nr = node.r + dr[d] * moveCnt;
                int nc = node.c + dc[d] * moveCnt;

                if (nr < N && nc < N && nr >= 0 && nc >= 0 && !visited[nr][nc]) {
                    // System.out.println(nr + " " + nc);
                    q.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static class Node {
        int r;
        int c;

        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

