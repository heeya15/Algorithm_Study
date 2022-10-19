class Solution {
    
    static int rows, columns;
    static int[] answer;
    static int[][] map, queries;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        this.rows = rows;
        this.columns = columns;
        this.queries = queries;

        map = new int[rows + 1][columns + 1];
        answer = new int[queries.length];
    
        mapSetting(); // 배열 초기 설정
        rotation(); // 회전
        
        return answer;
    }
    
    private static void mapSetting() {
        int num = 1;
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                map[i][j] = num;
                num++;
            }
        }
    }
    
    private static void rotation() {
        for(int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
        
            int temp = map[x1][y1]; // 초깃값
            int min = temp; // 최솟값
            
            // 왼쪽 아래 -> 위
            for(int x = x1;x < x2; x++) {
                map[x][y1]=map[x + 1][y1];
                min = (min > map[x][y1]) ? map[x][y1] : min;
            }
            
            // 오른쪽 아래 -> 왼
            for(int y = y1; y < y2; y++) {
                map[x2][y] = map[x2][y + 1];
                min = (min > map[x2][y]) ? map[x2][y] : min;
            }
            
            // 오른쪽 위 -> 아래
            for(int x = x2; x > x1; x--) {
                map[x][y2] = map[x - 1][y2];
                min = (min > map[x][y2]) ? map[x][y2] : min;
            }
            
            // 왼쪽 위 -> 오른
            for(int y = y2; y > y1+1; y--) {
                map[x1][y] = map[x1][y - 1];
                min = (min > map[x1][y]) ? map[x1][y] : min;
            }
            
            map[x1][y1 + 1] = temp;
            answer[i] = min;
        }
    }
}