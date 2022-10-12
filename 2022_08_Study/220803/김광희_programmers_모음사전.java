package Programmers;

/**
 * ( 문제 )
 * 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 
 * 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 
 * 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다. 
 * 단어 하나 word가 매개변수로 주어질 때, 이 단어가 < 사전에서 몇 번째 단어인지 반환 >하면 됩니다.
 * 
 * ( 제한 사항 )
 * word의 길이는 1 이상 5 이하입니다.
 * word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
 * 
 * ( 문제 풀이 )
 * 1. DFS를 활용하여 단어 길이가 < 1이상 5 이하라고 제한 > 되어 있기 때문에 < 알파벳 AEIOU 배열을 이용하여 >
 *    깊이는 1씩 증가하며 alpha 배열 인덱스를 중복 허용하여 사용하였음
 *    ex) A 1
 *    ex) AA 2
 *    ex) AAAAA 5
 *    ex) AAAAU 9
 * 2. 만약 타겟이 아닌 찾을 단어 길이가 5를 초과하는 경우는 볼 필요도 없기 때문에 return ( 가지 치기 )
 *    < 5를 초과하지 않는 경우만 > 몇 번째 단어인지 알기 위해 count 변수에 +1 씩 해줌.
 * 3. 알파벳 모음들을 사용하여 찾을 target 단어와 같은 단어인 경우 boolean 타입 find 변수에 true 후
 *    해당하는 단어를 사전에서 찾았으면 더 이상 찾을 필요 없기 때문에 재귀들을 종료 시켜 줌.
 */
public class Lv2_모음사전 {
	static char[] alpha = { 'A', 'E', 'I', 'O', 'U' };
	static int count;
	static boolean find;
	public static void main(String[] args) {
		System.out.println(solution("AAAAE"));
		System.out.println(solution("AAAE"));
		System.out.println(solution("I"));
	}

	public static int solution(String word) {
		count = 0;
		find = false;
		dfs(0, "", word);
		return count;
	}

	public static void dfs(int depth, String line, String target) {
		if (depth > 5) return; // word 길이가 5를 초과하는 경우는 볼 필요도 없기 때문에 return( 가지 치기 )
		if (line.equals(target)) { // 알파벳 모음들을 사용하여 찾을 단어와 같은 단어인 경우 find 변수에 찾음 표시
			find = true;
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if (find)return;// 해당하는 단어를 사전에서 찾았으면 더 이상 찾을 필요 없기 때문에 재귀들을 종료 시켜 줌.
			else {
				if (depth + 1 < 6) count++;
				dfs(depth + 1, line + alpha[i], target);
			}
		}
	}
}