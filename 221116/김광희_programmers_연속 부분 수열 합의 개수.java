package Programmers;

import java.util.*;
/**
 * 문제에서 중복되는 값을 제거 -> set 을 이용해야겠다 생각
     따라서, 길이가 1개부터 최대 배열의 길이 만큼을 선택하여 더한 값을 중복을 제거한 값의 수를 반환
 * ( 문제 풀이 )
 * 1. 길이가 1 부터, 배열 원소의 길이 만큼 연속 부분 수열의 합을 중복된 값을 알아서 제거하는 HashSet에 저장.
 * 2. 해당 set에 저장된 부분 수열의 합 길이가 -> 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수가 됨
 */
public class Lv2_연속부분수열합의개수 {
	public static void main(String[] args) {
		int[] elements = { 7,9,1,1,4 };
		System.out.println(solution(elements)); // 답 : 18
	}

	public static int solution(int[] elements) {
		HashSet<Integer> set = new HashSet<>();
		int size = elements.length;
		// 길이가 1부터, 원소 길이 까지 연속되는 부분 수열의 합을 처리하기 위한 for 문
		for (int length = 1; length <= size; length++) {
			// 처음 원소부터 마지막 원소 까지
			for (int i = 0; i < size; i++) {
				int sum = 0;
				// 해당 길이가 1 부터, 원소 길이 사이즈 까지 < 연속된 수열의 합을 구하기 위한 메인 for문 >
				for (int j = 0; j < length; j++) {
					// 원형에 따른 인덱스 1씩 증가 시킴
					int index = (i + j) % size;
					sum += elements[index]; // 해당 인덱스의 원소를 부분 수열의 합에 누적
				}
				set.add(sum); // 최종적으로 누적된 연속되는 부분 수열의 합을 set에 추가.
			}
		}
		return set.size();
	}
}