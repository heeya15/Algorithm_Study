import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 캐시사이즈가 0인 경우 예외처리
        if (cacheSize == 0) return 5 * cities.length;
        
        ArrayList<String> cacheList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            int idx = cachePosition(cacheList, city);
            
            // 같은게 있다면 cache hit
            // 젤 앞으로 이동시키고 1 더함
            if (idx != -1) {
                cacheList.remove(idx);
                cacheList.add(0, city);
                answer += 1;
            // 같은게 없을 땐
            } else {
                // 들어갈 캐시 공간이 있다면 젤 앞에 넣어주기
                if (cacheList.size() < cacheSize) {
                    cacheList.add(0, city);
                // 들어갈 캐시 공간이 없다면 cache miss
                // 젤 뒤에껄 제거하고 새걸 앞에 넣고 5더함
                } else {
                    cacheList.remove(cacheList.size() - 1);
                    cacheList.add(0, city);
                    answer += 5;
                }
            }
        }
        
        // 남은 것 cache miss 처리
        answer += cacheList.size() * 5;
        
        return answer;
    }
    
    // 해당 도시가 있는지 없는지 확인, 있다면 인덱스 반환
    static int cachePosition(ArrayList<String> cacheList, String city) {
        for (int i = 0; i < cacheList.size(); i++) {
            if (cacheList.get(i).equals(city)) return i;
        }
        
        return -1;
    }
}