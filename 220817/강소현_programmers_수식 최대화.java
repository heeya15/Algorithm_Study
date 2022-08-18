import java.util.*;
import java.util.stream.*;

class Solution {
    
    static String[] OPERATOR = {"*", "+", "-"}; 

    static int[] order;
    static boolean[] visited;
    
    static ArrayList<Long> numberList = new ArrayList<>();
    static ArrayList<String> opList = new ArrayList<>();
    public long solution(String expression) {
        long answer = 0;
        
        // 숫자와 연산자 분리
        String numbers = expression.replaceAll("[*+-]", " ");
        String op = expression.replaceAll("[0-9]", "");
    
        // stream을 사용해서 숫자 담기
        for(long v : Arrays.stream(numbers.split(" ")).mapToLong(Long::parseLong).toArray()) {
            numberList.add(v);
        }
        
        for(String s : op.split("")) {
            opList.add(s);
        }
        
        order = new int[3];
        visited = new boolean[3];
        dfs(0);

        return answer;
    }
    
    private static long solve(int[] order) {
        
        for(int i = 0; i < order.length; i++) {
            for(int j = 0; j < opList.size(); j++) {
              
            }
        }
        
        return 0;
    }
    
    private static void dfs(int depth) {
        if(depth == 3) {
            // 계산하기
            
            return;
        }
        
        for(int i = 0; i < OPERATOR.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            order[depth] = i;
            dfs(depth + 1);
            visited[i] = false;
        }
    }
    
    static long calculator(long n1, long n2, String op) {
        long result = 0;
        switch(op) {
            case "*":
                result =  n1 * n2;
            case "+":
                result = n1 + n2;
            case "-":
                result = n1 - n2;
        }
        return result;
    }
}