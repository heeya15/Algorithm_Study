package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/* boj 19583번 싸이버개강총회 */
public class boj_19583 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");

        int S = Integer.parseInt(temp[0].replace(":", "")); // 개강총회를 시작한 시간
        int E = Integer.parseInt(temp[1].replace(":", "")); // 개강총회를 끝낸 시간
        int Q = Integer.parseInt(temp[2].replace(":", "")); // 개강총회 스트리밍을 끝낸 시간

        HashSet<String> attend = new HashSet<>();
        HashSet<String> exit = new HashSet<>();

        String input = null;
        while ((input = br.readLine()) != null) {
            int time = Integer.parseInt((input.split(" ")[0]).replace(":", "")); // 시간
            String person = input.split(" ")[1]; // 명단

            if (time <= S) {
                attend.add(person);
            }

            if (attend.contains(person) && (E <= time && Q >= time)) {
                exit.add(person);
            }
        }
        System.out.println(exit.size());
    }
}
