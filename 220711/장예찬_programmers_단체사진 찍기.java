import java.util.*;

class Solution {
    String[] data;
    char[] name = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    char[] pos = new char[8];
    int answer = 0;    
    void permutation(int cnt, int flag) {
        if(cnt == 8) {
            boolean countFlag = true;
            int from = 0, to = 0;
            for(int i=0; i<data.length; i++){

                for(int j = 0; j < pos.length; ++j){
                    if (pos[j] == data[i].charAt(0))
                        from = j;
                    if (pos[j] == data[i].charAt(2))
                        to = j;
                }

                int diff = Math.abs(from - to) - 1;
                if (data[i].charAt(3) == '='){
                    if (diff != data[i].charAt(4)- '0'){
                        countFlag = false;
                        break;
                    }
                }
                if (data[i].charAt(3) == '>'){
                    if (diff <= data[i].charAt(4) - '0'){
                        countFlag = false;
                        break;
                    }
                }
                if (data[i].charAt(3) == '<'){
                    if (diff >= data[i].charAt(4) - '0'){
                        countFlag = false;
                        break;
                    }
                }
            }
            if(countFlag == true)
                answer++;
            return;
        }
        for(int i = 0; i<8; i++) {
            if((flag & 1<<i)==0) {
                pos[cnt] = name[i];
                permutation(cnt+1, flag | 1<<i);
            }
        }
    }


    public int solution(int n, String[] data) {    
        this.data = data;
        permutation(0,0);
        return answer;
    }
}