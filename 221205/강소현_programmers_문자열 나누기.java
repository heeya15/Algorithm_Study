class Solution {
    public int solution(String s) {
        int answer = 0;
    
        int first = 0, second = 0;
        boolean flag = false;

        char ch = ' ';
        for(int i = 0; i < s.length(); i++) {
            
            // flag 체크
            // false이면 비교 문자열 초기화
            if(!flag) {
                flag = true;
                ch = s.charAt(i);
            }
        
            // 문자열이 같으면 first 카운트
            // 문자열이 다르면 second 카운트
            if(ch == s.charAt(i)) first++;
            else second++;
            
            // 카운트 개수가 같으면 분해
            if(first == second) {
                answer++;
                
                // 초기화
                first = 0;
                second = 0;
                flag = false;
                ch = ' ';
            }
            
            // 분해했을 때 문자열 하나가 남았을 경우
            if(flag && i == s.length() - 1) {
                answer++;
            }
        }
        return answer;
    }
}