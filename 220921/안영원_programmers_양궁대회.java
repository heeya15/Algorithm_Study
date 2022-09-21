import java.util.*;

class Solution {
    static boolean[] isWin;
    static int[] info;
    
    static boolean[] winRecord;
    static int max;
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        
        int[] answer = new int[11];
        isWin = new boolean[11]; // 1부터 10까지 중 이길 점수를 저장
        max = Integer.MIN_VALUE;
        dfs(n, 0);
        
        if (max == Integer.MIN_VALUE) return new int[] {-1};
        else {
            for (int i = 0; i < 11; i++) {
                if (winRecord[i]) {
                    answer[i] = info[i] + 1;
                }
            }
        }
        
        return answer;
    }
    
    static void dfs(int n, int point) {
        if (11 <= point) {
            int gap = cal(n);
            if (gap != -1 && max < gap) {
                // System.out.println(Arrays.toString(isWin));
                
                max = gap;
                winRecord = isWin.clone();
            }
            return;
        }

        // 현재 점수를 지거나 이기는 경우
        isWin[point] = false;
        dfs(n, point + 1);
        isWin[point] = true;
        dfs(n, point + 1);
    }
    
    static int cal(int arrowRemain) {
        int lion = 0;
        int apeach = 0;
        
        for (int i = 0; i < 11; i++) {         
            if (isWin[i]) {
                arrowRemain -= info[i] + 1; // 어피치보다 한 발 더 쏴야 이길 수 있음.
                if (arrowRemain < 0) return -1; // 남은 화살이 없으면 패배로 간주.
                lion += 10 - i;
            } else {
                apeach += 10 - i;
            }
        }
        if (apeach >= lion) return -1;
        return lion - apeach;
    }
}