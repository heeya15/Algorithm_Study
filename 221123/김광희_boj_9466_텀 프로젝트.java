package BaekJoon_self;
import java.io.*;
import java.util.*;
/**
 * ( 문제 이해하기 위해 적어봄 )
 * 순환이 발생하면 탐색을 종료한다. 
 * ex) 4 -> 7 -> 6 -> 4 
 * 또한 1번학생 기준 탐색 1->3->3 을 탐색했는대 
 * 2번 학생을 기준으로 원래는 2->1->3->3 탐색을 해야하지만
 * 2->1 까지만 가도, 이전에 1->3->3 을 이미 팀인지 아닌지 체크 했으니 < 탐색할 필요 없다. > -> 시간을 줄일 수 있음
 * 
 * ( 문제 풀이 )
 * 1. 1번 학생 번호 부터~ n번 학생 까지 dfs 탐색 시작
 * 2. 현재 학생 번호는 방문 체크 후, 해당 학생 번호가 가르키는 < 다음 학생 번호를 방문 >하지 않았다면 해당 학생 번호로 dfs 탐색
 *    해당 학생 번호를 방문하고, 해당 학생 번호로 "해당 학생이 선택하는 사람으로 계속 탐색"을 하지 않았다면
 *    팀 편성 완료 인원 증가
 *     -> 여기서 또, 현재 학생 번호가 다음 학생 번호와 같지않을때 까지 팀 편성 완료 인원 증가시켜 준다
 *        이유 : cycle (순환) 이 될때까지 반복 -> 서로를 가르키고 있는게 하나의 팀이 되기 때문.
 * 
 * [ 결론 ]
 * 1. 혼자 팀을 선택한 사람을 선택한 학생은 팀을 이룰 수 없다.
 * 2. 팀을 이루기 위해선 cycle(순환)이 되어야 한다.
 */
public class Main_G3_9466_텀프로젝트 {
	static int [] student_number;
	static boolean [] visited, finishVisited;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine()); // 학생의 수
            student_number = new int[n+1];
            visited = new boolean[n+1];       // 해당 학생 번호 방문 여부 
            finishVisited = new boolean[n+1]; // 해당 학생 번호 탐색 종료 여부
            count = 0;
            
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            // 학생들의 번호가 주어진다.
            for(int j=1; j<= n; j++) student_number[j] = Integer.parseInt(st.nextToken());
               
            for(int k=1; k<= n; k++){
                dfs(k); // 해당 학생번호 기준으로 dfs 탐색
            }
            sb.append(n-count).append("\n");// 전체 학생수 - 팀을 이룬 학생 수 => 속하지 못한 학생 수
		}
	    System.out.println(sb.toString()); 	
	}
	public static void dfs(int node) {	
		if(visited[node]) return; // 가지치기 ( 이미 현재 노드가 방문 했다면 볼 필요 x )
		visited[node] = true; 	         // 현재 학생번호 방문 처리
		int next = student_number[node]; // 다음 학생 번호 저장
		if(!visited[next]) dfs(next);    // 다음 학생 번호를 방문하지 않았다면 해당 학생 번호로 dfs 탐색
		else { // 다음 학생 번호를 방문 했었고 
			if(!finishVisited[next]) { // 해당 학생 번호로 탐색을 하지 않았을 경우 
				count++;
				while(next != node) { // cycle (순환) 이 될때까지 반복 -> 서로를 가르키고 있는게 하나의 팀이 되기 때문.
					count++;
					next = student_number[next];
				}
			}
		}
		// 더 이상 탐색할 필요 x
		finishVisited[node] = true; 
	}
}
