package 이분탐색;

import java.util.*;
import java.io.*;
/**
 * 이 배열을 < M개 이하의 구간 >으로 나누어서 < 구간의 점수의 최댓값을 최소>로 하려고 한다.
 * 구간은 다음 조건을 만족해야 한다.
 * 1. 하나의 구간은 하나 이상의 연속된 수들로 이루어져 있다.
 * 2. 배열의 각 수는 < 모두 하나의 구간에 포함 >되어 있어야 한다.
 * 구간의 점수란 < 구간에 속한 수 >의 [ 최댓값 ]과 [ 최솟값의] "차이"이다.
 * 예를 들어, 배열이 [1, 5, 4, 6, 2, 1, 3, 7] 이고, M = 3인 경우가 있다.
 * 이때, [1, 5], [4, 6, 2], [1, 3, 7]로 구간을 나누면 각 구간의 점수는 4, 4, 6점이 된다. 
 * 이때, 최댓값은 6점이다.
 * 만약, [1, 5, 4], [6, 2, 1], [3, 7]로 구간을 나누었다면, 각 구간의 점수는 4, 5, 4점이 되고, 
 * 이때 최댓값은 5점이 된다.
 * 두 경우 중에서 최댓값이 최소인 것은 5점인 것이고, 5점보다 최댓값을 작게 만드는 방법은 없다.
 * 구간의 점수의 [ 최댓값의 ] < 최솟값 >을 구하는 프로그램을 작성하시오.
 * 
 * ( 문제 풀이 )
 * 1. left = 0, right = 배열에서 가장 큰 원소값으로 저장
 * 2. 이진 탐색을 활용하여, 배열 전체를 탐색하면서 < 최댓값 최솟값을 구하고, 갱신 해서 구간의 수를 구한다.
 * 3. 구간의 수가 M개 구간 보다 작거나 같다면 result 값을 갱신 및 오른쪽 기준을 mid -1 로 해준다
 * 4. 구간의 수가 M개 구간 보다 크다면 왼쪽 기준을 mid + 1로 해준다.
 * 5. left가 right 보다 크다면 while을 탈출하여 result 값을 출력해 준다.
 */
public class Main_G4_13397_구간나누기2 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // M개 이하의 구간으로 나눠야 하는 범위
		arr = new int[N];
		
		// 왼쪽 : 최솟값, 오른쪽 : 배열의 최댓값으로 둔다. 
		int left = 0;
		int right = -1;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		int result = 0;
		while(left <= right) {
			int mid = (left + right) / 2; // 중간 값
			if(SectionCnt(mid) <= M) {
				result = mid;
				right = mid -1; // M개이하의 구간이면 오른쪽을 (중간값 -1)
			}
			else left = mid+1;	// M개보다 크다면 왼쪽을 (중간 값 + 1)로 한다.
		}
		System.out.println(result);
		
	}
	public static int SectionCnt(int mid) {
		int cnt = 1;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		// 배열 원소 전체를 탐색하면서 최댓값 최솟값을 구하고, 갱신 하며 해결.
		for(int i=0; i < N; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			// 최댓값 - 최솟값 => 구간의 점수의 최댓값
			// 즉, 구간점수의 최댓값이 "MID 보다 크다면 
			if(max - min > mid) {
				cnt++; // 구간 개수 증가
				/** 
				 max와 min은 현재 인덱스 배열의 원소로 초기화. 
				 이유 : 초기화 하지 않으면 앞에서 나온 최댓값이 쭉 최댓값이여서 구간의 최댓값이 고정이 될 수 있다
				 ex) 1 100 99 2 3 이고 mid가 50인 경우
				 (100 -1) 인경우 들어온다. 하지만 "최댓값"이 100이여서 모든 원소보다 계속 크게 됨.
				  현재 인덱스 배열의 원소로 갱신을 다시 해주면 (100-1),(100 -2)와 같이 새로운 구간의 수를 계산 가능
				**/
				max = arr[i]; 
				min = arr[i];
			}
		}
		return cnt;
	}
}