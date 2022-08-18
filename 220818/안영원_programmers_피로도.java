import java.util.*;

class Solution {
    static int[] result;
    static boolean[] visited;
    static int[][] dungeons;
    static int k;
    static int max = -1;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        this.dungeons = dungeons;
        this.k = k;
        
        int length = dungeons.length;
        result = new int[length];
        visited = new boolean[length];
        
        permu(0, length);
        answer = max;
        
        return answer;
    }
    
    static void permu(int depth, int end) {
        if (depth >= end) {
            // System.out.println(Arrays.toString(result));
            max = Math.max(max, goDungeons());
            return;
        }
        
        for (int i = 0; i < end; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = i;
                permu(depth + 1, end);
                visited[i] = false;
            }
        }
    }
    
    static int goDungeons() {
        int count = 0;
        int startPoint = k;
        for (int i = 0; i < result.length; i++) {
            if (dungeons[result[i]][0] <= startPoint) {
                startPoint -= dungeons[result[i]][1];
                count++;
            } else break;
        }
        return count;
    }
}