import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<>();
        int[] reverseScore = new int[score.length];
        
        Arrays.sort(score);
        
        for(int s : score) {
            list.add(s);
        }
        
        Collections.reverse(list);
        
        for(int i = 0; i < score.length; i++) {
            reverseScore[i] = list.get(i);
        }
        
        for(int i = 1; i < (score.length / m) + 1; i++) {
            answer += reverseScore[m * i - 1] * m;
        }
        
        return answer;
    }
}