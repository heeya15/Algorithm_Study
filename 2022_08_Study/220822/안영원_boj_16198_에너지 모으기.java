import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안영원_boj_16198_에너지 모으기 {
    static int N;
    static int[] marbles;
    static boolean[] visited;
    static int[] numberOrder;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        marbles = new int[N];
        numberOrder = new int[N - 2];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        // 첫 번째와 마지막을 제외한 나머지를 뽑는 순서를 찾기
        find(N - 2, 0);
        System.out.println(max);
    }
    static void find(int end, int cur) {
        if (cur == end) {
            // 해당 순서의 에너지 확인
            max = Math.max(max, checkEnergy());
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numberOrder[cur] = i;
                find(end, cur + 1);
                visited[i] = false;
            }
        }
    }

    static int checkEnergy() {
        int energy = 0;
        int[] copyMarbles = new int[N]; // 에너지 확인할 배열 복제
        for (int i = 0; i < marbles.length; i++) {
            copyMarbles[i] = marbles[i];
        }

        for (int i = 0; i < numberOrder.length; i++) {
            int curPosition = numberOrder[i];

            // 에너지 구슬이 제거된 것이라면 다음 것으로 탐색
            int idx = 1;
            int left = copyMarbles[curPosition - idx];
            while (left == 0) {
                idx++;
                left = copyMarbles[curPosition - idx];
            }
            idx = 1;
            int right = copyMarbles[curPosition + idx];
            while (right == 0) {
                idx++;
                right = copyMarbles[curPosition + idx];
            }

            energy += left * right;
            copyMarbles[curPosition] = 0; // 제거한 에너지 구슬 표시
        }
        return energy;
    }
}