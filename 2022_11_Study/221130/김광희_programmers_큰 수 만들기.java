package Programmers;

/**
( 문제 풀이 )
 1. 0 ~ (number.length() - k) (return 해야할 문자 길이 수 만큼) 인덱스 사이에서 가장 큰 값을 구한다.
 2. 제일 처음 index(탐색 시작점) 에서 (i+k) 인덱스 이하까지 가장 큰 숫자를 찾는다.
 3. 가장 큰 값을 찾았다면 max 에 갱신 후 index를 찾은 숫자 다음 index 값으로 갱신
    -> 아래 테스트 케이스 설명에 갱신해서 풀어나가는 그리디적 방법을 적어 둠.
 4. 시작점 에서 (i+k)까지 탐색이 끝난 후 가장 큰 숫자 값을 StringBuilder에 누적
 
 ( 테스트 케이스 이해 설명 )
 - 25843235 에서 4자리 => 맨 뒤 2,3,5를 제외한 숫자중에 가장 큰 수를 찾는다. 25843 ==> 8이다.
   sb = "8" 오른쪽 인덱스 부터 다시 탐색을 한다. 
 - 43235중에 맨뒤 2개를 제외하고 "432" 중 가장 큰 값을 찾는다. sb = "4" 오른쪽 인덱스 부터 다시 탐색을 한다.
 - 3235 중에 맨뒤 1개를 제외하고 "323" 중 가장 큰 값을 찾는다. sb = "3" 오른쪽 인덱스 부터 다시 탐색을 한다.
 - 235 중에 가장 큰 값을 찾는다 sb = "5" -> 마지막 자리의 큰 숫자를 찾는 과정
**/
public class Lv2_큰수만들기 {
	public static void main(String[] args) throws Exception {
		String number = "1924" ;
		String number1 = "1231234";
		String number2 = "4177252841"	 ;
		System.out.println(solution(number, 2));  // 답 : "94"
		System.out.println(solution(number1, 3)); // 답 : "3234"
		System.out.println(solution(number2, 4)); // 답 : "775841"
	}
	// number 문자열 형식 숫자, k = 제거할 수의 개수 
	public static String solution(String number, int k) {
		StringBuilder sb = new StringBuilder();
		int index = 0; // 탐색 범위의 시작점
		// return 해야할 문자 길이 수 만큼 반복.
		for (int i = 0; i < number.length() - k; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = index; j <= i + k; j++) {
				if (max < number.charAt(j) - '0') { // max 값 보다 더 큰 숫자를 찾을경우
					max = number.charAt(j) - '0'; // max 값 더 큰 숫자 값으로 갱신
					index = j + 1; // 가장 큰 수 다음 index를 저장. -> 그 다음 문자열 부터 가장 큰수를 찾아 나가야 하기 때문
				}
			}
			sb.append(max); // 앞의 탐색 범위에서 가장 큰 숫자를 StringBuilder에 누적
		}
		return sb.toString();
	}
}
