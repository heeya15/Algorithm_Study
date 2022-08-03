package Programmers;
/**
 * ( 문제 설명 )
 * 이 놀이기구의 원래 이용료는 price원 인데, 놀이기구를 [ N 번 째 이용 ]한다면 원래 이용료의 [ N배 ]를 받기로 하였습니다.
 * 처음 이용료가 100이었다면 2번째에는 200, 3번째에는 300으로 요금이 인상됩니다.
 * 놀이기구를 "count번 타게 되면" 현재 자신이 가지고 있는 금액에서 
 * 얼마가 모자라는지를 return 하도록 solution 함수를 완성하세요.
 * 
 * price	money	count	result
 *  3	     20	      4	      10
 */
public class Lv1_부족한금액계산하기 {
	public static void main(String[] args) {
		System.out.println(solution(3,20,4)); // 답 : 10
	}
	public static long solution(int price, int money, int count) {
        long answer = 0;
        long sum = 0; // 총 필요한 놀이기구의 이용 금액 담을 변수
        for(int i = 1; i <= count; i++) sum += price * i; // 놀이기구를 count번 타고 싶은 고객의 총 요금 계산
        answer = sum > money ? (sum-money) : 0; 
        return answer ;
    }
}