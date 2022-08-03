import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2615 {
    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 1, 0, -1};
    static int[][] map = new int[19][19];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] != 0) {
                    for (int d = 0; d < 4; d++) {
                        int cnt = find(i, j, d);
                        if (cnt == 5) {
                            System.out.println(map[i][j]);
                            System.out.println((i + 1) + " " + (j + 1));
                        }
                    }
                }
            }
        }
    }

    static int find(int r, int c, int dir) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r, c, 1));
        int max = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            max = Math.max(node.cnt, max);

            int nr = node.r + dr[dir];
            int nc = node.c + dc[dir];

            if (nr < 0 || nc < 0 || nr >= 19 || nc >= 19 || map[nr][nc] != map[node.r][node.c]) continue;
            q.add(new Node(nr, nc, node.cnt + 1));
        }

        return max;
    }

    static class Node {
        int r;
        int c;
        int cnt;
        
        Node (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}

