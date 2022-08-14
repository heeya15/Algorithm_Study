package 구현;

import java.util.*;
import java.io.*;

/**
 * ( 문제 풀이 )
 * 1. ArrayList 로 파이어볼 정보들을 관리 -> 이것을 활용하여 K번 이동
 * 2. 파이어볼 정보가 담겨 있는 리스트 크기 만큼 반복 1번 조건과정 처리 ->  파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동
 *    -> ( 주의할 점 : 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.) 
 *    -> 지문에 의하여 순환 한다는 의미로 해석 즉, 
	     ex) [ 0,2 ] 가 위로 한 칸 이동하게 되면 범위가 넘어 [ 3,2 ]로 -> [ N으로 연결되어 있는 곳으로 시작하여 이동 하기 위해 ] 
			  아래와 같은 공식 사용 (R + N + (방향 * ( 속력 % N)) % N (C + N + 방향)
			  속도 제한이 1000이하기 때문에 ( 속도 % N )을 해줌 그렇지 않으면 "인덱스 초과 에러" 발생.
			  에러나는 경우 Case) f.r(R) = 0, N = 4, dr[f.d](방향)= -1, f.s(속력)= 8 이면  -> < '-4' 이 나오기 때문 >
	  -> 또한, 이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다. 라는 조건이 있기 때문에 [ 2차원 ArrayList에 중복되는 칸에 파이어 볼 정보 처리를 하였음 ]
   3. 2번 조건 -> 2개 이상의 파이어볼이 있는 칸에서 다음과 같은 일이 일어나는 조건에 맞춰 구현하면 됨!
      -> 파이어볼 개수가 '2' 보다 미만인 경우는 해당 map을 초기화 시켜줌. -> 항상 map 전체 초기화한 상태에서 fiaball 정보를 다시 넣어줄 것이기 때문
      -> 또한 2개 이상의 파이어볼을 모두 하나로 합쳐지고 처리가 다 되었기 때문에 "해당 칸에 있는 파이어볼 정보도 초기화"   
 */
public class Main_G4_20056_마법사상어와파이어볼 {
	public static class FireBall {
		int r, c, m, s, d;
		
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}

	static int N, M, K;
	// 0,2,4,6 = 상, 우, 하, 좌 , 1,3,5,7 = 오른쪽 대각선 위, 오른 쪽 대각선 아래, 왼쪽 대각선 아래, 왼쪽 대각선 위
	public static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static ArrayList<FireBall> fiaball_lists;
	public static ArrayList<FireBall>[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 맵 크기 및 ( 1번 부터 N번까지 번호 )
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // K번 이동 명령
		// 초기화
		map = new ArrayList[N][N];
		fiaball_lists = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		// M개의 줄에 파이어볼의 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 파이어 볼의 위치
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			int m = Integer.parseInt(st.nextToken()); // 질량
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 방향
			fiaball_lists.add(new FireBall(r, c, m, s, d)); // 파이어볼 위치, 질량, 속력, 방향 객체를 리스트에 넣어줌.
		}

//		마법사 상어가 이동을 K번 이동
		for (int i = 0; i < K; i++) {
			move();
			checkFireball();
		}
		int answer = 0;
//		 남아있는 파이어볼 질량의 합을 구해보자.
		for (FireBall current : fiaball_lists) answer += current.m;
		System.out.println(answer);
	}

	public static void move() {
		for (FireBall f : fiaball_lists) {
			/**
			 * 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다. 
			 * 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다. -> 지문에 의하여 순환 한다는 의미로 해석 즉, 
			 * [ 0,2 ] 가 위로 한칸 이동하게 되면 범위가 넘어 [ 3,2 ]로 [ N으로 연결되어 있는 곳으로 시작하여 이동 하기 위해 ] 
			 * 아래와 같은 공식 사용 (R + N + (방향 * ( 속력 % N)) % N (C + N + 방향)
			 * 속도 제한이 1000이하기 때문에 % N을 해줌 그렇지 않으면 인덱스 초과 에러 발생.
			 * ex) f.r  = 0, N = 4, dr[f.d]= -1, f.s= 8 이면  -> < '-4' 이 나오기 때문 >
			 */
			int nr = (f.r + N + (dr[f.d] * (f.s % N))) % N;
			int nc = (f.c + N + (dc[f.d] * (f.s % N))) % N;
			// 순환하는 방향 업데이트
			f.r = nr;
			f.c = nc;
			// 이동하는 중에는 < 같은 칸에 여러 개의 파이어볼 >이 있을 수도 있다.
			map[nr][nc].add(f);
		}
	}

	public static void checkFireball() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() >= 2) { // 2번 조건 -> 2개 이상의 파이어볼이 있는 칸에서 다음과 같은 일이 일어남.
					int massSum = 0; // 합친 파이어볼 질량의 합
					int speedSum = 0; // 합친 파이어볼 속력의 합
					// 2번 조건 안에 3-(3) 조건 -> 방향이 모두 짝수인지, 홀수인지 판별
					boolean even = true;
					boolean odd =  true;
					for (FireBall f : map[i][j]) {
						massSum += f.m;
						speedSum += f.s;
						if(f.d % 2 == 0) odd = false;
                        else even = false;
						fiaball_lists.remove(f); // 합쳐진 해당 파이어볼 정보 삭제.
					}
//					 2번 조건 안에 3-(1) 조건 -> 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
					int nMass = massSum / 5;
//					 2번 조건 안에 3-(2) 조건 -> 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
					int nSpeed = speedSum / map[i][j].size();
					
					map[i][j].clear(); // 2개이상 파이어볼이 있는 칸에서 이미 위와 같은 일이 일어졌기 때문에 < 해당 칸에 있는 파이어볼 정보 초기화 >
					
//					 2번 조건 안에 4 조건 -> 합쳐진 파이어볼 질량의 합이 0인 파이어볼은 소멸되어 없어짐.
					if (nMass == 0) continue; // 질량이 0이기 때문에 더 이상 파이어볼을 이동할 필요가 없기 때문에 무시.

//					 모두 홀수이거나 모두 짝수이면 방향 0,2,4,6
					if (even || odd) {
						for (int k = 0; k < 8; k += 2) {
							fiaball_lists.add(new FireBall(i, j, nMass, nSpeed, k));
						}
					}else {// 홀수 짝수가 섞여있다면 방향 1,3,5,7
						for (int k = 1; k < 8; k += 2) {
							fiaball_lists.add(new FireBall(i, j, nMass, nSpeed, k));
						}
					}
				}else {// 파이어볼 개수가 2 보다 작은 경우는 해당 map을 초기화 시켜줌. -> 항상 map 전체 초기화한 상태에서 fiaball 정보를 다시 넣어줄 것이기 때문
					map[i][j].clear(); 
				}
			}
		}
	}
}