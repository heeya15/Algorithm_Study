import java.util.*;

class Solution {
    
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> id_map = new HashMap<>(); // <유저가 신고한 ID, 신고된 횟수 저장>
        HashMap<String, HashSet<String>> report_map = new HashMap<>(); // <유저가 신고한 ID, 유저 ID>
        
        int[] answer = new int[id_list.length];
        
        // 초기화
        for(String id : id_list) {
            id_map.put(id, 0); // 신고된 횟수 초기화
            report_map.put(id, new HashSet<>()); // 유저가 신고한 ID, 유저 ID 초기화
        }
        
        // 신고 받기
        for(String r : report) {
            StringTokenizer st = new StringTokenizer(r, " ");
            String m1 = st.nextToken();
            String m2 = st.nextToken();
            
            report_map.get(m2).add(m1);
        }
        
    
        // 정지된 아이디 메일 처리
        for(String r : report_map.keySet()) {
            
            HashSet<String> report_set = report_map.get(r);
            
            // 신고 된 횟수가 k 이상일 때
            if(report_set.size() >= k) {
                for(String user : report_set) {
                    // 신고 횟수 저장
                    id_map.put(user, id_map.get(user) + 1);
                }
            }
        }        
    
        // 결과 
        for(int i = 0; i < id_list.length; i++) {
            answer[i] = id_map.get(id_list[i]);
        }
        
        return answer;
    }
}