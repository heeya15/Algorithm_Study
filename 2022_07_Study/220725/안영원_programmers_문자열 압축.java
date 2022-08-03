class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        // i개 단위로 자름
        for (int i = 1; i <= s.length() / 2; i++) { // 전체의 절반을 자를 수 있는게 최대
            String curWord = s.substring(0, i); // 비교할 현재 단어
            int cnt = 1;                        // 반복 횟수
            String restWord = "";               // 남은 단어 표시
            String result = "";                 // 현재 만들어진 단어 표시
            
            for (int j = i; j < s.length(); j+= i) {
                if (j + i > s.length()) {
                    // 체크가 불가능한 남은 단어들
                    restWord = s.substring(j);
                    continue;
                }
                
                // 만들어진 단어가 다음 단어와 같을 때(반복될 때)
                if (curWord.equals(s.substring(j, j + i))) {
                    cnt++;
                // 반복되지 않는다면
                } else {
                    result += curWord;
                    if (cnt != 1) { // 반복된게 있다면 숫자를 앞에 더해줌 
                                    // (굳이 반복되는 숫자 앞에 맞출 필요없음 길이만 보기 때문)
                        result = cnt + result;
                    }
                    // 현재 단어를 교체
                    curWord = s.substring(j, j + i);
                    cnt = 1;
                }
            }

            result += curWord + restWord;
            if (cnt != 1) {
                result = cnt + result;
            }
            //System.out.println(result);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}