class Solution {
    public long solution(int price, int money, int count) {
        long total = 0;
        
        for(int i = 1; i <= count; i++) {
            total += i * price;
        }

        return total > money ? total - money : 0;
    }
}