class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String[] kNum = getKnum(n, k).split("0");
        for (String num : kNum) {
            if (num.equals("")) continue;
            if (isPrime(Long.valueOf(num))) answer++;
        }
        
        return answer;
    }
    
    String getKnum(int n, int k) {
        String kNum = "";
        while (n > 0) {
            kNum = n % k + kNum;
            n /= k;
        }
        return kNum;
    }
    
    boolean isPrime(long num) {
        if (num == 1) return false;
        int max = (int) Math.sqrt(num);
        for (int i = 2; i <= max; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
}