import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 안영원_boj_2239_스도쿠 {
    static int[][] board;
    static ArrayList<Node> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = temp.charAt(j) - '0';
                if (board[i][j] == 0) list.add(new Node(i, j));
            }
        }

        dfs(0);
        
    }
    static void dfs(int find) {
        if (list.size() == find) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            return;
        }

        int r = list.get(find).r;
        int c = list.get(find).c;

        boolean[] number = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if (board[r][i] != 0) number[board[r][i]] = true;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][r] != 0) number[board[i][r]] = true;
        }

        int startR = (r / 3) * 3;
        int startC = (c / 3) * 3;

        for (int i = startR; i < startR + 3; i++) {
            for (int j = startC; j < startC + 3; j++) {
                if (board[i][j] != 0) number[board[i][j]] = true;
            }
        }
        for (int i = 1; i <= 9; i++) {
            if (!number[i]) {
                board[r][c] = i;
                dfs(find + 1);
                board[r][c] = 0;
            }
        }
    }

    static class Node {
        int r;
        int c;

        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

