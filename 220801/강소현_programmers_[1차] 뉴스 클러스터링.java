import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String str1, String str2) {
        
        // 집합 만들기
        ArrayList<String> set1 = new ArrayList<>();
        ArrayList<String> set2 = new ArrayList<>();
        
        // 대문자로 치환
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        check(set1, str1);
        check(set2, str2);

        // list가 비어있으면 "65536" 반환
        if(set1.isEmpty() && set2.isEmpty()) return 65536;
        
        // 교집합
        int intersection = 0;
        for(int i = 0; i < set1.size(); i++) {
            for(int j = 0; j < set2.size(); j++) {
                
                String s1 = set1.get(i);
                String s2 = set2.get(j);
                
                if(s1.equals(s2)){
                    set2.remove(s1); 
                    intersection++;
                    break;
                }
            }
        }

        // 합집합
        double union = set1.size() + set2.size();

        return (int) (intersection / union * 65536);
    }
    
    static void check(ArrayList<String> arr, String str) {
        String result = "";
        String pattern = "^[A-Z]*$"; // 정규표현식 사용
        
        for(int i = 0; i < str.length() - 1; i++) {
            // 입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만듦.
            result = str.substring(i, i + 2);
            
            // 영문만 있을 때
            if(Pattern.matches(pattern, result)) {
                arr.add(result); // 리스트에 넣음.
            }
        }
    }
}