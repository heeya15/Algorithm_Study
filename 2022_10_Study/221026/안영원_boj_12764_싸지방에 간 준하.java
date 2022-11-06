import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// 대기 인원 -> 시작 시간이 작은 순서대로 꺼내기위해 정렬
		PriorityQueue<Person> waitQ = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sTime = Integer.parseInt(st.nextToken());
			int eTime = Integer.parseInt(st.nextToken());

			waitQ.offer(new Person(sTime, eTime));
		}

		// 싸지방을 이용 중인 인원 -> 본인 시간보다 종료 시간이 작은 사람 꺼내기 -> 종료 시간 작은 순서대로 정렬
		PriorityQueue<Player> playerQ = new PriorityQueue<>();

		// 사용가능한 컴퓨터 확인용
		PriorityQueue<Integer> comQ = new PriorityQueue<>();

		// 컴퓨터 사용 횟수
		int[] used = new int[100001];

		// 탐색 시작
		int max = 1;
		while (!waitQ.isEmpty()) {
			Person person = waitQ.poll();

			// 본인보다 종료시간이 이전인 사람 추출
			while (!playerQ.isEmpty()) {
				if (person.sTime >= playerQ.peek().eTime) {
					comQ.offer(playerQ.poll().cIdx);
				} else break;
			}

			// 가능한 곳에 본인 입장
			if (comQ.isEmpty()) {
				playerQ.offer(new Player(person.eTime, max));
				used[max]++; // 사용 횟수 표시
				max++;
			} else {
				int cIdx = comQ.poll();
				used[cIdx]++; // 사용 횟수 표시
				playerQ.add(new Player(person.eTime, cIdx));
			}
		}


		for (int i = 1; i < 100001; i++) {
			if (used[i] == 0) {
				max = i - 1;
				break;
			}
			sb.append(used[i] + " ");
		}
		
		System.out.println(max);
		System.out.println(sb);
	}

	static class Person implements Comparable<Person> {
		int sTime;
		int eTime;

		Person(int sTime, int eTime) {
			this.sTime = sTime;
			this.eTime = eTime;
		}

		@Override
		public int compareTo(Person o) {
			return sTime - o.sTime;
		}
	}

	static class Player implements Comparable<Player> {
		int eTime;
		int cIdx; // 사용 중인 컴퓨터 번호

		Player (int eTime, int cIdx) {
			this.eTime = eTime;
			this.cIdx = cIdx;
		}

		@Override
		public int compareTo(Player o) {
			return eTime - o.eTime;
		}
	}
}