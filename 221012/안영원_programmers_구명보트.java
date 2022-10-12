import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people); // 무게가 제일 적게나가는 사람과 많이 나가는 사람을 묶어주기 위해 정렬
        int idx = 0; // 현재 탐색할 짝꿍
        
        for (int i = people.length - 1; i >= idx; i--) {
            if (people[i] == limit) { // 구명보트의 무게 제한과 무게가 같으면 무조건 혼자 타야함
                answer++;
                continue;
            }
            
            // 현재 탐색하는 짝꿍과 제일 무게가 많이 나는 짝꿍이 구명보트에 탈 수 있는지
            if (people[idx] + people[i] <= limit) {
                idx++; // 다음 사람 탐색
                answer++;
            } else {
                answer++; // 못탄다면 몸무게가 많이 나가는 사람 혼자 태워야함
            }
        }
        
        return answer;
    }
}