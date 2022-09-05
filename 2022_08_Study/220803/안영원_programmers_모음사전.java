class Solution {
    public int solution(String word) {
        int answer = word.length();
        
        String rule = "AEIOU";
        int[] count = {781, 156, 31, 6, 1};
        
        for (int i = 0; i < word.length(); i++) {
            int position = rule.indexOf(word.charAt(i));
            answer += count[i] * position;
        }
        return answer;
    }
}