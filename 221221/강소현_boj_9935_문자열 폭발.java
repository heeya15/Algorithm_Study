package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* boj 9935번 문자열 폭발 */
public class boj_9935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() < bomb.length()) continue;

            if (bomb.equals(sb.substring(sb.length() - bomb.length(), sb.length()))) {
                sb.delete(sb.length() - bomb.length(), sb.length());
            }
        }

        if (sb.length() > 0) {
            System.out.println(sb);
        } else {
            System.out.println("FRULA");
        }
    }
}
