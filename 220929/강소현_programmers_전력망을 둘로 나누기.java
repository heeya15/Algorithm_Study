import java.util.*;

class Solution {
    
    static int n;
    static List<Integer>[] list;

    public int solution(int n, int[][] wires) {
        
        this.n = n;
       
        list = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            list[wire[0]].add(wire[1]);
            list[wire[1]].add(wire[0]);
        }
        
        int answer = 100;
        for(int[] wire : wires) {
            answer = Math.min(answer, solve(wire[0], wire[1]));
        }
        return answer;
    }
    
    private static int solve(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        int ret = 0;

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            ret++;

            for (int next : list[now]) { 
                // end랑 연결된 노드 끊기 또는 이미 방문했을 경우
                if(next == end || visited[next]) continue;
                
                queue.offer(next);
                visited[next] = true;
            }
        }

        // 두 전력망이 가지고 있는 송전탑 개수의 차이
        return Math.abs((n - ret) - ret);
    }
}