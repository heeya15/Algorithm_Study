import java.util.*;

class Solution {

    static ArrayList<Integer> work = new ArrayList<>();
    static ArrayList<Integer> days = new ArrayList<>();
    public int[] solution(int[] progresses, int[] speeds) {

        for(int i = 0; i < progresses.length; i++) {
            // (100% - 작업 진도) / 작업 속도를 list에 담는다.
            work.add((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }
        
        int count = 1, max_days = 0;
        
        for(int i = 0; i < progresses.length; i++) {
            // 첫 작업이면 max_days에 첫 작업 남은 작업일 갱신
            if(i == 0) {
                max_days = work.get(0);
            }
            // 이전 남은 작업일 < 현재 남은 작업일이면
            else if(max_days < work.get(i)) {
                max_days = work.get(i); // 갱신
                days.add(count); // 특정 배포일에 몇개의 기능이 배포되는지 list에 추가
                count = 1; // count 초기화
            }else {
                count++; 
            }
        }
        
        days.add(count); // 마지막 작업까지 넣기 위해 list에 한번 더 추가
        
        int[] answer = new int[days.size()];
    
        for(int i = 0; i < days.size(); i++) {
            answer[i] = days.get(i);
        }
        
        return answer;
    }
}