class Solution {
    public int solution(int distance, int[][] scope, int[][] times) {
        int answer = distance;
        
        // 감시 구간 기록 (1 ~ distance + 1)
        int[] array = new int[distance + 1];
        for (int i = 0; i < scope.length; i++) {
            int start = Math.min(scope[i][0], scope[i][1]);
            int end = Math.max(scope[i][0], scope[i][1]);
            
            for (int j = start; j <= end; j++) {
                array[j] = i + 1;
            }
        }
        
        // 계산
        for (int i = 0; i < distance; i++) {
            // 감시병이 있는 곳을 지나간다면 !!
            if (array[i] != 0) {
                int num = array[i] - 1;
                int sum = times[num][0] + times[num][1];
                int result = i % sum;
                
                if (result == 0) continue; // 나누어떨어지는 경우는 휴식 중
                if (result <= times[num][0]) {
                    return i;
                }
            }
        }
        return answer;
    }
}