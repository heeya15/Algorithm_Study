package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* boj 2285번 우체국 */
public class boj_2285 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Point> list = new ArrayList<>();

        long mid = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long a = Integer.parseInt(st.nextToken()); // 마을
            long b = Integer.parseInt(st.nextToken()); // 마을에 사는 사람 수

            list.add(new Point(a, b));

            mid += b; // 전체 사람 수
        }

        Collections.sort(list);

        mid = (mid + 1) / 2; // 전체 사람 수의 중간 값

        long sum = 0;
        for(Point p : list) {
            sum += p.people;

            if(sum >= mid) {
                System.out.println(p.house);
                break;
            }
        }
    }

    public static class Point implements Comparable<Point> {
        long house, people;

        public Point(long house, long people) {
            this.house = house;
            this.people = people;
        }

        @Override
        public int compareTo(Point o) {
            // 오름차순 정렬
            return Long.compare(this.house, o.house);
        }
    }
}
