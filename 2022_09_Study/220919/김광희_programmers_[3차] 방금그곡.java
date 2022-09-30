package Programmers;

/**
( 문제 설명 )
- 한 음악을 반복해서 재생할 때도 있어 네오가 기억하고 있는 멜로디는 음악 끝부분과 처음 부분이 이어서 재생된 멜로디일 수도 있다.
- 악보에 사용되는 음은 C, C#, D, D#, E, F, F#, G, G#, A, A#, B < 12개 >
- "각 음은" < 1분에 1개씩 재생 >
- 음악은 반드시 "처음부터 재생"되며 음악 길이보다 [ 재생된 시간이 길 때 ]는 음악이 끊김 없이 < 처음부터 반복 >해서 재생된다.
- 음악 길이보다 <재생된 시간이 짧을 때는> 처음부터 재생 시간만큼만 재생된다.
- 음악이 00:00를 넘겨서까지 재생되는 일은 없다.
- < 조건이 일치하는 음악이 여러 개 >일 때에는 라디오에서 [ 재생된 시간이 제일 긴 음악 제목을 반환 ]한다. 
- < 재생된 시간도 같을 경우 > [ 먼저 입력된 음악 제목을 반환 ]한다.
- 조건이 일치하는 음악이 없을 때에는 “(None)”을 반환한다.

( 문제 풀이 )
1. 먼저 네오가 기억한 멜로디를 담은 문자열에서 #이 붙은 문자를 "소문자"로 변환
2. 방송된 곡의 정보를 담고 있는 배열길이 만큼 반복을 한다
   - 여기서, ',' 를 기준으로 배열 원소들을 나눠주고, ':'를 이용하여 시작 시간, 끝 시간을 구해 전체 노래 재생 시간을 구함
3. 음악의 길이보다 "재생된 시간이 길 경우" -> 음악은 끊김 없이 처음부터 반복 해서 재생하여 "음악 정보"를 저장한다.
4. 음악 길이보다 <재생된 시간이 짧을 때는> 음악 재생시간 만큼 "음악 정보"를 저장해 준다.
5. 음악 정보에  네오가 기억한 멜로디가 포함되어 있는지 여부와, 재생된 시간까지 비교함으로 써 answer을 구함
6. 만약 최대 재생 시간 변수가 갱신되었다면 answer을 반환, 그렇지 않다면(일치하는 음악이 없다면) "(None)"을 반환
**/
public class Lv2_3차방금그곡 {
	static String[] rule = { "A#", "C#", "D#", "F#", "G#" };

	public static void main(String[] args) {
		String m = "ABCDEFG";
		String[] musicinfos = { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		System.out.println(solution(m, musicinfos));
	}
	 /**
	    m: 네오가 기억한 멜로디를 담은 문자열
	    musicinfos: 방송된 곡의 정보를 담고 있는 배열이 "매개변수로" 주어짐.
	                배열은 음악이 시작한 시각, 끝난 시각, 음악 제목, 악보 정보가 ','로 구분된 문자열로 형성
	 **/
	public static String solution(String m, String[] musicinfos) {
		String answer = "";
		// 조건이 일치하는 음악이 여러 개 일 때는 라디오에서 < 재생된 시간이 제일 긴 음악 제목을 반환 >하기 위해 사용하는 변수
		int max_playtime = 0;
		m = ConvertToLowercase(m); // 멜로디를 담은 문자열에서 #이 붙은 문자를 "소문자"로 변환

		for (int i = 0; i < musicinfos.length; i++) {
			String[] temp = musicinfos[i].split(",");
			String[] start_time = temp[0].split(":"); // 시작 시간
			String[] end_time = temp[1].split(":");   // 종료 시간
			String music_title = temp[2];             // 음악 제목
			String music_info = temp[3];         	  // 음악 정보
			
			// 시작 시간, 끝난 시간을 "분 단위"로 변환 하는 과정
			int start_min = (Integer.parseInt(start_time[0]) * 60 ) + Integer.parseInt(start_time[1]);
			int end_min = (Integer.parseInt(end_time[0]) * 60)  + Integer.parseInt(end_time[1]);
			int play_time = end_min - start_min; // 지속 시간
			
			music_info = ConvertToLowercase(music_info); // 악보 정보에 #이 붙어있는 문자를 소문자 알파벳으로 치환
			
			// 음악의 길이보다 "재생된 시간이 길 경우" -> 음악은 끊김 없이 처음부터 반복 해서 재생
			if(play_time > music_info.length()) {
				StringBuilder sb = new StringBuilder();
				/**
				 * 나눈 몫 만큼 악보 처음부터 반복해서 넣어준다
				 * 1번 테케 1번째 배열의 음악정보가 CDEFGAB 즉, 길이 7
				 * 하지만 재생 시간이 14분 임으로 1번째 배열의 음악정보가 2번 반복되게됨
				 * 따라서 CDEFGABCDEFGAB로 재생되었다.
				 * 만약 여기서 재생시간이 15인 경우 맨 뒤에 C가 더붙어야 한다.
				 * 그래서 재생 시간 % 음악 정보길이를 해줘서 처음 부터 나머지 의 값 만큼 잘라서 붙여준다. 
				**/
				for (int j = 0; j < play_time / music_info.length(); j++) sb.append(music_info);
				// 나머지 만큼 안복에서 잘라서 붙여줘야 [ 홀 수인 재생시간을 해결 가능. ]
				sb.append(music_info.substring(0,play_time % music_info.length()));
				music_info = sb.toString();
			}
			else { // 음악 길이보다 <재생된 시간이 짧을 때는>
				music_info = music_info.substring(0,play_time); // 처음부터 재생 시간만큼만 재생된다.
			}
			/** 
			 * 조건이 일치하는 음악이 여러개일 때 라디오에서 "재생된 시간이 제일 긴 음악 제목을 반환"해야 한다는 
			 * "또한 < 재생된 시간도 같을 경우 > [ 먼저 입력된 음악 제목을 반환 ]한다" 지문이 있다.
			 * 따라서, 음악 정보에  멜로디가 포함되어 있는지 여부와, 재생된 시간까지 비교함으로 써 위 지문을 해결 가능
			 * 예를들어 조건이 일치하는게 2가지가 있다, 하지만 재생된 시간도 같을경우 먼저 입력된 음악 제목을 반환하기 위해
			 * 처음 반복문에서 play_time에 먼저 음악 제목을 저장하고, 최고 시간을 갱신 
			 * 두번째로 조건도 똑같고 최고 시간과 같은 상태임으로 아래 "조건문을 탈 수가 없음" 
			 * 따라서, < 먼저 입력된 음악 제목만을 저장하며 반환 >을 할 수 있다.
			 */
			if(music_info.contains(m) && play_time > max_playtime) {
				max_playtime = play_time;
				answer = music_title;
			}
		}
		return max_playtime != 0 ? answer : "(None)";
	}

	public static String ConvertToLowercase(String EnglishLetters) {
		for (int i = 0; i < rule.length; i++) {
			switch (i) {
				case 0:
					EnglishLetters = EnglishLetters.replace(rule[i], "a");
					break;
				case 1:
					EnglishLetters = EnglishLetters.replace(rule[i], "c");
					break;
				case 2:
					EnglishLetters = EnglishLetters.replace(rule[i], "d");
					break;
				case 3:
					EnglishLetters = EnglishLetters.replace(rule[i], "f");
					break;
				case 4:
					EnglishLetters = EnglishLetters.replace(rule[i], "g");
					break;
			}
		}
		return EnglishLetters;
	}
}