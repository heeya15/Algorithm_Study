import java.util.*;

class Solution {
    static PriorityQueue<int[]> q;
    static int[] ryan;
    static int[] apeach;
    static int max;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        
        init();
        
        dfs(n, 0);
        
        if (max == 0) return new int[] {-1};
        return q.poll();
    }
    
    static void init() {
        ryan = new int[11];
        max = 0;
        
        q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				for (int i = 10; i >= 0; i--) {
					if (o1[i] != o2[i])
						return o2[i] - o1[i]; // 내림차순
				}
				return 0;
			}
		});
    }
    
    static void dfs(int remainArrow, int cur) {
        if (10 <= cur) {
            ryan[10] = remainArrow; // 남은 화살을 0점에 모두 소비
            
            int gap = cal();
            
            if (max < gap) {
                max = gap;
                q.clear();
                q.offer(ryan.clone());
            } else if (max == gap) q.offer(ryan.clone()); // 갭이 같은 경우 큐에 추가
            
            return;
        }
        
        // 이번 라운드를 지는 경우
        ryan[cur] = 0;
        dfs(remainArrow, cur + 1);
        
        // 이번 라운드를 이기는 경우
        remainArrow -= apeach[cur] + 1;
        ryan[cur] = apeach[cur] + 1;
        if (remainArrow >= 0) dfs(remainArrow, cur + 1);
    }
    
    static int cal() {
        int ryanScore = 0;
        int apeachScore = 0;
        
        for (int i = 0; i < 11; i++) {
            if (ryan[i] + apeach[i] == 0) continue; // 둘다 0점 일 때
            
            // 라이언은 이길 수 있는 경우에만 활을 쐈음.
            if (ryan[i] != 0) ryanScore += 10 - i;
            else apeachScore += 10 - i;
        }
        
        return ryanScore - apeachScore;
    }
}