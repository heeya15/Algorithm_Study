package 구현;

import java.io.*;
import java.util.*;

/**
 * 컴퓨터 수가 모자라게 된 것이다.
 * 이런 사태를 도저히 용납할 수 없었던 준하는 곧 전역하는 선임을 설득해 민원을 넣도록 하는 데 성공
 * 컴퓨터를 증설하기로 했다. 또한, 컴퓨터 간의 [ 사용률에 따라 ] 다른 성능의 컴퓨터를 설치하고자 한다.
 * 모든 사람이 항상 정해진 시간에 싸지방을 이용한다는 사실을 발견
 * 컴퓨터가 있는 자리에는 1번부터 순서대로 번호가 매겨져 있다.
 * 싸지방에 들어왔을 때 비어있는 자리 중에서 번호가 가장 작은 자리에 앉는 것이 규칙
 * 모든 사람이 기다리지 않고 [ 싸지방을 이용할 수 있는 컴퓨터의 최소 개수 ]와 [ 자리별로 몇 명의 사람이 사용했는가 ]를 구하시오.
 * 
 * ( 문제 풀이 )
 * 1. 각 사람의 시작 시간과, 종료시간을 Person 객체 배열에 저장한다. 
 *    -> Person 객체에 담겨져 있는 정보 중 시작 시간을 기준으로 오름차순 정렬, 시작 시간이 같다면 종료시간 오름차순 정렬을 한다.
 * 2. 해당 자리에 앉은 사람의 횟수를 구하기 위해 계수 용도의 배열(arr)을 선언 및 초기화 한다.
 * 3. 현재 번호에 앉아 있는 인덱스 표시를 위한 (empty_idx)변수를 활용한다.
 * 4. 사람의 수 만큼 반복문을 돌리는대 
 *    (1)- 먼저 컴퓨터를 이용하고 있는 사람의 끝나는 시각과, 해당 자리 번호를 담는 우선순위 큐가 비어있지 않고,
 *         "현재 사람의 시작 시간" 보다, 제일 먼저 싸지방에 < 먼저 와서 앉은 사람의 종료 시간이 더 짧거나 같은 경우 > 
 *         -> 해당 사람의 컴퓨터 번호에 앉는다.
 *    (2)- < 번호가 작은 자리에 앉아 있던 사람 중 > 자리가 비어 있는 곳이 있다면
 *         해당 번호에 새로운 사람이 앉는다
 *    (3)- 비어있는 곳이 없다면 가장 작은 번호에 앉아 있는 사람 < 다음 인덱스에 > 새로 사지방에 들어온 사람이 앉는다.
 * 5. 컴퓨터의 최소 개수 X를 출력 하고 줄 바꾼 뒤, 1번 자리 부터 X번 자리까지 순서대로 각 자리를 사용한 사람의 수를 띄어쓰기 간격으로 출력
 */
public class Main_G3_12764_싸지방에간준하 {
	public static class Person implements Comparable<Person>{
		int start_time, end_time;
		public Person(int start_time, int end_time) {
			this.start_time = start_time;
			this.end_time = end_time;
		}
		
		// 시작 시간 오름차순 정렬, 시작 시간이 같을경우 종료시간 오름차순 정렬
		public int compareTo(Person o) {
			if(this.start_time == o.start_time) {
				return this.end_time - o.end_time;
			}
			return this.start_time - o.start_time; 
		}
	}
	public static class Computer implements Comparable<Computer>{
		int end_time, index;
		public Computer(int end_time, int index) {
			this.end_time = end_time;
			this.index = index;
		}
			
		public int compareTo(Computer o) {
			return this.end_time - o.end_time; 
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 사람의 수
		Person [] person = new Person[N];
		
		// N개의 줄에 걸쳐서 각 사람의 컴퓨터 이용 "시작 시각"과 "종료 시각"이 주어진다
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			person[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// 1. 시작 시각이 가장 빠른 순으로 정렬
		Arrays.sort(person);
		int [] arr = new int[N];  // 2. 해당 자리에 앉은 사람의 횟수를 구하기 위해 계수 용도의 배열을 초기화.
		int empty_idx = 0;        // 비어있는 자리 인덱스 번호
		
		PriorityQueue<Integer> pq_end_seat = new PriorityQueue<>(); 		 // 번호가 작은 자리에 앉아 있던 사람 자리가 비는 곳의 인덱스 번호를 담는 용도
		PriorityQueue<Computer> pq_computer_endtime = new PriorityQueue<>(); // 끝나는 시각에 대한 컴퓨터 정보
		
		for (int i = 0; i < N; i++) {	
			// 현재 사람의 시작 시간 보다, 제일 먼저 싸지방에 < 먼저 와서 앉은 사람의 종료 시간이 더 짧거나 같은 경우 > -> 해당 사람의 컴퓨터 번호에 앉는다.
			while(!pq_computer_endtime.isEmpty() && person[i].start_time >= pq_computer_endtime.peek().end_time ) {
				pq_end_seat.offer(pq_computer_endtime.poll().index);
			}
			
			// 번호가 작은 자리에 앉아 있던 사람 중 자리가 비어 있는 곳이 있다면
			if(!pq_end_seat.isEmpty()) {
				int end_idx = pq_end_seat.poll();
				arr[end_idx]++;
				pq_computer_endtime.offer(new Computer(person[i].end_time, end_idx));
			}else { // 컴퓨터 정보가 비어있는 경우 ( 싸지방 컴퓨터 앞 자리에 아무도 앉지 않은 경우 )
				arr[empty_idx]++;
				pq_computer_endtime.offer(new Computer(person[i].end_time, empty_idx));
				empty_idx++;
			}
		}
		StringBuilder sb = new StringBuilder();
		// 모든 사람이 기다리지 않아도 되는 컴퓨터의 최소 개수 X를 출력
		sb.append(empty_idx).append("\n");
		// 1번 자리 부터 X번짜리 까지 순서대로 각 자리를 사용한 사람의 수를 띄어쓰기 간격으로 출력
		for (int i = 0; i < N; i++) if(arr[i]!=0) sb.append(arr[i]+" ");
		// 최종 출력
		System.out.println(sb.toString());
	}
}
