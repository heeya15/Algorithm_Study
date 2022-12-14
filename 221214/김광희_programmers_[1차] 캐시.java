package Programmers;

import java.util.*;

/**
 * ( 문제 설명 ) 
 * LRU: 가장 오랫동안 참조하지 않은 개체를 캐시에서 교체
 * 
 * ( 문제 풀이 )
 * 1. 먼저 cacheSize가 0이라면 무조건 MISS기 때문에 모든 시간이 +5초
 * 2. 도시이름 길이 만큼 반복문을 도는대 먼저 도시 이름은 대문자로 변경하여 준다.
 *    그 후, LinkedList 에 해당 도시이름이 포함(HIT)되어 있다면 해당 도시 이름을 제거하고, 새로 해당 도시 이름을 입력해 준다.
 *    -> 그리고 HIT 시간 즉, answer에 +1 하기
 * 3. 만약 해당 도시 이름이 포함되어 있지 않다면(MISS)
 *    -> 먼저 캐시 사이즈와 LinkedList 사이즈가 같다면 캐시가 가득찼다는 의미로 가장 오래 남겨진 도시 이름을 제거하고
 *    -> 새로 입력된 도시 이름을 추가 및 answer에 +5 하기
 *    -> 캐시 사이즈가 가득 차 있지 않다면 새로운 도시 이름을 추가 및 answer에 +5 해주기
 * 4. 총 실행시간 출력
 * 
 * < 추가적 2번째 방법 >
 * 위와 비슷한 로직이지만 도시 이름이 포함되어 있는 경우 바로 도시 이름을 제거하는 것을 
 * if문 조건 -> if(cache.remove(city))로 좀더 시간 복잡도가 개선된 것 같다.
 * 그리고 좀 더 명시적으로 하기 위해 addFirst() 함수로 새로 입력된 것들을 추가해 주고, 가장 오래된 것을 빼기 위해 pollLast()로 가장 마지막 도시 이름을 제거하는 함수도 이용하였다. 
 **/
public class Lv2_1차캐시 {
	static final int CACHE_HIT = 1;
	static final int CACHE_MISS = 5;
	public static void main(String[] args) {
		String[] cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
		System.out.println(solution(3, cities)); // 50
		System.out.println(solution2(3, cities)); // 50
	}
	// 1번째 방법
	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		if (cacheSize == 0) return cities.length * CACHE_MISS; // 0이면 무조건  MISS여서 모든 시간이 +5초
		LinkedList<String> cache = new LinkedList<>();
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toUpperCase();
			if (cache.contains(city)) {
				cache.remove(city);
				cache.add(city);
				answer += CACHE_HIT;
			} else {
				if (cache.size() == cacheSize) {
					cache.remove(0); // 가장 오래 사용된 것을 먼저 제거하고
					cache.add(city); // 새로 입력된 도시 이름을 cache에 추가
				} else cache.add(city);
				
				answer += CACHE_MISS;
			}
		}
		return answer;
	}
	
	 // 2번째 방법 : 매개 변수 : 캐시 크기, 도시이름 배열
    public static int solution2(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) return cities.length * CACHE_MISS; // 0이면 무조건  MISS여서 모든 시간이 +5초
        LinkedList<String> cache = new LinkedList<>();
        for(int i = 0; i < cities.length; i++){
            String city = cities[i].toUpperCase();
            // hit - 가장 처음에 해당 도시를 삽입하면 된다.
            if(cache.remove(city)){
               cache.addFirst(city);
               answer += CACHE_HIT;
            }
            else {// miss
                int currentSize = cache.size();
                if(currentSize == cacheSize){ // 1. 캐시가 가득 찬 경우
                    cache.pollLast(); // 2. 가장 뒤 도시 이름을 삭제하고
                }
                cache.addFirst(city); // 3. 가장 앞에 새 도시이름을 넣는다.
                answer += CACHE_MISS;
            }
        }
        return answer;
    }
}