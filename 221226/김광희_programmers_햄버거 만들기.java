package Programmers;

import java.util.*;
/*
 * 1 : 빵
 * 2 : 야채
 * 3 : 고기
 * 
 * ( 문제 풀이 ) 
 * 1. stack에 햄버거 재료를 넣는다.
 * 2. 햄버거 재료가 4개 이상 쌓일경우 -> 재료 순서를 비교할 수 있으므로 조건문을 태운다.
 *    ->  빵 야채 고기 빵 으로 쌓인 햄버거 일 경우 -> 햄버거 개수 증가 및 사용된 햄버거 재료 제거
 */
public class Lv1_햄버거만들기 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 2, 1, 1, 2, 3, 1, 2, 3, 1 })); // 2
		System.out.println(solution(new int[] { 1, 3, 2, 1, 2, 1, 3, 1, 2 })); // 0
	}
	public static int solution(int[] ingredient) {
		int answer = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < ingredient.length; i++) {
			stack.push(ingredient[i]);
			if (stack.size() >= 4) { // stack에 햄버거 재료가 4개 이상 쌓일경우 -> 재료 순서를 비교할 수 있음
				int stack_size = stack.size();
				// 빵 야채 고기 빵 으로 쌓인 햄버거 일 경우
                if(stack.get(stack_size-4) == 1 
                && stack.get(stack_size-3) == 2
                && stack.get(stack_size-2) == 3
                && stack.get(stack_size-1) == 1)
                {
					answer++; // 햄버거 개수 증가
					// 사용된 햄버거 재료 제거
					stack.pop(); 
					stack.pop();
					stack.pop();
					stack.pop();
				}
			}
		}
		return answer;
	}
}