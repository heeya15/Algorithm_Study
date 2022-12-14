import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 캐시의 크기가 0일 경우
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        Queue<String> cache = new LinkedList<>();
        
        for (int i = 0; i < cities.length; i++) {
            // 대소문자 구분하지 않으므로 대문자로 변경
            String city = cities[i].toUpperCase();
            
            // 캐시가 존재할 때 vs 존재하지 않을 때
            if (cache.contains(city)) {
                cache.remove(city);
                
                answer += 1;
            } else {
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                
                answer += 5;
            }
            
            cache.offer(city);
        }
        
        return answer;
    }
}
                 
                 