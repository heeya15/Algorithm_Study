import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안영원_boj_12761_돌다리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<DongGue> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        q.offer(new DongGue(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            DongGue dongGue = q.poll();

            if (dongGue.position == M) {
                System.out.println(dongGue.count);
                return;
            }

            int[] move = new int[8];

            move[0] = dongGue.position + 1;
            move[1] = dongGue.position - 1;
            move[2] = dongGue.position + A;
            move[3] = dongGue.position - A;
            move[4] = dongGue.position + B;
            move[5] = dongGue.position - B;
            move[6] = dongGue.position * A;
            move[7] = dongGue.position * B;

            for (int i = 0; i < 8; i++) {
                if (move[i] >= 0 && move[i] <= 100000) {
                    if (!visited[move[i]]) {
                        q.offer(new DongGue(move[i], dongGue.count + 1));
                        visited[move[i]] = true;
                    }
                }
            }
        }
    }

    static class DongGue {
        int position;
        int count;

        DongGue(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}