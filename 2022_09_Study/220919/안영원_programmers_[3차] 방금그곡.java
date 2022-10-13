import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int max = Integer.MIN_VALUE;
        
        // contains는 A#과 같은 문자열을 하나의 문자가 아닌 A와 #으로 분리해서 확인하기 때문에 A#을 멜로디에서 사용하지 않는 문자로 변경시켜준다.
        m = replace(m);
        
        // 노래 진행동안의 음을 만들기
        for (int i = 0; i < musicinfos.length; i++) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            
            String startTime = st.nextToken();
            String endTime = st.nextToken();
            String songName = st.nextToken();
            String melody = st.nextToken();
            
            st = new StringTokenizer(startTime, ":");
            int startHour = Integer.parseInt(st.nextToken());
            int startMin = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(endTime, ":");
            int endHour = Integer.parseInt(st.nextToken());
            int endMin = Integer.parseInt(st.nextToken());
            
            int min = endMin - startMin + ((endHour - startHour) * 60);
            
            // 전체 시간동안 재생된 노래 만들기
            melody = replace(melody);
            String song = makeSong(melody, min);
            
            // 만든 노래의 멜로디에 네오가 기억한 멜로디가 포함되어있다면 해당 노래 중 재생 시간이 긴 것이 정답
            if (song.contains(m)) {
                if (max < song.length()) {
                    max = song.length();
                    answer = songName;
                }
            }
        }
        return answer;
    }
    
    static String replace(String melody) {
        melody = melody.replace("A#", "a");
        melody = melody.replace("C#", "c");
        melody = melody.replace("D#", "d");
        melody = melody.replace("F#", "f");
        melody = melody.replace("G#", "g");
        
        return melody;
    }
    
    static String makeSong(String melody, int time) {
        String song = "";
        for (int i = 0; i < time; i++) {
            song += melody.charAt(i % melody.length());
        }
        
        return song;
    }
}