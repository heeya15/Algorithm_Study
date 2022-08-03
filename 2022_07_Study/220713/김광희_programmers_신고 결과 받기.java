package Programmers;

import java.util.*;

public class Lv1_신고결과받기 {
	/**
	 * 2022 KAKAO BLIND RECRUITMENT - Lv1_신고 결과 받기 ( 구현 문제 )
	 * ( 문제 풀이 ) 
	 * 1. "2가지 HashMap을 이용"하여 하나는 유저id index 용도, 또 다른 하나는 각 유저별 자신을 신고한 유저를 저장하기 위해 사용
	 * 2. 2가지중 하나의 HashMap의 Value를 set으로 사용
	 *    -> 문제에서 동일한 유저에 대한 신고 횟수는 1회로 처리 지문을 보고 -> Value를 set으로 적용할 생각.
	 * 3. 신고 기록을 한다. -> 신고자 id에 누가 신고했는지를 기록
	 * 4. 유저별 자신을 "신고한 사람이 k번 이상" 이라면 메일을 보내줌
	 *    ex) neo와 frodo의 신고한 사람이 k명 이상이므로, 해당하는 유저id HashMap의 index 순서에 맞는 answer값을 증가시켜준다.
	 *        answer[map.get(name)]++;
	 **/
	public static void main(String[] args) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo",
				            "muzi neo", "apeach muzi" };
		System.out.println(Arrays.toString(solution(id_list, report, 2)));// 답 : [2, 1, 1, 0]
		System.out.println(Arrays.toString(solution2(id_list, report, 2)));// 답 : [2, 1, 1, 0]
	}
    /**   2 번째 방법 -> answer의 인덱스 용도로 사용할 userid_idxMap HashMap을 사용하여 코드 리펙토링  **/
	public static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		Map<String, Integer> userid_idxMap = new HashMap<>(); // 이용자 id index 용도 map
		// key : 신고 당한 사람, value : key의 대상을 신고한 사람
		Map<String, HashSet<String>> map = new HashMap<>(); // 각 유저별 자신을 신고한 유저
		for (int i = 0; i < id_list.length; i++) {
			map.put(id_list[i], new HashSet<String>());
			userid_idxMap.put(id_list[i], i);
		}

		for (int i = 0; i < report.length; i++) {
			String[] temp = report[i].split(" ");
			// ex) muzi frodo --> muzi = 유저, frodo = muzi가 신고한 id
			String userid = temp[0]; // 유저 id
			String singo = temp[1]; // 유저가 신고한 id
			map.get(singo).add(userid); // 신고한 id에 "누가 신고했는지"를 저장함.
		}

		for (int i = 0; i < id_list.length; i++) {
			HashSet<String> send = map.get(id_list[i]);
			if (send.size() >= k) { // 유저별 자신을 신고한 사람이 "k번 이상" 이라면 메일을 보내줌
				for (String name : send) answer[userid_idxMap.get(name)]++;
			}
		}
		return answer;
	}

	/****** 1 번째 방법 ********/
	public static int[] solution2(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		// key : user_id, value: 신고 메일 개수
		HashMap<String, Integer> mail_count = new HashMap<>();
		// key : 신고 당한 사람, value : key의 대상을 신고한 사람
		HashMap<String, HashSet<String>> map = new HashMap<>();
		for (int i = 0; i < id_list.length; i++) {
			mail_count.put(id_list[i], 0);
			map.put(id_list[i], new HashSet<String>());
		}

		for (int i = 0; i < report.length; i++) {
			String[] temp = report[i].split(" ");
			String userid = temp[0]; // 유저 id
			String singo = temp[1]; // 유저가 신고한 id
			map.get(singo).add(userid); // 신고한 id에 "누가 신고했는지"를 저장함.
		}
		for (int i = 0; i < id_list.length; i++) {
			HashSet<String> h = map.get(id_list[i]);
			if (h.size() >= k) {
				for (String name : h) {
					mail_count.put(name, mail_count.get(name) + 1);
				}
			}
		}
		for (int i = 0; i < id_list.length; i++) {
			answer[i] = mail_count.get(id_list[i]);
		}
		return answer;
	}
}