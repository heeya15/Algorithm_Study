package 그리디;

import java.util.*;
import java.io.*;
/**
 * 나라에서는 < 각 사람들까지의 거리의 합 >이 [ 최소가 되는 위치 ]에 "우체국을 세우기로 결정"
 * 우체국을 세울 위치를 구하는 프로그램을 작성하시오.
 * ( 문제 풀이 )
 * 1. 마을 위치 순으로 오름차순으로 정렬한 뒤, 1번째 마을부터 탐색하며 각 마을에 위치한 사람의 수를 sum 변수에 누적
 * 2. 탐색 중에 더해진 사람의 수가 마을 전체 총 인구수의 절반보다 크거나 같을경우 < 해당 마을 위치를 return >
 *
 */
public class Main_G4_2285_우체국 {
	public static class Post implements Comparable<Post>{
		int town; // 마을
		long person_count; //사람 수 
		public Post(int town, long person_count) {
			this.town = town;
			this.person_count = person_count;
		}
		// 오름차순으로 정렬
		public int compareTo(Post n) {
			return this.town-n.town;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 마을 수 주어짐
		Post [] post = new Post[N];
		long total_people = 0; // 사람수가 최대 10억까지여서 누적하다보면 int 범위를 넘기 때문에 long 타입 
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			post[i] = new Post(A,B);
			total_people += B;
		}
		
		Arrays.sort(post); // 마을 오름차순으로 정렬
		long sum = 0;  // 탐색하며 각 마을의 인구수를 저장하기 위해 사용할 변수
		
		// 중간 값과 가장 근접한 마을을 찾아 출력하기 -> 즉 사람들이 중간 값과 근접하다면 사람들까지의 거리의 합이 최소가 됨
		for(Post n : post) {
			sum += n.person_count;
			if(sum >= Math.ceil(total_people/2.0)) {
				System.out.println(n.town);
				break;
			}
		}
	}
}