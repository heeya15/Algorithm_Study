import java.util.*;

class Solution {
    
    public String[] solution(String[] record) {
        
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        
        for(String str : record) {
            StringTokenizer st = new StringTokenizer(str);

            String input = st.nextToken();
            String id = st.nextToken();
            String nickname = "";
            
            // 채팅방에서 나간 유저는 닉네임이 주어지지 않는다.
            // Enter, Change 상태일 경우 id랑 닉네임 담으면 된다.
            if(!input.equals("Leave")) {
                nickname = st.nextToken();
                
                map.put(id, nickname);
            }
        }
        
        for(String str : record) {
            StringTokenizer st = new StringTokenizer(str);

            String input = st.nextToken();
            String id = st.nextToken();
            
            if(input.equals("Enter")) {
                list.add(map.get(id) + "님이 들어왔습니다.");
            }else if(input.equals("Leave")) {
                list.add(map.get(id) + "님이 나갔습니다.");
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}