import java.util.*;

class Solution {
    
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static HashMap<Character, Integer> map = new HashMap<>();
    static String[] copyData;
    static int len = friends.length;
    static boolean[] flag = new boolean[len];
    static int answer;
    public int solution(int n, String[] data) {
        copyData = Arrays.copyOf(data, data.length);
        
        answer = 0;
        Permutation(0);

        return answer;
    }
    
    // 모든 조건을 만족할 수 있도록 서는 경우의 수
    public void Permutation(int r) {
        // 기저 조건
        if(r == len) {
            // 조건에 만족하면 count
            if(checkDistance()) answer++;
            return;
        }
        
        for(int i = 0; i < len; i++) {
            if(!flag[i]) {
                flag[i] = true;
                map.put(friends[i], r + 1); // 프렌즈, 위치 저장
                Permutation(r + 1);
                flag[i] = false;
            }
        }
    }
    
    public boolean checkDistance() {
        for (String str : copyData) {
            char m1 = str.charAt(0); // 프렌즈 1
            char m2 = str.charAt(2); // 프렌즈 2
            char op = str.charAt(3); // =, <, >
            int dist = str.charAt(4) - '0'; // 제시되는 간격   
            int len = Math.abs(map.get(m1) - map.get(m2)) - 1; // 두 프렌즈 사이에 있는 다른 프렌즈의 수
            
            // 조건에 제시되는 간격에 맞지 않으면 false
            if(op == '=') {
                if (len != dist) return false;
            }else if(op == '<') {
                if (len >= dist) return false;
            }else if(op == '>') {
                if (len <= dist) return false;
            }
        }
        return true;
    }
}