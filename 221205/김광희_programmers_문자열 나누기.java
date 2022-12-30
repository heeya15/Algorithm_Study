package Programmers;

/**
 * ( 문제 풀이 )
 * 1. 첫 글자 처음에 뽑고, 첫 글자는 무조건 -> 현재 단어와 같다라는 의미로 same_index 증가
 * 2. now_word와 현재 문자 단어(s.charAt(i)) 가 같다면 same_index 증가
 *    만약 같지 않다면 different_index 증가
 *    -> 처음으로 두 횟수가 같아지는 순간 멈추고 라는 지문에 의해 
 *    -> same_index와 different_index가 같다면 분해여부 상태가 true로 초기화로 맨 처음 (1)번 단계로 이동 
 *       또한, 분해한 문자열 개수 증가
 * 3. (1), (2) 번 과정이 끝나고 index가 문자열 끝인대, 분해 해야 할 문자가 남아있다면 분해할 문자열 개수 answer 증가.
 */
public class Lv1_문자열나누기 {
	public static void main(String[] args) {
		System.out.println(solution("banana")); // 답 : 3
		System.out.println(solution("abracadabra")); // 답 : 6
	}

	public static int solution(String s) {
        int answer = 0;
        int same_index = 0;    // 문자열이 같으면
        int different_index =0;// 문자열이 다르면
        char now_word = ' ';
        boolean start = false;  // 분해 여부 상태
        for(int i = 0; i < s.length(); i++){
            if(start == false){  // 첫 글자 뽑음
                now_word = s.charAt(i);
                same_index++;   // 첫 글자는 무조건 일치해서 동일 인덱스 증가.
                start = true;  // start = true 했다고 해서 아래 else if 이기 때문에 아래 조건문 타지 않음. 
            }
            else if (start == true ){
                // 현재 단어와 < 현재 인덱스 글자와 > 같다면
                if(now_word == s.charAt(i)) same_index++;  // 동일 인덱스 수 증가.
                
                // 현재 단어와 < 현재 인덱스 글자와 > 다르다면
                else if(now_word != s.charAt(i))  different_index++; // 다른 인덱스 증가  
                
                if (same_index == different_index){ // 두 횟수가 같아지는 순간 멈춤
                    start = false;
                    answer++; // 지금까지 읽은 문자열을 분리 -> 즉, 분해한 문자열 개수 증가
                }
            }
            // index가 문자열 끝인대, 분해 해야 할 문자가 남아 있다면
            if(i == (s.length() -1)) {
                if(start == true) answer++;   
            }
        }
        return answer;
    }
}