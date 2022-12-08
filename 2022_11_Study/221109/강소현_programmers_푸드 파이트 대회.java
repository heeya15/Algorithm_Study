class Solution {
    public String solution(int[] food) {
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < food.length; i++) {
            sb.append(String.valueOf(i).repeat(food[i] / 2));
        }

        return sb.toString() + "0" + sb.reverse().toString();
    }
}