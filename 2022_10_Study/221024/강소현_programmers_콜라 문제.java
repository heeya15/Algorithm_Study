class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        // 보유 중인 빈 병이 a개 이상일 때까지
        while(n >= a) {
            int getCola = (n / a * b); // 얻을 수 있는 콜라병 개수
            int remainCola = n % a; // 빈병으로 바꾸고 남은 콜라병 개수
            
            answer += getCola;
            n = getCola + remainCola; // 얻은 콜라병 개수 + 남은 콜라병 개수
        }
        return answer;
    }
}