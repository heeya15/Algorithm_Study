import java.util.*;
class Solution {
    
    public int solution(String skill, String[] skill_trees) {
        
        List<String> list = new ArrayList<>();
        int answer = 0;
        
        for(String value : skill_trees) {
            
            String[] target = value.split("");
            for(int j = 0; j < target.length; j++) { 
                // 스킬 트리를 나타내는 원소가 포함되지 않았을 경우
                if(!skill.contains(target[j])) {
                    value = value.replace(target[j], ""); // 제거
                }
            }
            list.add(value); 
        }
        
        for(String str : list) {
            for(int i = 0; i < skill.length() + 1; i++) {
                // skill 문자열과 정제한 skill_trees 문자열 순서대로 비교
                if(skill.substring(0, i).equals(str)) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}