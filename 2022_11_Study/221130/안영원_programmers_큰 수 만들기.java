class Solution {
    public String solution(String number, int k) {
        // 가능한 수 중 가장 큰 수를 계속 찾는다.
        StringBuilder answer = new StringBuilder();
        
        // length - k 만큼 해주어야 k개의 수를 찾을 수가 있음.
        int idx = 0;
        for (int i = 0; i < number.length() - k; i++) {
            char max = '0';
            // 탐색 가능한 위치까지만 탐색
            for (int j = idx; j <= k + i; j++) {
                if (max < number.charAt(j)) {
                    max = number.charAt(j);
                    idx = j + 1;
                }
            }
            answer.append(Character.toString(max));
        }
        return answer.toString();
    }
}