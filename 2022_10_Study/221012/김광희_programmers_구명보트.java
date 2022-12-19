package Programmers;

import java.util.*;

/**
 * ( 문제풀이 )
 * 1. 사람들의 몸무게를 담은 배열 people을 오름차순으로 정렬
 * 2. 가장 가벼운 사람과 가장 무거운 사람의 합이 무게제한"limit" 보다 작거나 같다면
 *    가장 가벼운 사람의 인덱스 위치 증가, 가장 무거운 사람의 인덱스 위치 당겨줌. 그리고 구명 보트의 개수 증가.
 * 3. 2명이서 보트를 탈 수 없는 경우는 구명 보트의 개수만 증가 -> 혼자 보트를 타야하는 경우
 * 
 * ( 문제 상세 과정 풀이 )
 * 50 50 70 80
 * 1단계 50 80 -> 무게 제한 100보다 합이 크다 -> 구명 보트 개수만 증가 --> answer : 1
 * 2단계 50 70 -> 위와 마찬가지임 -> 구명 보트 개수만 증가 --> answer : 2
 * 3단계 50 50 -> 무게제한보다 작거나 같기 때문에 < 최소 인덱스 증가 > 및 < 최대 인덱스 감소 > 및 구명 보트의 개수 증가 --> answer : 3
 * 여기서 max_index = 0, min_index = 1 이기 때문에 for문 조건 max_index >= min_index 에 걸리지 않기 때문에 for문에서 나옴
 * 최소 인덱스가, 최대 인덱스 보다 크기 때문에 조건에서 true가 아닌 false 상태이기 때문
 */
public class Lv2_구명보트 {

	public static void main(String[] args) throws Exception {
		int [] people = {70, 50, 80, 50};
		System.out.println(solution(people, 100)); // 답 : 3
	}
	public static int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		int min_index = 0;
		for (int max_index = people.length - 1; max_index >= min_index; max_index--) {
			// 가장 가벼운 사람 + 가장 무거운 사람의 합이 "무게제한(limit)" 보다 작거나 같다면
			if (people[min_index] + people[max_index] <= limit) {
				min_index++; // 가장 가벼운 사람의 위치 증가, 가장 무거운 사람의 위치는 for문을 통해 당겨짐
				answer++;    // 구명 보트의 개수 증가
			} else answer++; // 혼자 보트 타야하는 경우 -> 구명 보트의 개수만 증가.
		}
		return answer;
	}
}
