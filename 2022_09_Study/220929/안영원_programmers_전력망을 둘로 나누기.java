import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 101;
        
        // 인접 리스트 생성
        ArrayList<Integer>[] wireList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) wireList[i] = new ArrayList<>();
        
        for (int i = 0; i < wires.length; i++) {
            wireList[wires[i][0]].add(wires[i][1]);
            wireList[wires[i][1]].add(wires[i][0]);
        }
        
        // 하나씩 끊어서 체크해보자
        for (int i = 0; i < wires.length; i++) {
            wireList[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            wireList[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            
            answer = Math.min(answer, checkNumDiff(n, wireList, wires[i][0], wires[i][1]));
            
            wireList[wires[i][0]].add(wires[i][1]);
            wireList[wires[i][1]].add(wires[i][0]);
        }
        
        return answer;
    }
    
    static int checkNumDiff(int n, ArrayList<Integer>[] wireList, int startA, int startB) {
        int a = getTowerCount(n, wireList, startA);
        int b = getTowerCount(n, wireList, startB);
        
        return Math.abs(a - b);
    }
    
    static int getTowerCount(int n, ArrayList<Integer>[] wireList, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        
        int towerCnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int i = 0; i < wireList[cur].size(); i++) {
                int next = wireList[cur].get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    towerCnt++;
                    q.offer(next);
                }
            }
        }
        
        return towerCnt;
    }
}