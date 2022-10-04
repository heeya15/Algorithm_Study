package Programmers;

/**
 * 2020 KAKAO BLIND RECRUITMENT- Lv2_문자열 압축 - ( 문자열 문제 )
 * 
 * ( 문제 풀이 )
 * - 바깥쪽 for문은  s.length() / 2 만큼 반복 
 *   ex) 예를들어 문자열 길이 8인경우 주어진 문자열을 5로 잘라서 어떻게든 압축을 하려고 해도 할 수가없기 때문. 
 * - 안 쪽 for문은 "문자열을 i개 단위로 잘라가며" 압축하기 위해 다시 한번 for문 사용
 * - 현재 타겟과 다음 문자가 같은 경우 "압축 정도 증가", 
 *   그렇지 않다면 압축된 정도에 따라 문자열을 StringBuilder에 추가하고 < 타겟 패턴을 다음 문자로 변경 > 
 * - 안 쪽 for문이 끝나고 아직 압축이 안된 문자가 있는 것을 StringBuilder에 추가
 * - answer의 값과 "현재 StringBuilder에 압축된 문자열의 길이 중" < 더 작은 수를 answer 값으로 할당 >
 **/
public class Lv2_문자열압축 {
	public static void main(String[] args) {
		System.out.println(solution("aabbaccc")); // 답 : 7
		System.out.println(solution("ababcdcdababcdcd")); // 답 : 9
		System.out.println(solution("abcabcdede")); // 답 : 8
		System.out.println(solution("abcabcabcabcdededededede")); // 답 : 14
		System.out.println(solution("xababcdcdababcdcd")); // 답 : 17
	}

	public static int solution(String s) {
		int answer = s.length();
		// i개 단위로 자르며 반복
		for (int i = 1; i <= s.length() / 2; i++) {
			// 타겟 문자 패턴 설정
			String target = s.substring(0, i);
			int zipWordCnt = 1; // 압축 문자의 개수
			StringBuilder sb = new StringBuilder();// 압축 완료한 문자를 저장하는 부분.

			for (int start = i; start <= s.length(); start += i) {
				String next = s.substring(start, start + i > s.length() ? s.length() : start + i);

				if (target.equals(next)) zipWordCnt++; // 현재 타겟과 다음 문자가 같은 경우 "압축 정도 증가"
				else {
					// 첫 번째 테케 경우를 예시로 현재 "압축 정도는 2이고" < 1번째 인덱스 a 2번째 인덱스 b > 다르닌깐
					// (압축 문자의 개수)(반복되는 값) --> 2a로 압축된다.
					// 압축 정도가 1이 아닌경우 -> 2a, 1인 경우는 그냥 반복되는 문자 값 a로 StringBuilder에 추가.
					sb = zipWordCnt != 1 ? sb.append(zipWordCnt + target) : sb.append(target);
					target = next; // 타겟 문자 패턴을 다음 문자로 지정.
					zipWordCnt = 1; // 압축 정도 1로 초기화
				}
			}
			sb.append(target); // 마지막에 추가안된 타겟 문자 패턴 추가
			answer = Math.min(answer, sb.length()); // 가장 작은 문자열 길이를 저장
		}
		return answer;
	}
}