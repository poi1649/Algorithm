import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] board = new int[11][11];
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
        for (int i = 1; i <= 10; i++) {
            final var s = br.readLine().toCharArray();
            for (int j = 1; j <= 10; j++) {
                if (s[j - 1] == 'O') {
                    board[i][j] = -1;
                    continue;
                }
                if (s[j - 1] == 'X') {
                    board[i][j] = 1;
                }
            }
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (board[i][j] == 0) {
                    boolean canWin = false;
                    board[i][j] = 1;
                    if (checkRow(i)) {
                        canWin = true;
                    }
                    if (checkCol(j)) {
                        canWin = true;
                    }
                    if (checkDiag(j - i)) {
                        canWin = true;
                    }
                    if (checkDiag2(i + j)) {
                        canWin = true;
                    }
                    if (canWin) {
                        System.out.println(1);
                        return;
                    }
                    board[i][j] = 0;
                }
            }
        }
        System.out.println(0);
    }

    private static boolean checkDiag(int gap) {
        boolean started = false;
        int count = 0;
        for (int k = 1; k <= 10; k++) {
            if (count >= 5) {
                return true;
            }
            final var j = k + gap;
            if (j > 10 || j < 1) {
                continue;
            }
            if (!started) {
                if (board[k][j] == 1) {
                    started = true;
                    count = 1;
                }
                continue;
            }
            if (board[k][j] != 1) {
                started = false;
                count = 0;
                continue;
            }
            count++;
        }
        return count >= 5;
    }

    private static boolean checkDiag2(int sum) {
        boolean started = false;
        int count = 0;
        for (int k = 1; k <= 10; k++) {
            if (count >= 5) {
                return true;
            }
            final var j = sum - k;
            if (j < 1 || j > 10) {
                continue;
            }
            if (!started) {
                if (board[k][j] == 1) {
                    started = true;
                    count = 1;
                }
                continue;
            }
            if (board[k][j] != 1) {
                started = false;
                count = 0;
                continue;
            }
            count++;
        }
        return count >= 5;
    }

    private static boolean checkRow(int x) {
        boolean started = false;
        int count = 0;
        for (int k = 1; k <= 10; k++) {
            if (count >= 5) {
                return true;
            }
            if (!started) {
                if (board[x][k] == 1) {
                    started = true;
                    count =1;
                }
                continue;
            }
            if (board[x][k] != 1) {
                started = false;
                count = 0;
                continue;
            }
            count++;
        }
        return count >= 5;
    }

    private static boolean checkCol(int y) {
        boolean started = false;
        int count = 0;
        for (int k = 1; k <= 10; k++) {
            if (count >= 5) {
                return true;
            }
            if (!started) {
                if (board[k][y] == 1) {
                    started = true;
                    count = 1;
                }
                continue;
            }
            if (board[k][y] != 1) {
                started = false;
                count = 0;
                continue;
            }
            count++;
        }
        return count >= 5;
    }
}


