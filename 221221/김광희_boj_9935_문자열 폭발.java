package 여러알고리즘활용;

import java.io.*;
import java.util.*;
/**
 * ( 문제 풀이 ) 
 * 1. 스택을 활용하여 입력받은 문자열 맨 앞에서부터 하나씩 스택에 넣어준다.
 * 2. 스택의 사이즈가 폭발 문자열 길이와 같다면 -> 폭발 문자열이 존재할 가능성이 있기때문에 
 *    -> if문 안에서 스텍에 들어있는 문자와 폭발 문자열의 문자와 비교하여 "폭발 문자열이 포함되어 있는지, 없는지 " 여부를
 *       isCheck 변수로 판단한다.
 *    -> 만약 폭발 문자열이 포함되어 있다면 stack에서 폭발 문자열을 꺼내준다.
 * 3. 모든 폭발이 끝나고 난 후 스텍에 남은 문자열을 sb에 누적해 준다.
 * 4. 그 후 StringBuilder에 남아있는 문자가 없는 경우가 있다. 이때는 "FRULA"를 출력 
 *    -> 남아 있는 문자가 있다면 그대로 출력
 */
public class Main_G4_9935_문자열폭발 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();      // 문자열
		String boom_str = br.readLine(); // 폭발 문자열
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i)); // 스택에 문자열의 단일 문자를 하나씩 넣는다.
			
			// 스택 사이즈가 폭발 문자열 길이와 같거나 크다면 -> 폭발 문자열이 존재할 가능성이 있음
			if(stack.size() >= boom_str.length()) {
				boolean isCheck = true; // 폭발 문자열이 있는지를 체크하기 위한 변수
				
				for (int j = 0; j < boom_str.length(); j++) {
					/* 
					 * ex) 테케 1번 예시로 폭발 문자열 길이 2이기 때문에 if문에 들어온다.
					 *     2 - 2 + 0 = 0 번째 스텍을 가져오면 m을 가져오게 된다.
					 */
					char t1 = stack.get(stack.size() - boom_str.length() + j);
                    char t2 = boom_str.charAt(j);
                    if(t1 != t2) { //폭발 문자열이 포함되어 있지 않다면 
                    	isCheck = false;
                        break;
                    }
				}
				if(isCheck) { // 폭발 문자열이 포함되어 있다면
					// 폭발 문자열을 스텍에서 꺼내준다 -> 테케 1번 예시로 4먼저 꺼내고 C를 꺼내준다. -> 늦게 들어온게 먼저 나가기 때문
					for (int j = 0; j < boom_str.length(); j++) stack.pop();			
				}
			}
		}
		// 모든 폭발이 끝나고 난 후 스텍에 남은 문자열을 sb에 누적해 준다.
		StringBuilder sb = new StringBuilder();
		for (Character st : stack ) sb.append(st);
		System.out.println(sb.toString().isEmpty() ?"FRULA" : sb.toString());
	}
}
