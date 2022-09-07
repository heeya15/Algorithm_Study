import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안영원_boj_13397_구간_나누기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        int right = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, array[i]);
        }

        int left = 0;
        // 이분 탐색 mid는 구간의 최댓값
        while (left <= right) {
            int mid = (left + right) / 2;
            if (cal(array, mid) <= M) right = mid - 1; // 같을 때 더 작은 값도 가능할 수 있으므로 추가적으로 탐색
            else left = mid + 1;
        }
        System.out.println(left);
    }

    static int cal(int[] array, int mid) {
        int cnt = 1; // 나눠지는 구간의 수
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);

            if (max - min > mid) { // 현재까지 구간 값이 mid보다 크니까 이 앞까지를 구간으로 나눔
                // 현재 위치부터 다시 구간 찾기
                min = array[i];
                max = array[i];
                cnt++;
            }
        }

        return cnt;
    }
}