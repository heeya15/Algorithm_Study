class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int len = s.length();
        
        // 문자열 길이가 1이면 1
        if(len == 1) return 1;
        
        for(int i = 1; i < len / 2 + 1 ; i++){
            StringBuilder sb = new StringBuilder();
            
            int count = 0; // 반복 횟수
            String target = s.substring(0, i); // 0 ~ i까지 반복 문자열
            String str = ""; // 비교 문자열

            for(int j = 0; j <= len; j+=i) {
                str = s.substring(j, Math.min(i + j, len)); // 비교 문자열
                
                // 타켓 문자열과 비교 문자열이 같을 때
                if(str.equals(target)) count++; // 비교 횟수 카운트
                else { 
                    // 비교 횟수가 1보다 크면 횟수 저장
                    if(count > 1) sb.append(count);

                    sb.append(target);

                    target = str;
                    count = 1;
                }
            }
            // 비교 횟수가 1보다 크면 횟수 저장
            if(count > 1) sb.append(count);
            sb.append(target);
            
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}