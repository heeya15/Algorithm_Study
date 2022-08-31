package Programmers;
import java.util.*;

public class Lv1_성격유형검사하기 {
	/**
	 * ( 문제 풀이 )
	 * 1. 각 지표에 대한 성격 유형을 HashMap에 key로 해당 성격 유형의 점수를 value로 사용
	 * 2. 선택한 선택지 (choices[i])에 따라 해당 성격 유형에 점수를 추가 시켜줌
	 * 3. 각 지표에서 더 높은 점수를 받은 유형을 검사자의 성격 유형으로 지정
	 * 4. 만약 각 성격 유형 점수가 같을 경우 두 성격 유형 중 사전순으로 빠른 성격 유형을 검사자의 성격 유형이라 판단하여 해결.
	 */
	public static void main(String[] args) {
		String[] survey = { "AN", "CF", "MJ", "RT", "NA" };
		int[] choices = { 5, 3, 2, 7, 5 };
		System.out.println(solution(survey,choices)); // 답 : 2
	}

	 // 질문 마다 [ 판단하는 지표를 ] 담은 1차원 문자열 배열, [ 선택한 선택지를 ] 담은 1차원 정수 배열
    public static String solution(String[] survey, int[] choices) {
        String answer = "";
        String [] types = {"RT","CF","JM","AN"};
        HashMap<Character, Integer> map = new LinkedHashMap<>(); // 순서를 유지하게 넣기 위해 LinkedHashMap 사용
        for(int i = 0; i < types.length; i++){
            char type1 = types[i].charAt(0);
            char type2 = types[i].charAt(1);
            map.put(type1,0);
            map.put(type2,0);
        }
       
        //map에 점수 데이터 저장
        for(int i = 0; i < choices.length; i++){
            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);
            switch(choices[i]){
                case 1 : map.put(a, map.get(a) + 3);
                    break;
                case 2 : map.put(a, map.get(a) + 2);
                    break;
                case 3 : map.put(a, map.get(a) + 1);
                    break;    
                case 4 :  // 어떤 성격 유형도 점수를 얻지 않는다.
                    break;
                case 5 : map.put(b, map.get(b) + 1);
                    break;
                case 6 : map.put(b, map.get(b) + 2);
                    break;
                case 7 : map.put(b, map.get(b) + 3);
                    break;
            }
        }
     
        for(int i = 0; i < types.length; i++){
            char type1 = types[i].charAt(0);
            char type2 = types[i].charAt(1);
            // 각 지표에서 더 높은 점수를 받은 유형을 검사자의 성격 유형으로 지정
            if(map.get(type1) > map.get(type2)) answer += type1;
            else if(map.get(type1) < map.get(type2)) answer += type2;
            else{ // 하나의 지표에서 각 성격 유형 점수가 같을 경우   
            	// 두 성격 유형 중 사전 순으로 빠른 성격 유형을 검사자의 성격 유형이라고 판단.
               answer += type1 < type2 ? type1 : type2; 
            }   
        }
        return answer;
    }
    
}