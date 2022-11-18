import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        // t * m까지 숫자를 모두 n진법으로 변환해서 나열
        for(int i = 0; sb.length() <= t * m; i++) {
            // i를 n진법으로 변환시켜주는 메서드
            sb.append(Integer.toString(i, n));
        }
        
        // 나열된 것 중 튜브가 외칠 숫자를 찾아서 출력
        for(int i = p - 1; answer.length() < t; i += m) {
            answer.append(sb.charAt(i));
        }
        
        return answer.toString().toUpperCase();
    }
}
