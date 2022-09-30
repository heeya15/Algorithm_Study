class Solution {
    
    public String solution(String m, String[] musicinfos) {
        
        int time = 0;
        String answer = "(None)";
        
        String[][] info = new String[musicinfos.length][4];

        m = change(m); // #이 붙은 멜로디는 소문자로 변경
        
        for(int i = 0; i < musicinfos.length; i++) {
            info[i] = musicinfos[i].split(",");
            
            info[i][3] = change(info[i][3]);
            
            int song_length = timeCheck(info[i][0], info[i][1]); // 음악 재생 시간 계산
            
            // 음악 재생 시간만큼 재생된 멜로디 길이
            int music_length = song_length / info[i][3].length(); 
            int mod_length = song_length % info[i][3].length();
            
            String str = "";
            
            for(int j = 0; j < music_length; j++) {
                str += info[i][3];
            }
            
            str += info[i][3].substring(0, mod_length);
            
            // 기억한 멜로디가 포함되어 있을 때
            if(str.contains(m)) {
                if(str.length() > time) {
                    time = str.length();
                    answer = info[i][2]; 
                }
            }
        }
        return answer;
    }
    
    private static String change(String m) {
        // #이 들어간 문자열 치환
        return m.replace("A#","a").replace("C#","c").replace("D#","d").replace("F#","f").replace("G#","g");
    }
    
    private static int timeCheck(String t1, String t2) {
        int hour = (Integer.parseInt(t2.substring(0, 2)) - Integer.parseInt(t1.substring(0, 2))) * 60;
        int min = Integer.parseInt(t2.substring(3, 5)) - Integer.parseInt(t1.substring(3, 5));
        
        return hour + min;
    }
}