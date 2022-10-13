package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* boj 14891번 톱니바퀴 */
public class boj_14891 {

    static int K;
    static int[][] gear;
    static int[] dir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];

        for(int i = 0; i < 4; i++) {
            String input = br.readLine();
            for(int j = 0; j < 8; j++) {
                gear[i][j] = input.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());

        while(K --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int gearNum = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
            int rotationNum = Integer.parseInt(st.nextToken()); // 방향

            dir = new int[4];

            dir[gearNum] = rotationNum;

            // S극 N극 체크
            rightDirCheck(gearNum);
            leftDirCheck(gearNum);

            // 회전
            leftCheck();
            rightCheck();
        }

        // 12시방향 S극 확인
        int[] score = {1, 2, 4, 8};

        int answer = 0;
        for(int i = 0; i < 4; i++) {
            if(gear[i][0] == 1) {
                answer += score[i];
            }
        }
        System.out.println(answer);
    }

    private static void rightDirCheck(int gearNum) {
        // 우측
        for(int i = gearNum + 1; i < 4; i++) {
            // 극이 다르다면 회전한 방향과 반대방향으로 회전한다.
            if(gear[i][6] != gear[i - 1][2]) {
                dir[i] = -dir[i - 1];
            } else {
                // 극이 같으면 회전하지 않는다.
                dir[i] = 0;
            }
        }
    }

    private static void leftDirCheck(int gearNum) {
        // 좌측
        for(int i = gearNum - 1; i >= 0; i--) {
            // 극이 다르다면 회전한 방향과 반대방향으로 회전한다.
            if(gear[i][2] != gear[i + 1][6]) {
                dir[i] = -dir[i + 1];
            } else {
                // 극이 같으면 회전하지 않는다.
                dir[i] = 0;
            }
        }
    }

    private static void rightCheck() {
        // 시계 방향
        for(int i = 0; i < 4; i++) {
            if(dir[i] == 1) {
                int temp = gear[i][7];

                for(int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j - 1];
                }
                gear[i][0] = temp;
            }
        }
    }

    private static void leftCheck() {
        // 반시계 방향
        for(int i = 0; i < 4; i++) {
            if(dir[i] == -1) {
                int temp = gear[i][0];

                for(int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j + 1];
                }
                gear[i][7] = temp;
            }
        }
    }
}
