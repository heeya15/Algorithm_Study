package Programmers;

import java.util.*;
public class Lv1_1차뉴스클러스터링 {
	/** 
	 * 예를 들어 집합 A = {1, 2, 3}, 집합 B = {2, 3, 4}라고 할 때, 
	 * 교집합 A ∩ B = {2, 3}, 합집합 A ∪ B = {1, 2, 3, 4}이 되므로,
	 * 집합 A, B 사이의 자카드 유사도 J(A, B) = 2/4 = 0.5가 된다. 
	 * 집합 A와 집합 B가 모두 공집합일 경우에는 나눗셈이 정의되지 않으니 따로 J(A, B) = 1로 정의한다.
	 * 문자열 "FRANCE"와 "FRENCH"가 주어졌을 때, 
	 * 이를 [ 두 글자씩 끊어서 ] 다중집합을 만들 수 있다.
	 * 각각 {FR, RA, AN, NC, CE}, {FR, RE, EN, NC, CH}가 되며, 
	 * 교집합은 {FR, NC}, 합집합은 {FR, RA, AN, NC, CE, RE, EN, CH}가 되므로, 
	 * 두 문자열 사이의 자카드 유사도 J("FRANCE", "FRENCH") = 2/8 = 0.25가 된다.
	 * 
	 * (입력)
	 * 문자열은 < 두 글자씩 끊어서 > 다중집합의 원소로 만든다.
	 * 이때 < 영문자로 된 글자 쌍만 유효 >하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 [ 그 글자 쌍을 버린다. ] 
	 * 예를 들어 "ab+"가 입력으로 들어오면, "ab"만 다중집합의 원소로 삼고, < "b+"는 버린다. >
	 * 다중집합 원소 사이를 비교할 때, < 대문자와 소문자의 차이는 무시 >한다. "AB"와 "Ab", "ab"는 같은 원소로 취급한다.
	 * 
	 * ( 문제 풀이 )
	 * - str1, str2를 모두 대문자로 형 변환 시켜준다.
	 *  -> 입력에서 대문자와 소문자의 차이는 무시한다, 즉 같은 원소로 취급한다 라고 언급이 되어있기 때문
	 * - str1과 str2각각 다중 집합 원소를 만들어준다.
	 * - 그 후, str2 다중 집합안에 str1의 집합원소가 포함되어 있는 것을 ( 교집합 리스트에 추가 ) 
	 *   -> str2 다중 집합에서 < str1 원소가 포함되어 있는 것은 제거 > 시켜준다. ( 나중에 합집합을 위해서 )
	 *   -> str1 다중 집합원소는 모두 합집합 리스트에 추가한다.
	 * - str2에 남은 원소를 합집합 리스트에 추가한다.
	 * - 교집합과, 합집합이 모두 0인경우는 1 * 65536으로 처리
	 * - 그렇지 않은 경우는 ( 교집합 / 합집합 ) * 65536 한 뒤 소수점 아래를 버린 정수 부분만을 출력하게 함
	**/
	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
		System.out.println(solution("handshake", "shake hands"));
		System.out.println(solution("aa1+aa2", "AAAA12"));
		System.out.println(solution("E=M*C^2", "e=m*c^2"));
	}

	public static int solution(String str1, String str2) {
		int answer = 0;
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		ArrayList<String> union = new ArrayList<>();
		ArrayList<String> gogip = new ArrayList<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			char first = str1.charAt(i);
			char second = str1.charAt(i + 1);
			if (first >= 'A' && first <= 'Z' && second >= 'A' && second <= 'Z') {
				String temp = "";
				temp += first;
				temp += second;
				list1.add(temp);
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			char first = str2.charAt(i);
			char second = str2.charAt(i + 1);
			if (first >= 'A' && first <= 'Z' && second >= 'A' && second <= 'Z') {
				String temp = "";
				temp += first;
				temp += second;
				list2.add(temp);
			}
		}
		// list2 리스트에 "list1 원소가 있다면" 교집합 리스트에 추가.
		for (int i = 0; i < list1.size(); i++) {
			if (list2.contains(list1.get(i))) {
				gogip.add(list1.get(i));
				list2.remove(list1.get(i)); // 교집합 요소를 list2 리스트에 제거. ( 합 집합을 만들기 위해 )
			}
			union.add(list1.get(i));
		}

		// 남은 list2 요소를 합집합에 추가.
		for (int i = 0; i < list2.size(); i++)union.add(list2.get(i));

		// A와 집합 B가 모두 공집합일 경우 j를 1로 정의
		if (gogip.size() == 0 && union.size() == 0) answer = 1 * 65536;
		else {
			double div = (double) gogip.size() / (double) union.size();
			answer = (int) (div * 65536);
		}
		return answer;
	}
}