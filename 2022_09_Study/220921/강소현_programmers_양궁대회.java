import java.util.*;

class Solution {
    
    static int n, max;
    static int[] apeach, ryan, answer;
    
    static PriorityQueue<int[]> queue;
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        
        apeach = info.clone();
        ryan = answer = new int[11];
        
        // 우선순위 큐를 사용하여 내림차순 정렬
		queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				for (int i = 10; i >= 0; i--) {
					if (o1[i] != o2[i])
						return o2[i] - o1[i]; // 내림차순
				}
				return 0;
			}
		});
        
        max = -1;
        solve(0, 0);
                
        return queue.isEmpty() ? new int[] { -1 } : queue.poll();
    }
    
    private static void solve(int depth, int start) {
        if(depth == n) {
            check();
            return;
        }
        
        for(int i = start; i < 11; i++) {
            ryan[i]++;
            solve(depth + 1, i);
            ryan[i]--;
        }
    }
    
    private static void check() {
        int a = 0, r = 0;
        
        for(int i = 0; i < 11; i++) {
            int score = 10 - i;
            
            if(apeach[i] == 0 && ryan[i] == 0) continue;  // 어피치 == 라이언 == 0 인 경우 어느 누구도 k점을 가져가지 않는다.
            else if(apeach[i] >= ryan[i]) a += score; // 어피치 >= 라이언 인 경우 어피치가 점수를 가져간다.
            else if(apeach[i] <= ryan[i]) r += score; // 어치피 <= 라이언 인 경우 라이언이 점수를 가져간다.
        }
        
        // 라이언 점수 > 어피치 점수
        if(r > a) {
            int diff = r - a;

            if(max < diff) {
                max = diff;
                queue.clear(); // 초기화
                queue.offer(ryan.clone());
            } else if (max == r - a) {
                queue.offer(ryan.clone());
            }
        }
    }
}