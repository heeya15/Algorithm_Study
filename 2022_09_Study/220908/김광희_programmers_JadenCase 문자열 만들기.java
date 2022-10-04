package Programmers;

/**
 * ( 문제 풀이 )
 * 1. 매개변수로 주어진 문자열 s를 모두 소문자로 만들어준 것을 char 배열안에 각 원소별로 넣어준다.
 * 2. isFirst라는 첫 문자 판별 상태 변수를 true로 초기화를 둔 뒤, 주어진 문자열을 가지고 만든 char 배열 길이 만큼 반복문 돌기
 *    -> 첫 문자는 우선 대문자로 처리 하기 위해 isFirst 라는 첫 문자 판별 상태 변수 true로 초기화
 * 3. 그 후, 공백을 만났을 경우 isFirst라는 상태변수가 true, 그렇지 않다면 false로 초기화 시킴.
 *    -> 공백 다음에 글자를 대문자로 만들기 위해서 상태변수 true로 하고 그렇지 않으면 false로 다 소문자로 StringBuilder에 넣어준다.
 * 4. 위 과정을 통하여 StringBuilder 에 바뀐 문자열을 출력.
 */
public class Lv2_JadenCase문자열만들기 {
	public static void main(String[] args) throws Exception {
		System.out.println(solution("3people unFollowed me")); // 답 : "3people Unfollowed Me"
	}

	public static String solution(String s) {
		char[] arr = s.toLowerCase().toCharArray();
		StringBuilder sb = new StringBuilder();

		boolean isFirst = true;
		for (int i = 0; i < arr.length; i++) {
			sb.append(isFirst ? Character.toUpperCase(arr[i]) : arr[i]); // true인 경우 첫 문자 대문자로 변환하여 넣기
			isFirst = arr[i] == ' ' ? true : false; // 공백을 만났다면 상태 true -> 다음 글자 대문자로 만들기 위해
		}
		return sb.toString();
	}
}