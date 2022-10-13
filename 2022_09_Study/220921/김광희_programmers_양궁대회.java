package Programmers;

import java.util.*;

/**
< 어피치가 화살 n발을 다 쏜 후 >에 라이언이 화살 n발을 쏩니다.
- k점을 어피치가 a발을 ,  라이언이 b발을  맞혔을 경우 더 많은 화살을 k점에 맞힌 선수가 k점을 가져감
- a = b일 경우는 어피치가 k점을 가져갑니다.
- k점을 여러 발 맞혀도 k점 보다 많은 점수를 가져가는 게 아니고 < k점만 가져가는 것을 유의 >
- 한 a = b = 0 인 경우, 
  즉, 라이언과 어피치 모두 < k점에 단 하나의 화살도 맞히지 못한 경우 >는 어느 누구도 k점을 가져가지 않습니다.

- < 최종 점수가 더 높은 선수 >를 "우승자"로 결정
- 최종 점수가 "같을 경우" < 어피치를 우승자로 결정 >
- 현재 상황은 어피치가 화살 n발을 다 쏜 후이고 [ 라이언이 화살을 쏠 차례 ]입니다.
- "라이언"은 어피치를 < 가장 큰 점수 차이로 이기기 위해 > 
  n발의 화살을 < 어떤 과녁 점수에 맞혀야 하는지를 구하려고 > 합니다.
  
( 결과 )
- [ 라이언이 가장 큰 점수 차이로 우승 ]하기 위해 
  "n발의 화살"을 [ 어떤 과녁 점수에 맞혀야 하는지를 ] 10점부터 0점까지 < 순서대로 정수 배열에 담아 return 하도록 > 
- 만약, 라이언이 우승할 수 없는 경우(무조건 "지거나 비기는 경우")는 [-1]을 return

( 문제 풀이 )
1. 중복 조합을 활용하여 < 라이언이 쏜 과녁 점수 저장 >
2. 구한 점수를 활용하여 어피치 정보 처럼 < 라이언의 정보도 > 동일하게 해당 점수에 맞춘 개수형태로 만들어준다.
3. 라이언 정보와, 어피치 정보가 둘다 과녁점수를 < 0개 맞춘 원소는 무시. > 
4. 라이언이 이긴경우( 라이언이 과녁 점수를 맞춘 갯수가 더 많다면 ) k점 즉 해당 socre 가져감
     반대로 어피치가 이긴 경우 어피치가 해당 score 가져감
5. 라이언의과 어피치가 같을경우 어피치가 해당 score 가져간다. -> 문제에 기재 되어 있음
6. 최댓값이 (라이언 - 어피치)를 한 점수보다 작다면  -> 즉, < 라이언의 최종 점수가 더 크다면. >
      최댓값 갱신 및 라이언의 정보를 answer 배열에 복사시켜 준다.
7. 라이언이 무조건 지거나, 비길경우 [-1] 배열을 return / 그렇지 않으면 answer 배열을 return으로 해결.
**/
public class Lv2_양궁대회 {
	static int max;
	static int[] answer;
	static int[] result;
	public static void main(String[] args) {
		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
		System.out.println(Arrays.toString(solution(5, info))); 
		int[] info1 = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		System.out.println(Arrays.toString(solution(1, info1))); 
	}

	// n: 화살의 개수를 담은 자연수
	// info: < 어피치 >가 맞힌 [ 과녁 점수의 개수 ]를 10점부터 0점까지 순서대로 담은 정수 배열
	public static int[] solution(int n, int[] info) {
		max = 0;
		answer = new int[11];
		result = new int[n]; // 라이언의 화살개수 만큼 초기화 -> 라이언이 쏜 과녁 점수 저장할 배열
		comb(0, n, 0, info);
		return max == 0 ? new int[] { -1 } : answer;
	}
	// 중복 조합
	public static void comb(int start, int m, int depth, int[] apeach_info) {
		if (depth == m) { // 화살의 개수 만큼 다 쏘았다면
			int[] ryan_info = new int[11];
			// 어피치 정보와 같이 라이언 정보도 과녁점수를 맞춘 갯수로 표현
			for (int score : result) ryan_info[10 - score]++;
			int ryan = 0;   // 라이언 점수
			int apeach = 0; // 어피치 점수
			for (int i = 0; i < 11; i++) {
				int score = 10 - i;
				// 라이언 정보와, 어피치 정보가 둘다 과녁점수를 < 0개 맞춘 원소는 무시. >
				if(ryan_info[i] == 0 && apeach_info[i] == 0) continue;
				// 라이언이 이긴경우( 라이언이 과녁 점수를 맞춘 갯수가 더 많다면 ) k점 즉 해당 socre 가져감
				if (ryan_info[i] > apeach_info[i]) ryan += score;
				if (ryan_info[i] < apeach_info[i]) apeach += score; // 어피치가 이긴 경우
				// 둘다 과녁점수를 맞춘 갯수가 같다면  -> 어피치가 점수를 가져감.
				if (ryan_info[i] == apeach_info[i]) apeach += score;
			}
			// 최댓값이 (라이언 - 어피치)를 한 점수보다 작다면  -> 즉, < 라이언의 최종 점수가 더 크다면. >
			if (max < ryan - apeach) {
				max = ryan - apeach; // 최댓값을 갱신하고 
				answer = ryan_info;  // 라이언 정보의 배열을 < answer 배열에 복사 >해 준다.
			}
			return;
		}
		for (int i = start; i < 11; i++) {
			// 라이언이 쏜 과녁 점수 저장
			result[depth] = i;
			comb(i, m, depth + 1, apeach_info);
		}
	}
}