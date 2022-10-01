package Programmers;
import java.util.*;
/**
유일하게 식별
- 유일한 "학번"을 가지고
- "이름"은 후보 키가 될 수 없다. 
  그러나, 만약 ["이름", "전공"]을 함께 사용한다면 릴레이션의 모든 튜플을 유일하게 식별 가능
 ( 문제 풀이 ) 
 1. 컬럼에 대한 중복 조합을 활용하여 1개의 키 부터 ~컬럼 수 까지의 경우의 수를 구한다.
 2. 해당 경우의 수 중 "후보키"로 사용될 컬럼 인덱스를 구한 뒤 해당 컬럼에서의 유일성을 판단하기 위해 HashSet을 활용
 3. 유일성에 만족한다면 -> 최소성을 검증한다.
 4. 최소성 까지 검증이 된다면 후보키 리스트(candidateKey)에 해당 "후보키"로 사용될 컬럼 인덱스 묶음 문자열을 저장.
 **/
public class Lv2_후보키 {
    static List<String> candidateKey = new ArrayList<>();
    static boolean[] visited;
    static int col;
	public static void main(String[] args) {
		String [][] relation = {{"100","ryan","music","2"},
		                        {"200","apeach","math","2"},
		                        {"300","tube","computer","3"},
		                        {"400","con","computer","4"},
		                        {"500","muzi","music","3"},
		                        {"600","apeach","music","2"}};
		System.out.println(solution(relation)); // 답 :2
	}
	// relation : 릴레이션을 나타내는 문자열 배열
    public static int solution(String[][] relation) {
        col =  relation[0].length; // 열
        // 1개에서 열 크기만큼의 조합을 구하는 부분.
        for (int i = 0; i < col; i++) {
            visited = new boolean[col];
            comb( 0, 0, i + 1, relation);
        }
        return candidateKey.size();
    }
	public static void comb( int start, int depth, int key_end, String[][] relation) {
        if (depth == key_end) {
        	ArrayList<Integer> list = new ArrayList<>(); // 후보키의 경우의 수로 사용될 index를 저장.
            String candidate_key_column_index = ""; // "후보키"로 사용될 컬럼 인덱스 묶음을 저장하는 변수
            for (int i = 0; i < col; i++) {
                if (visited[i]) {
                	candidate_key_column_index += String.valueOf(i);
                    list.add(i);
                }
            }
            // 중복된 데이터를 거르기 위해 set을 활용  -> 유일성을 판별하기 위해
            HashSet<String> set = new HashSet<>();
             
            for (int i = 0; i < relation.length; i++) {  // 행 수만큼 반복
                String s = "";
                for (int key_index : list) s += relation[i][key_index];
                set.add(s);
            }
            // 유일성에 대한 성질을 만족하지 못한다면 return;
            if(set.size() < relation.length) return;
            /**
             * 예를들어 후보키에 해당 컬럼 인덱스 "0" 하나가 들어가 있다고 가정.
             * 후보키 길이는 : 1
             * 여기서 현재 key로 사용될 수 있는 인덱스가 "12"묶음으로 들어왔다고 가정하자
             * 최소성을 판별하기 위해
             * 1번 인덱스가 기존 후보키 안에 포함되어 있냐? x
             * 2번 인덱스가 기존 후보키 안에 포함되어 있냐? x
             * 따라서 후보키 안에 포함되어 있는 인덱스 수가 0개 임으로 최소성을 만족한다.
             * 후보키 길이만큼  반복문이 끝나게 되면 -> 후보키 리스트에 해당 key로 사용될 컬럼 인덱스 문자를 추가시켜준다.
             */
            for (String s : candidateKey) { 
                int count = 0;
                for(int i = 0; i < candidate_key_column_index.length(); i++){
                    String subS = String.valueOf(candidate_key_column_index.charAt(i));
                    if(s.contains(subS)) count++;                  
                }
                if (count == s.length()) return;
            }
            // 후보키로 사용될 데이터베이스 테이블 안에 해당 컬럼의 인덱스를 추가
            candidateKey.add(candidate_key_column_index);
            return;
        }
        // 열 크기 만큼 반복.
        for (int i = start; i < col; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            comb(i, depth + 1, key_end, relation);
            visited[i] = false;
        }
    }
}