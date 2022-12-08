package Programmers;

import java.util.*;

/**
 * ( 문제 풀이 )
 * 1. 배열을 list에 담아, 가장 큰 사과 점수 순으로 즉, 내림차순으로 정렬해 준다.
 * 2. list 사이즈 보다 크거나 같거나, list 사이즈보다 (현재 인덱스 + 한 상자에 담는 사과의 개수 )가 크다면 while문 탈출
 *    -> 배열 인덱스 범위를 넘는 것은 범위 문제, 
 *    -> 한 상자에 들어가는 사과의 수 보다 작을경우 볼 필요가 없음. 가득 채워서 판매해야 최대 이익을 계산할 수 있기 때문
 */
public class Lv1_과일장수 {
	public static void main(String[] args) {
		int [] score1 = {1, 2, 3, 1, 2, 3, 1};
		System.out.println(solution(3,4,score1)); // 답 : 8
		System.out.println(solution1(3,4,score1)); // 답 : 8
		
		int [] score2 = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};
		System.out.println(solution(4,3,score2)); // 답 : 33
		System.out.println(solution1(4,3,score2)); // 답 : 33
	}

	/**
	 * < 1번째 풀이 방법 >
    k : 사과의 최대 점수
    m : 한 상자에 들어가는 사과의 수 
    score : 사과들의 점수
    **/
	public static int solution(int k, int m, int[] score) {
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < score.length; i++) {
			list.add(score[i]);
		}
		// list를 가장 큰 사과들 점수 순으로 정렬
		Collections.sort(list, Collections.reverseOrder());
		int index = 0;
		while (true) {
			/** 
            - 사과들의 점수를 담은 배열 원소 개수 보다 크거나 같을경우 -> 배열 인덱스를 넘칠 경우 
            - 인덱스에 + m(항 상자에 들어가는 사과의 수) 보다 클 경우 
              ->즉, 한 상자에 들어가는 사과의 수 보다 작을경우 [ 볼 필요가 없음. ]
            ex) [3,3,2,2,1,1,1]   -> 배열 총 길이 7 '실제 맨 마지막 원소 인덱스는 < 6 >'
            (1차)
            [3,3,2,2]  -> index : 4 상태 -> 맨 마지막 원소의 실제 인덱스는 index-1 = 3인 상태
            (2차)
            [1,1,1 ]   -> index : (index + m 을하여 ) [ 8인 상태 ] -> 맨 마지막 원소의 실제 인덱스는 7인 상태 
             -> index + m > list.size()  -> 8 > 7 에 적합하여 while문 탈출
             -> 배열 실제 마지막 인덱스 보다 1 더 큰 상태이기 때문에 한 상자에 들어가는 사과의 수를 다 채울 수 가 없음.
            **/
			if (index >= list.size() || index + m > list.size()) break;
			index += m;
			// 최저 사과 점수 * 한 상자에 담긴 사과의 개수 * 상자의 개수
			answer += list.get(index - 1) * m;
		}
		return answer;
	}
    // 2번째 풀이 방법
	public static int solution1(int k, int m, int[] score) {
		int answer = 0;
		// 현재 배열 정렬을 해준다.
		Arrays.sort(score);
		// (맨 뒤의 인덱스 - 한 상자에 담긴 사과 개수) 가 곧 최저 사과 점수임
		// 한 상자에 들어가는 사과의 개수보다 i 값이 더 크거나 같아야 함. 
		// 그래야 한 상자에 꽉꽉 가득 차게 담아 판매할 수 있기 때문
		for (int i = score.length; i >= m; i -= m) { 
			// 최저 사과점수 * 한 상자에 담긴 사과개수를 곱해서 answer에 누적
			answer += score[i - m] * m;
		}
		return answer;
	}
}