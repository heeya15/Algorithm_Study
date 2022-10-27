import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int sum = 0;
		while (pq.size() > 1) {
			int first = pq.poll();
			int sec = pq.poll();

			sum += first + sec;
			pq.offer(first + sec);
		}

		System.out.println(sum);
	}
}