package Programmers;
import java.util.*;
/**
	( 문제 풀이 )
	1. 채팅방에 들어온 경우와, 나간 경우를 ArrayList의 String 배열에 넣어준다.
	2. HashMap을 활용하여 key는 user_id, value는 nickname으로 사용하였다. 
	   ex) key : uid4567  value : Prodo 가 HashMap에 값이 있는 상태에서 
	       < 동일한 key : uid4567 가 > 다른 닉네임 "Ryan으로 들어올 경우" 해당 key에 해당하는 "value가 Ryan으로 변경" 됨
	             즉 {key : uid4567,  value : Ryan}
	3. 들어오거나, 나간 경우의 기록이 담긴 문자열 배열을 list에서 꺼내옴         
	4. 해당 user_id를 통해 [ 닉네임을 ] HashMap 에서 찾음 
	   -> 최종적으로 방을 개설한 사람이 보게 되는 메시지 형태로 출력해 주기 위해서.
	5. [ 최종적으로 들어온 기록 ]이라면 nickname + "님이 들어왔습니다." , [ 나간 기록 ]이라면  nickname + "님이 나갔습니다."
**/
public class Lv2_오픈채팅방 {
	public static void main(String[] args) {
		String [] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo",
				            "Leave uid1234","Enter uid1234 Prodo",
				            "Change uid4567 Ryan"};
		System.out.println(Arrays.toString(solution(record)));
	}

	public static String[] solution(String[] record) {
        ArrayList<String[]> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>(); // user_id: key, nickname : value
        for(int i =0; i < record.length; i++){
            String line = record[i];
            String [] temp = line.split(" "); // 문자열에서 공백단위로 잘라서 배열로 만들어줌.
            if (temp[0].equals("Enter")) {
				list.add(temp);
				map.put(temp[1], temp[2]); 
			} else if (temp[0].equals("Change")) {
				map.put(temp[1], temp[2]); // 기존 id의 nickname을 바꿔줌
			} else { // Leave
				list.add(temp);
			}
        }
       
        String [] answer = new String[list.size()];
        for(int i =0; i < list.size(); i++){
            String [] temp = list.get(i);       // 들어오거나, 나간 경우의 기록이 담긴 문자열 배열을 list에서 꺼내옴
            String nickName = map.get(temp[1]); // 해당 id를 통해 닉네임을 hash 에서 찾음
            answer[i] = temp[0].equals("Enter") ? nickName + "님이 들어왔습니다." : nickName + "님이 나갔습니다.";   
        }
        return answer;
    }
}