import java.util.*;

class Solution {
    
    static String[] type = {"R", "T", "C", "F", "J", "M", "A", "N"};
    static int[] score = new int[8];
    static HashMap<String, Integer> map = new HashMap<>();
    
    static String answer = "";
    public String solution(String[] survey, int[] choices) {
        
        // 성격 유형 hashmap에 저장
        for(int i = 0; i < type.length; i++) {
            map.put(type[i], i);
        }
        
        for(int i = 0; i < survey.length; i++) {
            String a = survey[i].substring(0, 1); // 비동의
            String b = survey[i].substring(1, 2); // 동의
            int s = choices[i];
            
            checkScore(a, b, s); 
        }
        
        solve();

        return answer;
    }
    
    private static void checkScore(String a, String b, int s) {
        switch(s) {
            case 1 : score[map.get(a)] += 3; // 매우 비동의
                break;
            case 2 : score[map.get(a)] += 2; // 비동의
                break;
            case 3 : score[map.get(a)] += 1; // 약간 비동의
                break;
            case 4 : break; // 모르겠음
            case 5 : score[map.get(b)] += 1; // 약간 동의
                break;
            case 6 : score[map.get(b)] += 2; // 동의
                break;
            case 7 : score[map.get(b)] += 3; // 매우 동의
                break;
        }
    }
    
    private static void solve() {
        /* 
        1,2,3,4 지표 별로 점수가 높은 순으로 정렬
        ex) 
        1. 라이언형(R) 점수 < 튜브형(T) 점수 → 튜브형(T) 문자열 저장
        2. 라이언형(R) 점수 == 튜브형(T) 점수 → 알파벳 사전순으로 빠른 알파벳 문자열 저장
        3. 라이언형(R) 점수 > 튜브형(T) 점수 → 라이언형(R) 문자열 저장
        */
        for(int i = 0; i < type.length; i+=2) {
            if(score[i] < score[i + 1]) answer += type[i + 1];
            else if(score[i] == score[i + 1]) answer += type[i];
            else answer += type[i];
        }
    }
}