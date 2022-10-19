import java.util.*;

class Solution {
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows + 1][columns + 1];
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = num;
                num++;
            }
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int min = rotation(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
            list.add(min);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    static int rotation(int x1, int y1, int x2, int y2) {
        int pre = map[x1][y1];
        int tempX = x1;
        int tempY = y1;
        int min = pre;
        
        while (true) {
            tempY = tempY + 1; // 한 칸 우측 이동
            int temp = map[tempX][tempY]; // 현재 칸 정보
            min = Math.min(min, temp);
            map[tempX][tempY] = pre; // 앞 칸꺼 이동
            pre = temp;
            
            if (tempY == y2) break;
        }
        
        while (true) {
            tempX = tempX + 1;
            int temp = map[tempX][tempY]; // 현재 칸 정보
            min = Math.min(min, temp);
            map[tempX][tempY] = pre; // 앞 칸꺼 이동
            pre = temp;
            
            if (tempX == x2) break;
        }
        
        while (true) {
            tempY = tempY - 1;
            int temp = map[tempX][tempY]; // 현재 칸 정보
            min = Math.min(min, temp);
            map[tempX][tempY] = pre; // 앞 칸꺼 이동
            pre = temp;
            
            if (tempY == y1) break;
        }
        
        while (true) {
            tempX = tempX - 1;
            int temp = map[tempX][tempY]; // 현재 칸 정보
            min = Math.min(min, temp);
            map[tempX][tempY] = pre; // 앞 칸꺼 이동
            pre = temp;
            
            if (tempX == x1) break;
        }
        
        return min;
    }
}