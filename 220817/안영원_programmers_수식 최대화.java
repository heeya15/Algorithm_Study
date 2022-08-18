import java.util.*;

class Solution {
    static String[] operator = {"+", "-", "*"};
    static boolean[] visited;
    static String[] priority;
    static long answer;
    static String expression;
    
    public long solution(String expression) {
        answer = 0;
        this.expression = expression;
        
        // 연산자들의 우선순위를 구한다. (+, -, *)
        visited = new boolean[3];
        priority = new String[3];
        priority(0);
        
        return answer;
    }
    
    static void priority(int cnt) {
        if (cnt >= 3) {
            // 뽑은 우선순위로 결과 찾기
            cal();
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                priority[cnt] = operator[i];
                priority(cnt + 1);
                visited[i] = false;
            }
        }
    }
    
    static void cal() {
        // 숫자와 수식 구분
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<String> opers = new ArrayList<>();
        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            char cur = expression.charAt(i);
            if (cur == '+' | cur == '-' | cur == '*') {
                numbers.add(Long.parseLong(num));
                opers.add(String.valueOf(cur));
                num = "";
            } else {
                num += String.valueOf(cur);
            }
        }
        numbers.add(Long.parseLong(num));
        
        // 우선순위에 맞게 계산
        while(!opers.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < opers.size(); j++) {
                    // 첫번째 우선순위에 있는 연산자 발견
                    if (priority[i].equals(opers.get(j))) {                        
                        Long a = numbers.get(j);
                        Long b = numbers.get(j + 1);
                        // 제거되면 위치가 당겨지기 때문에 같은 위치에 2개를 제거
                        numbers.remove(j);
                        numbers.remove(j);
                        
                        // 계산
                        if (opers.get(j).equals("+")) {
                            numbers.add(j, a + b);
                        } else if (opers.get(j).equals("-")) {
                            numbers.add(j, a - b);
                        } else {
                            numbers.add(j, a * b);
                        }
                        
                        opers.remove(j);
                        j--;
                    }
                }
            }
        }
        Long result = Math.abs(numbers.get(0));
        answer = Math.max(answer, result);
    }
}