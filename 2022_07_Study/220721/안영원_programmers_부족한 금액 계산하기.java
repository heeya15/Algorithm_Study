class Solution {
    public long solution(int price, int money, int count) {
        long need = 0;
        for (int i = 1; i <= count; i++) {
            need += price * i;
        }
        
        long answer = money >= need ? 0 : need - money;
        return answer;
    }
}