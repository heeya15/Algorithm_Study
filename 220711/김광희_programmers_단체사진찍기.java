package Programmers;

import java.util.*;
/** 
 * 2017 카카오코드 본선 - Lv2_단체사진 찍기 ( 순열을 활용한 완전 탐색 문제 )
 * 
 * 네오는 프로도와 나란히 서기를 원했고
 * 튜브가 뿜은 불을 맞은 적이 있던 '라이언'은 < 튜브에게서 적어도 세 칸 이상 떨어져서 서기 >를 원했다.
 * 사진을 찍고 나서 돌아오는 길에, 
 * 무지는 모두가 원하는 조건을 만족하면서도 [ 다르게 서는 방법이 있지 않았을까 ] 생각해보게 되었다.
 * [ 각 프렌즈가 원하는 조건을 입력 ]으로 받았을 때 모든 조건을 만족할 수 있도록 서는 < 경우의 수를 계산 >하는 프로그램을 작성해보자.

( 입력 형식 )
- "첫 번째" 글자는 "조건을 제시"한 프렌즈, "세 번째" 글자는 < 상대방 >이다. 
- 첫 번째 글자와 세 번째 글자는 항상 다르다. 
- 네 번째 글자는 다음 3개 중 하나이다. {=, <, >} 각각 같음, 미만, 초과
- 다섯 번째 글자는 0 이상 6 이하의 정수의 문자형이며, 조건에 제시되는 간격을 의미

 ( 문제 풀이 )
 * 순열을 활용하여 카카오 프렌즈가 설 수 있는 모든 경우의 수를 구한 뒤, 
 * < 매개변수로 주어진 " data의 원소 다섯 글자로 구성된 문자열을 활용 ">하여
 * 주어진 조건을 모두 만족하는 경우 결과 변수 answer을 +1로 해 줌.
**/
public class Lv2_단체사진찍기 {
	static int answer;
	// 어피치, 콘, 프로도, 제이지, 무지, 네오, 라이언, 튜브를 의미
	static char[] friends = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
	static char[] position;
	static boolean[] isSelected;
    
	// 2번째 방법에 사용될 변수
	static int answer2;
	static HashMap<Character, Integer> map; // 모든 구성원의 정보를 담을 HashMap
	static int[] position2;
	static boolean[] isSelected2;

	public static void main(String[] args) {
		String[] data = { "N~F=0", "R~T>2" };
		System.out.println(solution(2, data));
		System.out.println(solution2(2, data));
	}

	public static int solution(int n, String[] data) {
		answer = 0;
		isSelected = new boolean[8]; // 프렌즈 친구들 알파벳 문자 체크 용도.
		position = new char[8];
		per(0, data);
		return answer;
	}

	public static void per(int depth, String[] data) {
		if (depth == 8) {
			if (check(data)) answer++;
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				position[depth] = friends[i];
				per(depth + 1, data);
				isSelected[i] = false;
			}
		}
	}

	public static boolean check(String[] data) {
		for (int i = 0; i < data.length; i++) {
			char first_word = data[i].charAt(0); // 첫 번째 글자
			char third_word = data[i].charAt(2); // 세 번째 글자
			char op = data[i].charAt(3); // 부등호
			int index = data[i].charAt(4) - '0'; // 사이에 간격을 의미
			// ex ) "< 2" 라면 2보다 작은 간격 -> 즉 0 또는 1개의 글자가 첫번째, 세 번째 글자 사이에 있으면 조건에 해당한 다는 뜻.
			int first = 0;
			int third = 0;
			for (int j = 0; j < 8; j++) {
				if (first_word == position[j]) first = j;
				if (third_word == position[j]) third = j;
			}
			int diff = Math.abs(third - first);
			switch (op) {
				case '=':
					if (diff - 1 != index) return false;
					break;
				case '<':
					if (diff - 1 >= index) return false;
					break;
				case '>':
					if (diff - 1 <= index) return false;
					break;
			}
		}
		return true;
	}
	
    /*******************************************************************************/
	// 2 번째 방법
	public static int solution2(int n, String[] data) {
		answer2 = 0;
		isSelected2 = new boolean[8]; // 프렌즈 친구들 알파벳 문자 체크 용도.
		position2 = new int[8];
		map = new HashMap<>();
		// 어피치, 콘, 프로도, 제이지, 무지, 네오, 라이언, 튜브를 의미
		map.put('A', 0);
		map.put('C', 1);
		map.put('F', 2);
		map.put('J', 3);
		map.put('M', 4);
		map.put('N', 5);
		map.put('R', 6);
		map.put('T', 7);
		per2(0, data);
		return answer2;
	}

	public static void per2(int depth, String[] data) {
		if (depth == 8) {
			if (check2(data)) answer2++;
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (!isSelected2[i]) {
				isSelected2[i] = true;
				position2[depth] = i;
				per2(depth + 1, data);
				isSelected2[i] = false;
			}
		}
	}

	public static boolean check2(String[] data) {
		for (int i = 0; i < data.length; i++) {
			int first_word = position2[map.get(data[i].charAt(0))]; // 첫 번째 글자 인덱스
			int third_word = position2[map.get(data[i].charAt(2))]; // 세 번째 글자 인덱스
			int index = data[i].charAt(4) - '0' + 1; // 해당 사이의 간격
   
			switch (data[i].charAt(3)) {
			case '=':
				if (Math.abs(first_word - third_word) != index) return false;
				break;
			case '<':
				if (Math.abs(first_word - third_word) >= index) return false;
				break;
			case '>':
				if (Math.abs(first_word - third_word) <= index) return false;
				break;
			}
		}
		return true;
	}
}