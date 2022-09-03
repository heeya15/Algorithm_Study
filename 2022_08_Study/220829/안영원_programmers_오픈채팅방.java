import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // ID에 맞는 닉네임 찾기
        Map<String, String> map = new HashMap<>();
        for (String notice : record) {
            StringTokenizer st = new StringTokenizer(notice, " ");
            
            String status = st.nextToken();
            if (status.equals("Leave")) continue;
            
            String id = st.nextToken();
            String nick = st.nextToken();
            
            if (map.containsKey(id)) map.remove(id);
            map.put(id, nick);
        }
        
        // 해당 닉네임으로 채팅 로그 입력
        ArrayList<String> output = new ArrayList<>();
        for (String notice : record) {
            StringTokenizer st = new StringTokenizer(notice, " ");
            
            String status = st.nextToken();
            String id = st.nextToken();
            
            if (status.equals("Enter")) {
                output.add(map.get(id) + "님이 들어왔습니다.");
            } else if (status.equals("Leave")) {
                output.add(map.get(id) + "님이 나갔습니다.");
            }
        }
        
        // 제출을 위해 배열로 변경
        String[] answer = output.stream().toArray(String[]::new);
        return answer;
    }
}