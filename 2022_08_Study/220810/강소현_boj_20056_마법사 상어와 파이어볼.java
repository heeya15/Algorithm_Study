import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* boj 20056번 마법사 상어와 파이어볼 */
public class Main {

	static int N, M, K;
	static ArrayList<FireBall>[][] fireBalls;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		
		fireBalls = new ArrayList[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				fireBalls[r][c] = new ArrayList<>();
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 위치
			int c = Integer.parseInt(st.nextToken()) - 1; // 파이어볼 위치
			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향
			
			fireBalls[r][c].add(new FireBall(m, s, d));
		}
		
		while(K --> 0) {
			// 파이어볼 이동
			move();
			
			// 파이어볼 나누기
			split();
		}
		
		System.out.println(sum());

	}
	
	static void move() {
		ArrayList<FireBall>[][] copy = new ArrayList[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				copy[r][c] = new ArrayList<>();
			}
		}
 		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				if(fireBalls[r][c].size() == 0) continue;
				
				for(FireBall fb : fireBalls[r][c]) {
					int m = fb.m;
					int s = fb.s;
					int d = fb.d;
				
					// 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
					// 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다. -> 순환을 의미
					int nr = (r + dr[d] * s) % N;
					int nc = (c + dc[d] * s) % N;
					
					if(nr < 0) nr += N;
					if(nc < 0) nc += N;
					
					copy[nr][nc].add(new FireBall(m, s, d));
				}
			}
		}
		fireBalls = copy;
	}
	
	static void split() {
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				// 파이어볼이 2개 이상일 때 
				if(fireBalls[r][c].size() >= 2) {
					
					int mTotal = 0; // 질량 합 초기화
					int sTotal = 0; // 속력 합 초기화
					
					boolean even = true;
					boolean odd = true;
					
					
					for(FireBall fb : fireBalls[r][c]) {
						mTotal += fb.m; // 질량 합
						sTotal += fb.s; // 속력 합
						
						// 짝수이면
						if(fb.d % 2 == 0) even = false;
						else odd = false;
						
					}
					
					mTotal /= 5; // 질량은 (합쳐진 파이어볼 질량의 합)/5
					sTotal /= fireBalls[r][c].size(); // 속력은 (합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)

					fireBalls[r][c].clear();
					
					// 질량의 합이 0이면 파이어볼 소멸
					if(mTotal > 0) {
						for(int d = 0; d < 4; d++) {
							// 모두 홀수이거나 모두 짝수 (0, 2, 4, 6)
							if(even || odd) {
								fireBalls[r][c].add(new FireBall(mTotal, sTotal, d * 2));
							}
							// (1, 3, 5, 7)
							else { 
								fireBalls[r][c].add(new FireBall(mTotal, sTotal, d * 2 + 1));
							}
						}
					}
				}
			}
		}
	}

	
	static int sum() {
		int sum = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				
				for(FireBall fb : fireBalls[r][c]) {
					sum += fb.m;
				}
			}
		}		
		return sum;
	}
	
	
	static class FireBall {
		int m, s, d;
		
		public FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}