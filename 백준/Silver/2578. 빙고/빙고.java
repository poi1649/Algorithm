import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[][] board = new boolean[6][6];
    static Index[] index = new Index[26];
    static int t;
    static int ans = 0;
    static StringBuilder sb = new StringBuilder();

    static class Index {

        int x;
        int y;

        public Index(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int t) throws IOException {
        for (int i = 1; i <= 5; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 1; j <= 5; j++) {
                final var i1 = parseInt(s[j - 1]);
                index[i1] = new Index(i, j);
            }
        }

        for (int i = 1; i <= 5; i++) {
            final var s = br.readLine().split(" ");
            for (int j = 1; j <= 5; j++) {
                final var cur = parseInt(s[j - 1]);
                final var x = index[cur].x;
                final var y = index[cur].y;
                board[x][y] = true;
                if (checkRow(x)) {
                    ans++;
                }
                if (checkCol(y)) {
                    ans++;
                }
                if (x == y) {
                    if (checkDiag()) {
                        ans++;
                    }
                }

                if (x + y == 6) {
                    if (checkDiag2()) {
                        ans++;
                    }
                }

                if (ans >= 3) {
                    System.out.println(((i - 1) * 5) + j);
                    return;
                }
            }
        }
    }

    private static boolean checkDiag2() {
        for (int i = 1; i <= 5; i++) {
            if (!board[i][6 - i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiag() {
        for (int i = 1; i <= 5; i++) {
            if (!board[i][i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRow(int x) {
        for (int k = 1; k <= 5; k++) {
            if (!board[x][k]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkCol(int y) {
        for (int k = 1; k <= 5; k++) {
            if (!board[k][y]) {
                return false;
            }
        }
        return true;
    }
}


