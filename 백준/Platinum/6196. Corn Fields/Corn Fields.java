import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[][] cache;
    static final int MOD = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        board = new int[N][M];
        cache = new int[N][(1 << M) + 1];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for (int[] row : cache)
            Arrays.fill(row, -1);

        int ans = 0;
        for (int i = 0; i < (1 << M); ++i) {
            if (validRow(0, i)) {
                ans += dp(0, i);
                ans %= MOD;
            }
        }
        System.out.println(ans);
    }

    static int dp(int row, int state) {
        if (row == N - 1)
            return 1;
        if (cache[row][state] != -1)
            return cache[row][state];

        int ret = 0;
        for (int i = 0; i < (1 << M); i++) {
            if (validState(state, i) && validRow(row + 1, i)) {
                ret += dp(row + 1, i);
                ret %= MOD;
            }
        }
        return cache[row][state] = ret;
    }

    static boolean validState(int cur, int next) {
        for (int i = 0; i < M; ++i) {
            if ((cur & (1 << i)) > 0 && (next & (1 << i)) > 0)
                return false;
        }
        return true;
    }

    static boolean validRow(int row, int state) {
        for (int i = 0; i < M; ++i) {
            if (((state & (1 << i)) > 0 && board[row][i] == 0) || ((state & (1 << i)) > 0 && i < M - 1 && (state & (1 << (i + 1))) > 0))
                return false;
        }
        return true;
    }
}
