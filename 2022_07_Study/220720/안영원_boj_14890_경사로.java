import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			
			// 가로로 확인
			for (int i = 0; i < N; i++) {
				boolean isCan = true;
				boolean make = false;
				int x = 0;
				boolean[] slope = new boolean[N];
				for (int j = 1; j < N; j++) {
					// 경사로 제작 중엔 전과 높이가 같아야함
					if (make) {
						// 경사로 제작 중 전과 높이가 다르면
						if (map[i][j - 1] != map[i][j]) {
							isCan = false;
							break;
						} else {
							// 전과 높이가 같으면 경사로 길이만큼 건설
							x--;
							slope[j] = true;
							if (x == 0) make = false;
						}
					} else {
						// 전과 높이차이가 1이라면 경사로 건설 시작
						if (map[i][j - 1] - map[i][j] == 1) {
							slope[j] = true;
							make = true;
							x = X - 1;
                            
                            if (x == 0) make = false;
						}
						
						// 전과 높이차이가 -1이라면 앞 쪽에 경사로 건설 가능 여부 확인
						if (map[i][j - 1] - map[i][j] == -1) {
							for (int k = j - 1; k >= j - X; k--) {
								if (k < 0) {
									// 앞 쪽에 경사로를 건설할 공간이 없으면 불가
									isCan = false;
									break;
								}
								// 앞 쪽이 서로 높이가 다르면 경사로 건설 불가
								if (map[i][j - 1] != map[i][k]) {
									isCan = false;
									break;
								}
								
								// 앞 쪽에 이미 경사로가 만들어져있으면 겹치기 불가
								if (slope[k]) {
									isCan = false;
									break;
								}
							}
						}
						if (!isCan) break;
						
						// 높이차이가 1보다 크면 건설 불가
						if (Math.abs(map[i][j - 1] - map[i][j]) > 1) {
							isCan = false;
							break;
						}
					}
				}
				if (isCan && !make) result++;
			}

			// 세로로 확인
			for (int i = 0; i < N; i++) {
				boolean isCan = true;
				boolean make = false;
				boolean[] slope = new boolean[N];
				int x = 0;
				for (int j = 1; j < N; j++) {
					// 경사로 제작 중엔 전과 높이가 같아야함
					if (make) {
						// 경사로 제작 중 전과 높이가 다르면
						if (map[j - 1][i] != map[j][i]) {
							isCan = false;
							break;
						} else {
							// 전과 높이가 같으면 경사로 길이만큼 건설
							x--;
							slope[j] = true;
							if (x == 0) make = false;
						}
					} else {
						// 전과 높이차이가 1이라면 경사로 건설 시작
						if (map[j - 1][i] - map[j][i] == 1) {
							slope[j] = true;
							make = true;
							x = X - 1;
                            
                            if (x == 0) make = false;
						}
						
						// 전과 높이차이가 -1이라면 앞 쪽에 경사로 건설 가능 여부 확인
						if (map[j - 1][i] - map[j][i] == -1) {
							for (int k = j - 1; k >= j - X; k--) {
								if (k < 0) {
									// 앞 쪽에 경사로를 건설할 공간이 없으면 불가
									isCan = false;
									break;
								}
								// 앞 쪽이 서로 높이가 다르면 경사로 건설 불가
								if (map[j - 1][i] != map[k][i]) {
									isCan = false;
									break;
								}
								
								// 앞 쪽에 이미 경사로가 만들어져있으면 겹치기 불가
								if (slope[k]) {
									isCan = false;
									break;
								} 
							}
						}
						if (!isCan) break;
						
						// 높이차이가 1보다 크면 건설 불가
						if (Math.abs(map[j - 1][i] - map[j][i]) > 1) {
							isCan = false;
							break;
						}
					}
				}
				if (isCan && !make) result++;
			}
			sb.append(result);
		
		System.out.println(sb);
	}
}