import java.util.*;

class Solution {
    static boolean[] visited;
    static String[][] relation;
    static Set<String> candi = new HashSet<>();
    
    public int solution(String[][] relation) {
        this.relation = relation;
        
        // 모든 경우의 수인 부분집합 구하기
        for (int i = 0; i < relation[0].length; i++) {
            visited = new boolean[relation[0].length];
            dfs(0, i + 1, 0);
        }        
        int answer = candi.size();
        return answer;
    }
    
    static void dfs(int start, int end, int depth) {
        if (depth >= end) {
            List<Integer> list = new ArrayList<>();
            
            // 현재 key 상황을 확인
            String key = "";
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    key += String.valueOf(i);
                    list.add(i);
                }
            }
            
            // 이미 구해진 후보키가 포함되어 최소성이 만족안되는지 확인
            if (candi.containsAll(key)) return;
            
            // 선정된 후보키가 유일성을 만족하는지 확인
            Set<String> set = new HashSet<>();
            for (int i = 0; i < relation.length; i++) {
                String s = "";
                for (Integer j : list) s += relation[i][j];
                
                if (set.contains(s)) return;
                else set.add(s);
            }
            
            // 모두 통과시 후보키 추가
            candi.add(key);
        }
        
        
        for (int i = start; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, end, depth + 1);
                visited[i] = false;
            }
        }
    }
}