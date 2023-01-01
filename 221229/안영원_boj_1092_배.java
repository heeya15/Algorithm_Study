import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> craneList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			craneList.add(Integer.parseInt(st.nextToken()));
		}
		
		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> boxList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			boxList.add(Integer.parseInt(st.nextToken()));
		}
		
		// 정렬
		craneList.sort(Comparator.reverseOrder());
		boxList.sort(Comparator.reverseOrder());

		int minTime = 0;

		// 크레인이 옮길 수 있는 최대 무게보다 큰 박스가 존재한다면 불가능
		if (craneList.get(0) < boxList.get(0)) {
			System.out.println(-1);
			return;
		}
		
		while (!boxList.isEmpty()) {
			minTime += 1;

			// 크레인으로 옮길 수 있는 것 찾기
			for (int i = 0; i < craneList.size(); i++) {
				for (int j = 0; j < boxList.size(); j++) {
					if (craneList.get(i) >= boxList.get(j)) {
						boxList.remove(j);
						break;
					}
				}
			}
		}

		System.out.println(minTime);
	}
}