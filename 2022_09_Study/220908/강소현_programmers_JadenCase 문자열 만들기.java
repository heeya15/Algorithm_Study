class Solution {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        String[] str = s.toLowerCase().split(""); // 문자열 전체 소문자로 변경 후 한글자씩 배열에 넣음

        for(int i = 0; i < str.length; i++) {
			// 첫번째 문자 혹은 현재 탐색하는 인덱스 i 기준 이전 인덱스인 i - 1 위치에 공백이 있으면 대문자
            if(i == 0 || str[i - 1].equals(" ")) {
                sb.append(str[i].toUpperCase());
            }else {
                sb.append(str[i]);
            }
        }
        
        return sb.toString();
    }
}