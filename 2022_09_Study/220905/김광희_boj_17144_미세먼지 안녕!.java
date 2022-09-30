package 구현;
import java.io.*;
import java.util.*;
/**
 * ( 문제 풀이 )
 * 1. 집 정보를 map 배열에 저장하고, 공기 청정기가 위치한 행을 Aircleaner 변수에 저장 
 *    -> 가장 왼쪽 열에 위치하다고 문제 지문에 있기 때문에 "행만" 저장.
 * 2. 미세먼지가 있는 위치를 "큐(dust_location)"에 담는다
 * 3. 미세먼지를 확산 시킨다. 
 *    -> 미세먼지 양이 5보다 작은 경우는 어차피 확산이 0이 되어서 가지 치기 해줌.
 * 4. 공기청정기를 작동한다. 
 *    -> 위쪽 공기 청정기 부터 반시계 방향, 오른쪽 공기 청정기는 시계 방향으로 순환하게 작동
 *    -> 따라서 바람이 불 때 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
 *    -> 여기서 방향 회전 부분을 가장 마지막 회전 방향 부터 당겨주는 방식으로 진행 하였다.
 * 즉, 이 문제는 주어진 순서대로 구현하여 해결하였다.
 */
public class Main_G5_17144_미세먼지안녕 {
	static class Dust { // 미세먼지 좌표 담아둠.
		int x;
		int y;
		int amount;

		public Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}
	static int R, C, T, Aircleaner;
	static int[][] map;
	// 좌,우,상,하
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	// 미세먼지 위치를 담는 "큐" 
	static Queue<Dust> dust_location = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		T = Integer.parseInt(st.nextToken()); // 초가 지난 후
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 항상 1번 열에 , 크기 두 행을 차지함으로 행만 저장받고 이용해서 공기 청정기 위치 구함.
				if (map[i][j] == -1) Aircleaner = i;// 공기 청정기 위치 행을 저장.
			}
		}
	
		for (int i = 0; i < T; i++) {
			addDust();     // 미세먼지 "담음"
			Dustspread();  // 미세먼지 "확산"
			Air_cleaner(); // 미세먼지가있는 위치 방향 돌려가며 [ 공기청정기 돌려주기 ]
		}
		
		int result = 0;
		
		// T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) result += map[i][j];	
			}
		}
		System.out.println(result);
	}
	private static void addDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 공기 청정기나, 미세먼지 양이 0인건 확산이 일어날 수가 없기 때문에 무시.
				if (map[i][j] == -1 || map[i][j] == 0) continue;
				// 미세먼지 모두 담기 ( 좌표 및, 미세먼지 양)
				dust_location.add(new Dust(i, j, map[i][j]));
			}
		}
	}
	private static void Dustspread() { // 미세먼지 확산.
		while (!dust_location.isEmpty()) {
			Dust temp = dust_location.poll();
			if (temp.amount < 5) continue; // 5미만인 경우 확산 불가능임으로 무시.
			int dust = temp.amount / 5;    // 확산 되는 양
			int count = 0;                 // 확산된 방향의 갯수 체크 (확산 갯수)
			// 4방 탐색.
			for (int k = 0; k < 4; k++) {
				int nx = temp.x + dx[k];
				int ny = temp.y + dy[k];
				// 인접한 방향에 공기청정기가 있거나, 경계값을 벗어나면 그 방향으로 확산이 일어나지 않기 때문에 무시.
				if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) continue;
				map[nx][ny] += dust; // 4방향 중 확산이 가능한 곳에 [ 미세먼지 확산.]
				count++; // 확산 갯수 증가.
			}
			// 현재 좌표 - (확산되는 양 * 확산된 방향의 개수 )
			map[temp.x][temp.y] -= dust * count; // 현재 좌표에선 확산한 양만큼 빼줌.
		}
	}
	// 공기 청정기 돌리기.
	private static void Air_cleaner() {
		int UpAir = Aircleaner - 1; // 위에 공기청정기 
		int DownAir = Aircleaner;   // 아래 공기청정기

		// 위에 있는 공기 청정기 -- [ 반시계 ] 방향
		// 위쪽에 있는 것을 아래쪽으로 당기기
		for (int i = UpAir - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 오른쪽에 있는 것을 왼쪽으로 당기기
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		// 아래쪽에 있는 것을 위쪽으로 댕기기
		for (int i = 0; i < UpAir; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// 왼쪽에 있는 것을 오른쪽으로 당기기(가장 오른쪽 부터 시작)
		for (int i = C - 1; i > 1; i--) {
			map[UpAir][i] = map[UpAir][i - 1];
		}
		
		map[UpAir][1] = 0;

		// 아래에 있는 공기 청정기 -- [ 시계 ] 방향
		// 아래쪽에서 "위쪽"으로 당김(아래 공기 청정기 바로 위쪽 부터 시작)
		for (int i = DownAir + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// 오른쪽에서 "왼쪽"으로 당김 (가장 왼쪽 부터 시작 )
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		// 위쪽에서 "아래쪽"으로 당김(가장 위쪽에서 부터 시작)
		for (int i = R - 1; i > DownAir; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// 왼쪽에서 "오른쪽"으로 당김(가장 오른쪽 부터 시작)
		for (int i = C - 1; i > 1; i--) {
			map[DownAir][i] = map[DownAir][i - 1];
		}
		map[DownAir][1] = 0;
	}
}
