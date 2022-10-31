import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;
        // 누적합 계산을 위해서 우측 한칸을 더 만듦
        int[][] prefix = new int[r + 1][c + 1];
        
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int degree = type == 1 ? -skill[i][5] : skill[i][5];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            
            prefix[r1][c1] += degree;
            prefix[r2 + 1][c1] -= degree;
            prefix[r1][c2 + 1] -= degree;
            prefix[r2 + 1][c2 + 1] += degree;
        }
        
        for (int j = 1; j < c; j++) {
            for (int i = 0; i < r; i++) {
                prefix[i][j] += prefix[i][j - 1];
            }
        }
        
		for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                prefix[i][j] += prefix[i - 1][j];
            }
        }
		
		for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + prefix[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}