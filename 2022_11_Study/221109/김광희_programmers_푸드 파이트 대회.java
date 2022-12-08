package Programmers;

/** 
	( 문제 설명 )
	food[0] = 물
	그 뒤 i번 음식의 수이다.
	1번 테케로 예시
	1번 : 3개 , 2번 : 4개 , 3번 : 6개 -> 1번 음식 1개는 대회에 사용하지 못함
	즉, 음식의 개수가 짝수개가 있어야 서로 먹을 수 있다. 
	
	( 문제 풀이 )
	1. 해당 하는 "i번 음식의 수를" 2로 나눈 몫 만큼 StringBuilder에 붙인 뒤, 
	"0" 을 더하고, StringBuilder에 붙인 수를 거꾸로 더 해주면 해결!
**/

public class Lv1_푸드파이트대회 {
	public static void main(String[] args) {
		int[] food = { 1, 3, 4, 6 };
		System.out.println(solution(food)); // 답 : "1223330333221"
		int[] numbers1 = { 1, 7, 1, 2 };
		System.out.println(solution(numbers1)); // 답 : "111303111"
	}

	public static String solution(int[] food) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < food.length; i++) {
			for (int j = 0; j < food[i] / 2; j++) {
				sb.append(Integer.toString(i));
			}
		}
		return sb.toString() + "0" + sb.reverse();
	}
}