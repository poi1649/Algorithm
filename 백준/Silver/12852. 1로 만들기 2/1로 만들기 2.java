import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX = 1000000;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        dp = new int[MAX + 1][2];
        for (int i = 2; i <= MAX; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= MAX; i++) {
            if (i + 1 <= MAX && dp[i][0] + 1 < dp[i + 1][0]) {
                dp[i + 1][0] = dp[i][0] + 1;
                dp[i + 1][1] = i;
            }
            if (i * 2 <= MAX && dp[i][0] + 1 < dp[i * 2][0]) {
                dp[i * 2][0] = dp[i][0] + 1;
                dp[i * 2][1] = i;
            }
            if (i * 3 <= MAX && dp[i][0] + 1 < dp[i * 3][0]) {
                dp[i * 3][0] = dp[i][0] + 1;
                dp[i * 3][1] = i;

            }
        }

        System.out.println(dp[n][0]);
        final StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        check(sb, n);
        System.out.println(sb);
    }

    static void check(StringBuilder sb, int target) {
        if (dp[target][1] == 0) {
            return;
        }
        sb.append(dp[target][1]).append(" ");
        check(sb, dp[target][1]);
    }
}
