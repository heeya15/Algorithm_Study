import java.util.*;

class Solution {
    static String number[] = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static String result[] = new String[8];
    static boolean visited[] = new boolean[8];
    static String[] data;
    static int answer = 0;
    
    public int solution(int n, String[] data) {
        this.data = data;
        
        getCount(0);
        return answer;
    }
    
    static void getCount(int cur) {
        if (cur >= 8) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(result));
            boolean check = true;
            for (int i = 0; i < data.length; i++) {
                if (data[i].charAt(3) == '=') {
                    if (!(Math.abs(temp.indexOf(String.valueOf(data[i].charAt(0))) - temp.indexOf(String.valueOf(data[i].charAt(2)))) == data[i].charAt(4) - '0' + 1)) check = false;
                } else if (data[i].charAt(3) == '>') {
                    if (!(Math.abs(temp.indexOf(String.valueOf(data[i].charAt(0))) - temp.indexOf(String.valueOf(data[i].charAt(2)))) > data[i].charAt(4) - '0' + 1)) check = false;
                } else {
                    if (!(Math.abs(temp.indexOf(String.valueOf(data[i].charAt(0))) - temp.indexOf(String.valueOf(data[i].charAt(2)))) < data[i].charAt(4) - '0' + 1)) check = false;
                }
                
            }
            if (check) answer++;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                result[cur] = number[i];
                visited[i] = true;
                getCount(cur + 1);
                visited[i] = false;
            }
        }
    }
}