package Programmers;

import java.util.*;

/**
 * ( 문제 풀이 )
 * 1. expression 문자열에서 숫자와, 연산자를 ArrayList에 각각 구분해서 담아준다
 * 2. 3가지 연산자의 순열 경우의 수를 구한 뒤, 숫자와, 연사자를 담은 ArrayList 원본을 
 *    copy_numbers, copy_ops ArrayList에 복사해서 
 *    -> 연산자의 순열 경우의 수를 구한 배열에서 해당 연산자(temp_op[i])와 expression 문자열에 들어있는 연산자(copy_ops.get(j))와 같을 경우
 *	  -> copy_ops.get(j) 에서 쓰인 인덱스 j를 사용하여 copy_numbers.remove(j),copy_numbers.remove(j) 두가지 숫자를 제거하면서
 *    -> copy_ops.get(j) 연산자를 통해 계산해 준다.
 *    ex) temp_op = {+,-,*}
 *        copy_numbers = {50 6 3 2}
 *        copy_ops = {*,-,*};
 *        temp_op의 '-' 문자와 copy_ops의 1번째 인덱스 '-'와 같으므로 
 *        copy_numbers의 1번째 인덱스 값 6 을 제거하며 사용하고, ArrayList는 앞으로 댕겨진다. [ 50 3 2 ] 가 됨
 *        또 copy_numbers의 1번째 인덱스 값 3 을 제거하며 사용함 
 *        따라서, 6 - 3 = 3이 됨.
 * 3. 위 처럼 계산할 숫자를 copy_numbers에 j번째 인덱스에 추가해 주고, expression 문자열에 들어있는 연산자 (copy_ops.get(j))는 사용했기 때문에 제거해 준다.
 * 4. expression 문자열에 들어있는 연산자 하나를 제거 했기 때문에  ArrayList의 크기가 하나 줄어서 copy_ops 기존 사이즈(j)를 -1로 하나씩 사이즈를 땡겨준다.
 * 5. [ 우승 상금은 ] 연산자 우선 순위 조합으로 계산한 수식의 결과 값에 [ 절대값을 취한 것이 ] 가장 큰 값이 된다.
 */
public class Lv2_수식최대화 {
	public static char[] op = { '+', '-', '*' };
	public static boolean[] isSelected;
	public static ArrayList<Long> numbers;
	public static ArrayList<Character> ops;
	public static long answer;
	public static char[] temp_op;
	public static void main(String[] args) {
		System.out.println(solution("50*6-3*2")); // 답: 300
		System.out.println(solution("100-200*300-500+20")); // 답: 60420
	}

	public static long solution(String expression) {
		answer = 0;
		numbers = new ArrayList<>();// 숫자 담을 리스트
		ops = new ArrayList<>();    // 연산자 담을 리스트
		String number = "";
		for (int i = 0; i < expression.length(); i++) {
			// 0~ 9 사이 문자인 경우 문자열에 문자를 붙여줌.
			if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
				number += expression.charAt(i);
			} else { // 특수 기호를 만났다면
				numbers.add(Long.parseLong(number));
				number = "";
				ops.add(expression.charAt(i));
			}
		}
		if(number.length() > 0) numbers.add(Long.parseLong(number));
		isSelected = new boolean[3]; // 방문처리 용도 배열
		temp_op = new char[3];       // 연산자 순열 경우의 수를 담을 배열 
		per(0);
		return answer;
	}

	// 순열
	public static void per(int depth) {
		if (depth == 3) { // 3가지 연산자 조합을 골랐을 경우
			ArrayList<Long> copy_numbers = new ArrayList<>(numbers);       // ArrayList 원본을 인쇄 함
			ArrayList<Character> copy_ops = new ArrayList<Character>(ops); // ArrayList 원본을 인쇄 함
			for (int i = 0; i < temp_op.length; i++) {
				for (int j = 0; j < copy_ops.size(); j++) {
					if (temp_op[i] == copy_ops.get(j)) { // 연산자 조합과, 연산 수식이 같을 경우
						long num = cal(copy_numbers.remove(j), copy_numbers.remove(j), temp_op[i]);
						copy_numbers.add(j, num);
						copy_ops.remove(j);
						j--; // 전체 적으로 ArrayList의 크기가 하나 줄어서 -1로 하나씩 사이즈를 땡겨준다.
					}
				}
			}
			// [ 우승 상금은 ] 연산자 우선 순위 조합으로 계산한 수식의 결과 값에 절대값을 취한 것이 가장 큰 값이 된다.
			answer = Math.max(answer, Math.abs(copy_numbers.get(0)));
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				temp_op[depth] = op[i];
				per(depth + 1);
				isSelected[i] = false;
			}
		}
	}

	public static long cal(long a, long b, char op) {
		long result = 0;
		switch (op) {
			case '+':
				result = a + b;
				break;
			case '-':
				result = a - b;
				break;
			case '*':
				result = a * b;
				break;
			}
		return result;
	}
}