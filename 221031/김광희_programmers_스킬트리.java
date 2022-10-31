package Programmers;

import java.util.*;

/**
 * ( 문제풀이 )
 * 1. 유저들이 만든 스킬트리(skill_trees)에 있는 원소의 문자열 길이 만큼 반복문을 돌리면서 
 *    선행 스킬 순서(skill)에 존재하는 문자일때 StringBuilder에 추가해준다.
 * 2. skill의 부분 문자열(맨 뒤 부분을 줄여줌)을 확인하면서 만든 StringBuilder와 같은지 확인.
 *    만약 같지 않으면 부분 문자열 맨 뒤 부분을 줄여주기 위해 size를 1 감소 시킴 
 *    -> 선행 스킬 순서가 항상 먼저 배워야하기 때문에 "맨 뒤 부분부터 줄여주는 생각을 가짐".
 * 
 * < 여기서 주의할 점 ! >
 * 유저들이 만든 스킬 트리가 선행 스킬 트리와 아무것도 같지 않은 경우는 -> "위 순서에 없는 다른 스킬(힐링 등)은 순서에 상관없이 배울 수 있습니다."
 * 에 해당하기 때문에 사용 가능한 스킬 트리라는 의미이다.
 * 따라서 size 가 -1 보다 클 경우를 while문 탈출 조건을 둠으로써 아얘 비어있는 값 까지 비교하게 만들어 주었다. 
 */
public class Lv2_스킬트리 {
	public static void main(String[] args) throws Exception {
		String [] skill_trees = {"AEF", "ZJW"};
		String [] skill_trees1 = {"BACDE", "CBADF", "AECB", "BDA"};
		System.out.println(solution("CBD", skill_trees)); // 답 : 2
		System.out.println(solution("CBD", skill_trees1)); // 답 : 2
	}
	// 매개변수 : 선행 스킬 순서, 유저들이 만든 스킬트리 
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String skill_tree : skill_trees){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < skill_tree.length(); i++){
                String temp = String.valueOf(skill_tree.charAt(i));
                // 선행 스킬에 포함된 단어라면 StringBuilder에 추가.
                if(skill.contains(temp)) sb.append(temp);
            }
           
            int size = skill.length();
            // -1보다 큰 경우를 해 준 이유는 skill의 부분 문자열이 만든 StringBuilder와 하나도 같지 않은경우도 가능
            while(size > -1){
                String st = skill.substring(0, size); // 선행 스킬을 뒤에서 부터 줄여준다.
                // skill의 부분 문자열을 확인하면서 만든 StringBuilder와 같은지 확인.
                if(st.equals(sb.toString())){
                    answer++;
                    break;
                }
                size--;
            }
        }
        return answer;
    }
}
