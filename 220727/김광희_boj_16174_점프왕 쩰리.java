package BaekJoon_self;

import java.util.*;
import java.io.*;

public class Main {
	/**
	 * ( 조건 )
	 * 1. 가로와 세로의 칸수가 같은 정사각형 구역 "내부"에서만 움직일 수 있다. 정사각형 "외부"로 나가는 경우엔 바닥으로 떨어져 즉시 패배
	 * 2. 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸이다. 다른 출발점에서는 출발하지 않는다.
	 * 3. 이동 가능한 방향은 오른쪽과 아래 뿐이다. ( 위쪽, 왼쪽 x )
	 * 4. 가장 오른쪽,가장 아래칸에 도달하는 순간, 그 즉시 젤리 승리로 게임 종료
	 * 5. 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 쓰여 있는 수 만큼, 칸에 쓰여 있는 수 초과나 그 미만으로 이동 x.
	 * 
	 * ( 출력 ) 
	 * 끝 점에 도달할 수 있으면  “HaruHaru”(인용부호 없이), 도달할 수 없으면 “Hing” (인용부호 없이)을 한 줄에 출력
	 * 
	 * ( 문제 풀이 ) 
	 * - BFS를 이용하여 "오른쪽 또는 아래로만" 해당 칸에 쓰여져 있는 수 만큼만 이동하여 탐색
	 * - 가장 오른쪽 즉 '-1'이 쓰여져 있는 칸에 도착하면 true, 도착하지 못하면 false로 반환하게 해결. 
	 */
	static int [][] map;
	static boolean [][] visited;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 게임 구역의 크기
		// 게임판의 구역 (맵)이 주어진다.
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(BFS(map[0][0])) System.out.print("HaruHaru");
		else System.out.println("Hing");
	}

    public static boolean BFS(int cnt){
        visited[0][0] = true;
        Queue<int []> q = new LinkedList<int []>();
        q.add(new int [] {0,0});
        while(!q.isEmpty()) {
        	int [] temp = q.poll();
        	int x = temp[0];
        	int y = temp[1];
          	if(map[x][y] == -1) return true; // 게임판의 승리 지점에 도착했을 경우

          	// 해당 칸에 쓰여져 있는 수 만큼만 이동
            int bottom = x + map[x][y]; // 아래 
            int right = y + map[x][y];  // 오른쪽

            if (bottom < N && !visited[bottom][y]){ // 아래로 가는 것이 방문하지 않았을 경우.
                q.offer(new int[] {bottom, y});
                visited[bottom][y] = true;
            }
            if (right < N && !visited[x][right]){ // 오른쪽으로 가는 것이 방문하지 않았을 경우.
                q.offer(new int[] {x, right});
                visited[x][right] = true;
            }
        }
        return false;
    }
}