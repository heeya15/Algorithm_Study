class Solution {

    static int answer = -1;
    
    static int[] order;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        
        order = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        
        goDungeons(dungeons, k, 0);
        
        return answer;
    }
    
    private static void goDungeons(int[][] dungeons, int k, int depth) {
        int len = dungeons.length;
        if(depth == len) {
            
            int hp = k;
            int count = 0;
            for(int n : order) {
                
                // 현재 체력이 최소 필요 피로도보다 적으면 던전 탐험 불가능
                if(hp < dungeons[n - 1][0]) continue;
                
                hp -= dungeons[n - 1][1]; // 현재 체력 - 소모 피로도
                count++; // 던전 탐험 횟수 카운트
            }
            answer = Math.max(answer, count);
        }

        for(int i = 0; i < dungeons.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            order[depth] = i + 1;
            
            goDungeons(dungeons, k, depth + 1);
            
            visited[i] = false;

        }
    }
}