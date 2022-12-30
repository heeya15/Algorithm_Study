import java.util.*;

class Solution {
    
    public int solution(int distance, int[][] scope, int[][] times) {
        
        boolean[] flag = new boolean[distance + 1];
        
        for(int i = 0; i < scope.length; i++) {
            Arrays.sort(scope[i]); // 정렬
            
            for(int j = scope[i][0]; j <= scope[i][1]; j++) {
                
                int work = times[i][0]; // 근무시간
                int rest = times[i][1]; // 휴식시간
                
                if(j % (work + rest) == 0) continue;
                else if(j % (work + rest) <= work){
                    flag[j] = true;
                }
            }
        }
         
        int answer = distance;
        for(int i = 1; i <= distance; i++) {
            if(flag[i]) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}