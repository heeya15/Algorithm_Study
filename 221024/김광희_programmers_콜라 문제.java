package Programmers;

/**
 * ( 문제 풀이 ) 
 * 1. n / a 가 0이면 while문 탈출 ->  상빈이가 받을 수 있는 총 콜라 수 
 *    - while 문 안에서  받는 콜라 수(receving_coke)와, 상빈이가 마트에 빈병을 가져가고 남은 콜라(remaider_coke)를  저장 
 *    - answer에 받는 콜라 수(receving_coke) 누적
 *    - n을 받는 콜라 수와, 마트에 빈병을 가져가고 남은 콜라 수 -> 즉 상빈이가 현재 들고있는 콜라 수로 초기화 시켜준다.
 */
public class Lv1_콜라문제 {
	public static void main(String[] args) throws Exception {
		System.out.println(solution(2,1,20)); // 답 : 19
	}
	/**
    a : 마트에 주어야 하는 병 수, 
    b : 빈 병 a개를 가져다 주면 마트가 주는 콜라 병수 b
    c : 상빈이가 처음에 가지고 있는 빈 병의 개수
    **/
   public static int solution(int a, int b, int n) {
	   int answer = 0;
       // n/ a == 0 이면  while문 탈출 -> 상빈이가 받을 수 있는 총 콜라 수 출력 
       while(n / a > 0){ 
           int receiving_coke = b * (n / a); // 받는 콜라
           int remaider_coke = n % a;        // 상빈이가 마트에 가져가고 나서 남은 콜라.
           answer += receiving_coke;         //  상빈이가 받을 수 있는 콜라 수 누적
           n = receiving_coke + remaider_coke;  // 상빈이가 가지고 있는 콜라 병의 수
       }
       return answer;
   }
}
