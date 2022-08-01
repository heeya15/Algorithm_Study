import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        double answer = 0;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        ArrayList<String> one = new ArrayList<>();
        for (int i = 0; i < str1.length() - 1; i++) {
            char a = str1.charAt(i);
            char b = str1.charAt(i + 1);
            
            if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
                one.add(a + "" + b);
            }
        }
        
        ArrayList<String> two = new ArrayList<>();
        for (int i = 0; i < str2.length() - 1; i++) {
            char a = str2.charAt(i);
            char b = str2.charAt(i + 1);
            
            if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z') {
                two.add(a + "" + b);
            }
        }
        
        // 합집합, 교집합 찾기
        ArrayList<String> gue = new ArrayList<>();
        ArrayList<String> hap = new ArrayList<>();
        
        for (String s : one) {
            if (two.remove(s)) gue.add(s);
            hap.add(s);
        }
        
        for (String s : two) hap.add(s);
        
        if (hap.size() == 0) return 65536;
        else answer = (double) gue.size() / (double) hap.size();
        
        return (int) (answer * 65536);
    }
}