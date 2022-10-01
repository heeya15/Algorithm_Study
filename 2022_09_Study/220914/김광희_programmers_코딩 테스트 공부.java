package Programmers;
/**
( 문제 설명 )
- 풀 수 없는 문제를 해결하기 위해서는 알고력과 코딩력을 높여야 합니다. 
- 같은 문제를 여러 번 푸는 것이 가능
- 알고력과 코딩력을 얻는 최단시간을 return 하도록 solutuon 함수를 작성해주세요.

< 알고력과 코딩력을 높이기 위한 다음과 같은 [ 3가지 방법 ]이 있다. >

1. 알고력 상승을 위한 -> 알고리즘 공부   ( 1을 높이기 위해 1의 시간 )
2. 코딩력 상승을 위한 -> 코딩 공부      ( 1을 높이기 위해 1의 시간 )
3. 알고력과 코딩력을 높이기 위한 -> 풀 수 있는 문제 중 하나를 풀기 

1번 테케를 예시로
초기: 알고력 | 코딩력     증가하는 알고력 | 증가하는 코딩력 |  푸는대 드는 시간
      10     10 ->      2            1            2
      필요 알고력 : 10
      필요 코딩력 : 15
- 코딩력을 5를 늘리기 위해 방법 3가지중 코딩 공부 방법을 사용하여 [  5초 -> 해결 ]
- 이제 아래와 같은 상황에서 
- 10 15 -> 2 상승,1 상승,2초 -> 5번풀면  20 20 이 매꿔져서  
- 드는시간 2초 걸리니 5번 풀면 < 10초 걸리니> 5(앞에 걸린 시간) + 뒤에 문제 푸는 시간 10초 해서 = 총 15초 걸림
**/
public class Lv3_코딩테스트공부 {
	public static void main(String[] args) {
		int[][] problems = { { 10, 15, 2, 1, 2 }, { 20, 20, 3, 3, 4 } };
		System.out.println(solution(10, 10, problems));
	}
	// alp : 알고력, cop : 코딩력
	// problems 배열 원소 : 필요한 알고력, 필요한 코딩력, 증가하는 알고력 , 증가하는 코딩력, 푸는대 드는 시간
	public static int solution(int alp, int cop, int[][] problems) {
		int target_alp = 0;
		int target_cop = 0;
		
		// 목표치를 구하는 for문
		for (int i = 0; i < problems.length; i++) {
			target_alp = Math.max(problems[i][0], target_alp);
			target_cop = Math.max(problems[i][1], target_cop);
		}
		// 목표치 알고력이 <= 초기 알고력 보다 작거나 같고, 목표치 코딩력이 <= 초기 코딩력 보다 작거나 같으면 return 0
		if (target_alp <= alp && target_cop <= cop) return 0;
		
		// 알고력이 목표치 알고력 보다 크거나 같다면
		if (alp >= target_alp) alp = target_alp;
		
		// 코딩력이 목표치 코딩력 보다 크거나 같다면
		if (cop >= target_cop) cop = target_cop;
		
		int[][] dp = new int[target_alp + 2][target_cop + 2];

		return dp[target_alp][target_cop];
	}
}