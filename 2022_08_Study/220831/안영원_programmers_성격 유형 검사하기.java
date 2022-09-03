import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        // 기본 세팅
        Map<Character, Integer> map = new HashMap<>();
        char[] personality = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        for (char temp : personality) map.put(temp, 0);
        
        // 설문 조사 중....
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        for (int i = 0; i < survey.length; i++) {
            char left = survey[i].charAt(0);
            char right = survey[i].charAt(1);
            
            if (choices[i] < 4) {
                int curScore = map.get(left);
                map.put(left, curScore + score[choices[i]]);
            } else if (choices[i] > 4) {
                int curScore = map.get(right);
                map.put(right, curScore + score[choices[i]]);
            }
        }
        
        // 결과 수집 중..
        String answer = "";
        
        if (map.get('R') >= map.get('T')) answer += "R";
        else answer += "T";
        
        if (map.get('C') >= map.get('F')) answer += "C";
        else answer += "F";
        
        if (map.get('J') >= map.get('M')) answer += "J";
        else answer += "M";
        
        if (map.get('A') >= map.get('N')) answer += "A";
        else answer += "N";
        
        return answer;
    }
}