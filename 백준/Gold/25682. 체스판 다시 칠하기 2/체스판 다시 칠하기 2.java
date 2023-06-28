import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        final int k = Integer.parseInt(s[2]) - 1;
        final char[][] board = new char[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            System.arraycopy(br.readLine().toCharArray(), 0, board[i], 1, m);
        }
        final int[][][] diffs = new int[2][n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                diffs[0][i][j] = diffs[0][i][j - 1];
                if ((i + j) % 2 == 0) {
                    if (board[i][j] != 'W') {
                        diffs[0][i][j] += 1;
                    }
                    continue;
                }
                if (board[i][j] != 'B') {
                    diffs[0][i][j] += 1;
                }
            }
            for (int j = 1; j <= m; j++) {
                diffs[0][i][j] += diffs[0][i - 1][j];
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n - k; i++) {
            for (int j = 1; j <= m - k; j++) {
                final int temp =
                        diffs[0][i + k][j + k] - diffs[0][i - 1][j + k] - diffs[0][i + k][j - 1] + diffs[0][i - 1][j
                                - 1];
                min = Math.min(temp, min);
                max = Math.max(temp, max);
            }
        }
        System.out.println(Math.min(min, (k+1) * (k+1) - max));
    }
}
