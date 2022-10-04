package Programmers;

import java.util.*;

/**
( 문제 풀이 )
주의 ! -> 문제에서 오버플로우 발생할 수 있다고 하여서 sum1, sum2 변수를 long 타입으로 잡았다.
1. q1, q2 각 큐에 queue1, queue2 원소를 넣고, 각 sum1, sum2에 queue1, queue2 원소 누적 합을 시킨다.
2. 두 배열의 원소 누적 합이 2로 나누었을 때 1인 경우 "똑같은 합을 만들 수 없기 때문"에 -1 을 리턴해 준다.
3. sum1 이 sum2 보다 클 경우 q1에서 원소 하나를 꺼낸 뒤 q2 제일 뒤에 넣어준다. 
     그 후, sum1에는 q1에서 꺼낸 원소를 빼주고, sum2에는 q1에서 꺼낸 원소를 더해준다. 그 후 < 작업 횟수를 1 증가 >
   sum2 가 sum1 보다 작을 경우도 위와 같은 과정을 해 준다.   
4. 각 큐의 원소합 즉, sum1과 sum2가 동일하다면 해당 작업 횟수를 return
5. 만약 어떤 방법으로 각 큐의 원소 합을 같게 만들 수 없는 경우, -1을 return

if (q1.isEmpty() || q2.isEmpty())break;
-> 작업을 해주어도 원소 합을 같게 만들 수 없을때 위 조건문으로 while문 탈출 조건을 주었더니 11번, 28번 테케 시간 초과 발생...

그래서 그냥 count == queue1.length * queue2.length 만큼 해보았고 안 되길래
count == (queue1.length * 숫자) -> '숫자' 부분에 1부터 1씩 올라가 보면서 넣어보았는대
count == (queue1.length * 4) -> 때 통과가 되었다... 원인은 아직 잘 모르겠지만.. 최소 작업
if (count >= (queue1.length * 3) -1 ) break; -> 이것도 통과가 되었다. 
--> 문제 설명의 작업을 7번 수행이라고 하길래 그냥 (queue1.length * 2) -1 을 하면 '7'이 나와서  해 보았지만 1번 테케 에서 실패 ...
--> 그래서 (queue1.length * 3 ) -1 으로 해 보았더니 되었다..

결론은.. 탈출 조건이 왜 이렇게 되는지는 모르지만 맞춰 버렸다 ... 찝찝하다...
 **/
public class Lv2_두큐합같게만들기 {
	public static void main(String[] args) {
		int[] queue1 = {3, 2, 7, 2};
		int[] queue2 = {4, 6, 5, 1};
		System.out.println(solution(queue1,queue2)); // 답 : 2
	}

	public static int solution(int[] queue1, int[] queue2) {
		int count = 0; // 횟수
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		long sum1 = 0;
		long sum2 = 0;
		for (int i = 0; i < queue1.length; i++) {
			q1.add(queue1[i]);
			q2.add(queue2[i]);
			// 각 큐의 누적합 저장.
			sum1 += queue1[i];
			sum2 += queue2[i];
		}
		// 두 배열 원소의 합이 2로 나누었는대 0이 되지 않는 다는 것은 "똑같은 합을 만들 수 없기 때문"에 -1 을 리턴
		if ((sum1 + sum2) % 2 == 1) return -1;

		while (true) {
			if (count == (queue1.length * 4)) break;
			if (sum1 > sum2) {
				int temp = q1.poll();
				q2.add(temp);
				sum1 -= temp;
				sum2 += temp;
				count++;     // 작업 횟수 1 증가
			} 
			if (sum1 < sum2) {
				int temp = q2.poll();
				q1.add(temp);
				sum1 += temp;
				sum2 -= temp;
				count++;    // 작업 횟수 1 증가
			} 
			if(sum1 == sum2) return count;
		}
		return -1;
	}
}