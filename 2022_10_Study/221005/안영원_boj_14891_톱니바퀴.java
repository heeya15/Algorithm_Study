import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// init
		ArrayList<Integer> gearList[] = new ArrayList[5];
		for (int i = 1; i <= 4; i++) {
			gearList[i] = new ArrayList<>();
			String temp = br.readLine();
			for (int j = 0; j < 8; j++) {
				gearList[i].add(temp.charAt(j) - '0');
			}
		}

		// 현재 상태를 기준으로 회전여부를 모두 결정한 다음 회전시킴
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			int[] rotate = new int[5]; // 회전 여부 기록
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotate[num] = dir;

			// 최대 3번까지 확인해야함(총 4개이므로)
			boolean left = true;
			boolean right = true;
			for (int j = 0; j < 3; j++) {
				// 왼쪽 확인
				if (left) {
					if (num - (j + 1) > 0) {
						if (gearList[num - (j + 1)].get(2) == gearList[num - j].get(6)) {
							left = false; // 더 이상 움직이지 않음
						} else {
							rotate[num - (j + 1)] = -1 * rotate[num - j];
						}
					}
				}

				if (right) {
					if (num + j + 1 < 5) {
						if (gearList[num + j].get(2) == gearList[num + (j + 1)].get(6)) {
							right = false; // 더 이상 움직이지 않음
						} else {
							rotate[num + (j + 1)] = -1 * rotate[num + j];
						}
					}
				}
			}

			// 기록에 맞게 회전시켜줌
			for (int j = 1; j <= 4; j++) {
				if (rotate[j] == 1) {
					int end = gearList[j].get(7);
					gearList[j].remove(7);

					gearList[j].add(0, end);
				}

				if (rotate[j] == -1) {
					int start = gearList[j].get(0);
					gearList[j].remove(0);

					gearList[j].add(start);
				}
			}
		}

		int sum = 0;
		for (int i = 1; i <= 4; i++) {
			if (gearList[i].get(0) == 1) {
				sum += Math.pow(2, i - 1);
			}
		}
		System.out.println(sum);
	}
}