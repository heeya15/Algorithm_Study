class Solution {
    public int[] solution(String s) {

        int[] answer = new int[s.length()];
        
        // 맨 첫 글자는 자신의 앞에 같은 글자가 없음
        answer[0] = -1; 
        
        for (int i = 1; i < s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    answer[i] = i - j;
                    break;
                } else {
                    answer[i] = -1;
                }  
            }
        }

        return answer;
        
    }
}