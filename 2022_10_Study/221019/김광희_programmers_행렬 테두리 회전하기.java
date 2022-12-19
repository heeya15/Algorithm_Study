package Programmers;

/**
 * [문제 ]
 *  행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns, 그리고 회전들의 목록 queries가 주어질 때,
 *  각 회전들을 배열에 적용한 뒤, 그 "회전에 의해 위치가 바뀐 숫자들 중" [ 가장 작은 숫자들을 순서대로 배열에 담아 ] 
 *  return 하도록 solution 함수를 완성해주세요.
 *    
 * [ 출력 예시 ]
	rows	      columns		                    queries	                                        result
	6		6			[[2,2,5,4],[3,3,6,6],[5,1,6,3]]				      [8, 10, 25]
	3		3			[[1,1,2,2],[1,2,2,3],[2,1,3,2],[2,2,3,3]]	              [1, 1, 5, 3]
	100		97			[[1,1,100,97]]						      [1]
 *
 * ( 문제 풀이 )
 * 1. row * columns 크기 만큼 1부터 row *columns 까지의 숫자를 한줄씩 map에 채워준다.
 * 2. 직사각형 모양의 범위에서 시작 지점 부터 끝지점 까지 map 안에 있는 숫자를 시계방향으로 회전 시켜준다. 
 *    -> 여기서 회전하면서 계속 최솟값을 갱신해 준다. -> 이 중 가장 작은 값을 answer 배열에 순서대로 담아준다.
 */
public class Lv2_행렬테두리회전하기 {
	// 우, 하, 좌, 상 델타배열
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	// 행, 열, 회전들의 목록이 인자로 주어짐.
	public static void main(String[] args) {
		int[][] query = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		int[] temp = solution(6, 6, query);
		for (int num : temp) System.out.print(num + " "); // 출력 8 10 25
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int [][] map = new int[rows][columns];
		int cnt = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = cnt++;
			}
		}
		System.out.println(queries.length);

		for (int i = 0; i < queries.length; i++) {
			// 현재 좌표 위치 용도의 변수
			int r = queries[i][0] - 1;
			int c = queries[i][1] - 1;
			// 회전 목록 경계 범위 용도 변수
			int r1 = queries[i][0] - 1;
			int c1 = queries[i][1] - 1;
			int r2 = queries[i][2] - 1;
			int c2 = queries[i][3] - 1;

			int tmp = map[r1][c1]; // 처음 좌표의 값을 임시 저장용도 사용 변수
			int min = map[r1][c1]; // 회전을 실행했을 때 이동한 숫자 중 최솟 값을 저장할 변수.
			int dir = 0; // 4방향 회전 체크 용도 변수
			while (dir < 4) {
				// 이동된 좌표 로 사용될 변수
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				// 현재 이동한 좌표의 값을 [ 다음 회전한 좌표에 값으로 주기 위해 임시 저장 용도 변수 ]
				int temp = 0;
				// 해당 회전 목록들의 범위 안 이라면
				if (nr >= r1 && nr <= r2 && nc >= c1 && nc <= c2) {
					temp = map[nr][nc];
					map[nr][nc] = tmp;
					tmp = temp;
					r = nr;
					c = nc;
					min = Math.min(min, temp);
				} else dir++; // 범위 밖 일경우 방향 전환.
			}
			answer[i] = min;
		}
		return answer;
	}
}