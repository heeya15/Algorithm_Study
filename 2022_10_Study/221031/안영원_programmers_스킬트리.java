class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            boolean isCan = true; // 현재 스킬트리가 가능한지 확인
            int idx = 0; // 선행 스킬 번호
            
            for (int j = 0; j < skill_trees[i].length(); j++) {
                if (isCan) {
                    char cur = skill_trees[i].charAt(j); // 현재 스킬
                    if (checkInSkill(cur, skill)) { // 선행 스킬이 필요하다면
                        if (skill.charAt(idx) == cur) idx++; // 현재 선행 스킬 번호와 일치하면 다음 선행스킬로 넘김
                        else isCan = false; // 선행 스킬 없이 다음 스킬이 왔다면 안됨
                    }
                }
            }
            if (isCan) answer++;
        }
        
        return answer;
    }
    
    // 현재 스킬이 선행스킬이 필요한 스킬인지 확인
    static boolean checkInSkill(char cur, String skill) {
        for (int i = 0; i < skill.length(); i++) {
            char check = skill.charAt(i);
            if (check == cur) return true;
        }
        
        return false;
    }
}