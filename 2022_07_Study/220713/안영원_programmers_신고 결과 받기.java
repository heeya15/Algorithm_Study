import java.util.*;

class Solution {
    static String[] id_list;
    static int[] answer;
    static int[] result;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        answer = new int[id_list.length];
        this.id_list = id_list;
        
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < report.length; i++) {
            if (!set.contains(report[i])) {
                set.add(report[i]);
                String[] temp = report[i].split(" ");
                addSinGo(temp[1]);
            }
        }
        
        // 정지 된 사람
        ArrayList<String> stop = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] >= k) stop.add(id_list[i]);
        }
        
        result = new int[id_list.length];
        // 메일 받는 사람
        for (String s : set) {
            String[] temp = s.split(" ");
            for (String stopMan : stop) {
                if (temp[1].equals(stopMan)) {
                    getMail(temp[0]);
                }
            }
        }
        return result;
    }
    
    static void addSinGo(String someone) {
        for (int i = 0; i < id_list.length; i++) {
            if (id_list[i].equals(someone)) {
                answer[i]++;
                return;
            }
        }
    }
    
    static void getMail(String someone) {
        for (int i = 0; i < id_list.length; i++) {
            if (id_list[i].equals(someone)) {
                result[i]++;
                return;
            }
        }
    }
}