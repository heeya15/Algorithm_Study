import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class 안영원_boj_20208_진우의_민트초코우유 {
    static int N, H;
    static Node home;
    static int[][] town;
    static int max = Integer.MIN_VALUE;

    static ArrayList<Node> milkList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 초기체력
        H = Integer.parseInt(st.nextToken()); // 민트초코우유를 마실 때마다 증가하는 체력

        town = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                town[i][j] = Integer.parseInt(st.nextToken());
                if (town[i][j] == 1) {
                    home = new Node(i, j);
                    town[i][j] = 0;
                } else if (town[i][j] == 2) milkList.add(new Node(i, j));
            }
        }

        visited = new boolean[milkList.size()];
        move(home, M, 0, false);
        System.out.println(max);
    }
    static void move(Node curPosition, int hp, int mintCnt, boolean goHome) {
        // 집에 온 친구면 획득한 우유 갯수 확인
        if (goHome) {
            max = Math.max(max, mintCnt);
            return;
        }

        // 남은 우유들 중 갈 수 있는 곳 방문
        for (int i = 0; i < milkList.size(); i++) {
            if (visited[i]) continue;
            int dist = Math.abs(curPosition.r - milkList.get(i).r) + Math.abs(curPosition.c - milkList.get(i).c);
            if (dist > hp) continue;

            visited[i] = true;
            move(milkList.get(i), hp - dist + H, mintCnt + 1, false);
            visited[i] = false;
        }

        // 현재 집에 갈 수 있는지 확인
        int distHome = Math.abs(curPosition.r - home.r) + Math.abs(curPosition.c - home.c);
        if (distHome <= hp) { // 갈 수 있으면 집으로 보냄
            max = Math.max(max, mintCnt);
            move(home, hp - distHome, mintCnt, true);
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

