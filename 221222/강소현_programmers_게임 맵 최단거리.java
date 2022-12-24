import java.util.*;

class Solution {
    
    static int N, M;
    static int[][] maps;
    static boolean[][] visited;
    
    static Queue<Node> queue = new LinkedList<>();
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        this.maps = maps;
        this.N = maps.length;
        this.M = maps[0].length;
        
        visited = new boolean[N][M];
    
        return solve(0, 0);
    }
    
    private static int solve(int x, int y) {
        visited[x][y] = true;
        queue.offer(new Node(x, y, 1));

        while(!queue.isEmpty()) {
            Node n = queue.poll();
            
            if(n.x == N - 1 && n.y == M - 1) return n.cost;
            
            for(int d = 0; d < 4; d++) {
                int nx = n.x + dx[d];
                int ny = n.y + dy[d];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] || maps[nx][ny] == 0) continue;
                
                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, n.cost + 1));
            }
        }
        return -1;
    }
    
    static class Node {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}