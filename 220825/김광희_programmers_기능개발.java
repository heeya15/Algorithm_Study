package Programmers;
import java.util.*;
/**
 * 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
 * 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 
 * 이때 "뒤에 있는 기능"은 [ 앞에 있는 기능이 배포될 때 함께 배포 ]됩니다.
 * 먼저 배포되어야 하는 순서대로 "작업의 진도가 적힌 정수 배열" progresses
 * "각 작업의 개발 속도"가 적힌 < 정수 배열 speeds가 > 주어질 때 
 * 각 배포마다 [ 몇 개의 기능이 배포 ]되는지를 return 하도록 solution 함수를 완성하세요.
 * 
 * [ 제한 사항 ]
      작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
      작업 진도는 100 미만의 자연수입니다.
      작업 속도는 100 이하의 자연수입니다.
      배포는 "하루에 한 번만" 할 수 있으며, "하루의 끝"에 이루어진다고 가정합니다. 
      예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 [ 배포는 2일 뒤 ]에 이루어집니다.
   progresses	              speeds	        return
   [93, 30, 55]	             [1, 30, 5]	        [2, 1]
   [95, 90, 99, 99, 80, 99]	 [1, 1, 1, 1, 1, 1]	[1, 3, 2]
   
   ( 문제 풀이 )
   1. 각 progresses 원소별 작업 후 배포 일 수를 success라는 ArrayList에 추가
   2. 첫 번째 작업 후 배포일 수를 꺼내온 뒤, 두 번째 작업 후 배포일 수와 비교하여 
           만약 첫 번째 작업 후 배포일 수가 < 두 번째 작업 후 배포일 수 > 보다 크거나 같을경우
           첫 번째 작업은 아직기능이 완성된 상태가 아니기 때문에 두 번째 작업은 첫 번째 작업과 같이 배포된다.
           따라서 기능 배포의 수 count 변수를 증가한다.
   3. 만약 그 반대라면 현재 까지 누적된 "배포의 수를" answer 이라는 ArrayList에 추가하고 
           배포의 수는 1로 초기화, 다음 몇일 뒤 배포 가능한지를 진행 중의 작업으로 셋팅해 준다. 
           그 후 2번 ~3번 과정을 반복 한 뒤 남은 배포에대한 몇개의 기능을 마저 answer 이라는 ArrayList에 추가
      
 */
public class Lv2_기능개발 {
	public static void main(String[] args) {
		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };
		System.out.println(solution(progresses, speeds)); // 출력 [ 2, 1]
	}
	
	public static ArrayList<Integer> solution(int[] progresses, int[] speeds) {
		// 각 작업이 몇일 뒤 배포 가능한지 저장받을 리스트
		ArrayList<Integer> sucess = new ArrayList<Integer>();
		for (int i = 0; i < progresses.length; i++) {
			int progress = 100 - progresses[i]; // 현재 남은 진행 작업률 저장.
			int speed = speeds[i];              // 하루 작업 속도 저장.
			int day = 0;                        // 몇일 뒤 배포 가능 한지를 저장 받을 변수.
			int speed_temp = 0;                 // 하루 작업 속도에 의해 진행한 작업량
			// 남은 진행 작업율 보다 < 하루 작업 속도에 의해 진행한 작업량 >이 "작을 경우" 배포일 수 증가.
			while (speed_temp < progress) {
				speed_temp = speed * day;
				day++;
			}
			sucess.add(day);
		}

		int temp = sucess.get(0); // 첫 번째 작업에 대한 배포 일 수를 꺼내옴.
		// 몇 개의 기능이 배포되는지를 담는 ArrayList
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int count = 1; // 배포 수
		// 두 번째 작업일 수부터 비교하며
		for (int i = 1; i < sucess.size(); i++) {
			int next = sucess.get(i);
			// 현재 작업에 대한 배포 일 수가 다음 작업의 배포 일 수 보다 크거나 같을 경우
			// 배포는 하루에 한 번 할 수 있고, 만약, 첫 번째 기능이 아직 완성된 상태가 아니여서
			// 다음 작업이 더 빨리 끝날지라도, 첫 번째 기능이 다 개발이 되고 해당 배포일이 되었을 때 배포가 됩니다.
			if (temp >= next) count++;
			else { // 현재 작업에 대한 배포 일 수 보다, 다음 작업의 배포 일수가 < 더 클 경우 >
				answer.add(count); // 현재 까지 누적된 배포 수를 결과 ArrayList에 추가.
				count = 1;
				temp = next;
			}
		}
		answer.add(count);// for문이 끝나고 나서 현재 까지 누적된 day결과 ArrayList에 추가.
		return answer;
	}
}