package BaekJoon_self;

import java.util.*;
import java.io.*;

/**
 * ( 문제 설명 )
 * 이런 문제를 해결하기 위해서, 다음과 같이 출석부를 관리하기로 결심했다.
 * 개강총회를 시작하기 전에, < 학회원의 입장 확인 여부 >를 확인한다. 
 * - 학회원의 입장 여부는 개강총회가 < 시작한 시간 이전 >에 대화를 한 적이 있는 학회원의 닉네임을 "보고 체크"한다. 
 * - 개강총회를 < 시작하자마자  > 채팅 기록을 남긴 학회원도 제 시간에 입장이 확인된 것으로 간주한다.
 * - 개강총회를 끝내고 나서, 스트리밍을 끝낼 때까지 학회원의 "퇴장 확인 여부"를 확인한다. 
 *   학회원의 퇴장 여부는 개강총회가 끝나고 스트리밍이 끝날 때까지 대화를 한 적이 있는 학회원의 닉네임을 보고 체크한다. 
 *   개강총회가 끝나자마자 채팅 기록을 남겼거나, 개강총회 스트리밍이 끝나자마자 채팅 기록을 남긴 학회원도 제 시간에 퇴장이 확인된 것으로 간주한다.  
 *   
 * ( 문제풀이 )
 * 1. 문제 설명 대로 개강총회가 < 시작한 시간 이전 및 시작하자마자> 대화를 한 적이 있는 학회원이 있다면 start라는 HashSet에 학회원의 닉네임을 저장
 * 2. 개강총회 끝낸 시간 보단 크거나 같고, < 개강총회 스트리밍 끝낸 시간 > 보단 작거나 같을 경우 해당 학회원은 퇴장이 학인된 것으로 간주하기 위해 end 라는 HashSet에 저장
 * 3. 시작 시간에 출첵하였고, 퇴장 확인 여부 채팅 기록이 있다면 결과 수 answer 증가
 */
public class Main_S2_19583_싸이버개강총회 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		String S = st.nextToken(); // 개강총회 시작 시간
		String E = st.nextToken(); // 개강총회 끝낸 시간
		String Q = st.nextToken(); // 개강총회 스트리밍 끝낸 시간
		
		int answer=0;              // 입장부터 퇴장까지 모두 확인된 학회원 수 저장 받을 변수
		// 스트리밍 영상 채팅 기록들이 시간 순으로 주어짐  ( 시간 ) (학회원 닉네임) 형태	
		HashSet<String> start = new HashSet<>();// 시작할 때(시작한 시간 이전 포함) 출석체크
		HashSet<String> end = new HashSet<>();  // 끝날 때 출첵
		String str;
		while((str = br.readLine()) != null) {
            String[] arr = str.split(" "); // 입력받은 문자열을 공백 단위로 자른다.
            String time = arr[0]; // 시간
            String nickname = arr[1]; // 학회원 닉네임
            
            // 개강총회 시작 시간 전과 시작하자마자 채팅 기록을 남겼다면
            if(S.compareTo(time) >= 0) start.add(nickname);
            // 개강총회 끝낸 시간 보단 크거나 같고, < 개강총회 스트리밍 끝낸 시간 > 보단 작거나 같을 경우 
            else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) end.add(nickname);  
        }
		// 시작 시간에 출첵하였고, 퇴장 확인 여부 채팅 기록이 있다면 결과 수 증가
		for(String temp : end) {
			if(start.contains(temp)) answer++;
		}
		System.out.println(answer);
	}
}