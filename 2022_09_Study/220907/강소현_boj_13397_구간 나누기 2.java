package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* boj 13397번 구간 나누기2 */
public class boj_13397 {

	static int N, M, result = 0;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		int start = 0, end = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			end = Math.max(end, arr[i]);
		}
		
		System.out.print(binarySearch(start, end));
	}
	
	static private int binarySearch(int start, int end) {
		
		int result = 0;

		while(start <= end) {
			
			int mid = (start + end) / 2; // 중간값으로 비교하며 갱신
			
			int min = arr[0], max = arr[0];
			
			int section = 1; // 구간 개수
			
			for(int i = 1; i < N; i++) {
				
				if(max < arr[i]) max = arr[i];
				if(min > arr[i]) min = arr[i];
				
				int diff = max - min; // 최댓값 - 최솟값
				
				// 최댓값 - 최솟값 >= 중간값이면 
				if(diff > mid) {
					// 구간을 나누어야 되므로 구간 카운트
					section++;
					
					// 최댓값, 최솟값 갱신
					max = arr[i]; 
					min = arr[i];
				} 
			}
			
			// 구간 개수 <= M 이면
			if(section <= M) {
				result = mid;
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		return result;
	}
}
