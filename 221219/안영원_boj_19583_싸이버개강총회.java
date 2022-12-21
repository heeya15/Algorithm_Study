import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken().replaceAll(":", ""));
		int e = Integer.parseInt(st.nextToken().replaceAll(":", ""));
		int q = Integer.parseInt(st.nextToken().replaceAll(":", ""));

		// 개강총회 시작 전 입장여부 확인
		Set<String> nameSet = new HashSet<>();
		int answer = 0;

		String temp;
		while ((temp = br.readLine()) != null) {
			st = new StringTokenizer(temp);
			int time = Integer.parseInt(st.nextToken().replaceAll(":", ""));
			String name = st.nextToken();

			if (time <= s) {
				nameSet.add(name);
			}

			if (time >= e && time <= q) {
				if (nameSet.contains(name)) {
					answer += 1;
					nameSet.remove(name);
				}
			}
		}
		System.out.println(answer);
	}
}