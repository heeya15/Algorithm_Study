import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        int r = maps.length;
        int c = maps[0].length;
        boolean[][] visited = new boolean[r][c];
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (node.r == r - 1 && node.c == c - 1) {
                answer = node.cnt;
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = dr[d] + node.r;
                int nc = dc[d] + node.c;
                
                if (nr < 0 || nc < 0 || nr >= r || nc >= c || maps[nr][nc] == 0 || visited[nr][nc]) continue;
        
                q.offer(new Node(nr, nc, node.cnt + 1));
                visited[nr][nc] = true;
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

class Node {
    int r;
    int c;
    int cnt;
    
    Node (int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}