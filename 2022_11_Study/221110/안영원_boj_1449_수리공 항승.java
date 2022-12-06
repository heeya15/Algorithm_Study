import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] array = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		// 순서대로 탐색하기 위해서 정렬
		Arrays.sort(array);

		int cnt = 1;
		// 현재 물이 새는 곳 0.5 전부터 테이프 길이만큼 붙였을 때
		// 테이프가 끝나는 지점의 위치
		double tapeEnd = array[0] + L - 0.5;
		
		// 나머지 구멍들을 비교하며 필요한 테이프 개수 찾기
		for (int i = 1; i < n; i++) {
			// 테이프가 끝나는 지점이 현재 물 새는 곳을 막지 못한다면
			if (tapeEnd < array[i] + 0.5) {
				// 테이프를 하나 더 소모하고 테이프 끝나는 지점을 바꿔줌
				cnt++;
				tapeEnd = array[i] + L - 0.5;
			}
		}

		System.out.println(cnt);
	}
}