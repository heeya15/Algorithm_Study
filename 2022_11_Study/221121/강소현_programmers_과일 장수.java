import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        Arrays.sort(score);
        
        List<Integer> list = Arrays.stream(score).boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    

        for(int i = 1; i < (score.length / m) + 1; i++) {
            answer += list.get(m * i - 1) * m;
        }
        
        return answer;
    }
}