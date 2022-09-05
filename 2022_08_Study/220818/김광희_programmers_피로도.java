package Programmers;

/**
 * ( 문제 설명 )
 * 던전마다 < 탐험을 시작 > 하기 위해 필요한 "최소 필요 피로도"와 던전
         < 탐험을 마쳤을 때 > 소모되는 "소모 피로도"가 있습니다.
 * 이 게임에는 하루에 "한 번씩 탐험"할 수 있는 < 던전이 여러개 > 있는데, 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다. 
 * 유저의 [ 현재 피로도 k와 ] [ 각 던전별 "최소 필요 피로도", "소모 피로도"가 ] 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때, 
  < 유저가 탐험할수 있는 "최대 던전 수" >를 return 하도록 solution 함수를 완성해주세요.
 *
 * ( 문제 풀이 )  
 * 1. 순열을 활용하여 던전을 탐험하는 순서 인덱스를 구한다.
 * 2. 현재 피로도가 최소 필요 피로도보다 크거나 같다면 < 탐험할수 있는 던전의 수를 증가 > 및
 *    < 현재 피로도를 > 던전 탐험하고 나서 < 소모 피로도를 빼준다 >.
 * 3. 그 후 탐험할 수 있는 < 최대 던전 수를 max_dungeon_count에 반영 > 시켜준다.
 **/
public class Lv2_피로도 {
	static boolean[] visited;
	static int[] numbers;
	static int max_dungeon_count = Integer.MIN_VALUE;
	public static void main(String[] args) {
		int[][] dungeons = { { 80, 20 }, { 50, 40 }, { 30, 10 } };
		System.out.println(solution(80, dungeons));
	}

	public static int solution(int k, int[][] dungeons) {
		numbers = new int[dungeons.length];
		visited = new boolean[dungeons.length];
		per(0, k, dungeons);
		System.out.println(dungeons[0][0]);
		return max_dungeon_count;
	}

	public static void per(int depth, int k, int[][] dungeons) {
		if (depth == dungeons.length) {
			int now_perodo = k;
			int count = 0;
			for (int i = 0; i < numbers.length; i++) {
				int index = numbers[i];
				int min_perodo = dungeons[index][0];
				int somo_perodo = dungeons[index][1];
				if (now_perodo >= min_perodo) { // 현재 피로도 보다 최소 필요도보다 크거나 같다면 탐험이 가능
					count++;
					now_perodo -= somo_perodo;
				}
			}
			max_dungeon_count = Math.max(max_dungeon_count, count);
			return;
		}
		for (int i = 0; i < dungeons.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numbers[depth] = i;
				per(depth + 1, k, dungeons);
				visited[i] = false;
			}
		}
	}
}