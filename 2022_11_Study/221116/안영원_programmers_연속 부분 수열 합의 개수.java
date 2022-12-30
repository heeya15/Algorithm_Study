import java.util.*;

class Solution {
    public int solution(int[] elements) {
        // 원형 배열이라 2배로 늘림
        int[] nElements = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            nElements[i] = elements[i];
        }
        for (int i = elements.length; i < nElements.length; i++) {
            nElements[i] = elements[i - elements.length];
        }
        
        // 중복 제외를 위해 Set 사용
        Set<Integer> set = new HashSet<>();
        // 길이가 1인 수열부터 elements.length인 수열까지 모두 찾아서 set에 넣기
        for (int i = 0; i < elements.length; i++) {
            for (int j = i; j < elements.length; j++) {
                set.add(Arrays.stream(nElements, j, j+i).sum());
            }
        }
        return set.size();
    }
}