import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(dp[0][0]);
            return;
        }

        if (n == 2) {
            System.out.println(dp[0][0] + dp[1][0]);
            return;
        }

        Arrays.fill(dp[0], dp[0][0]);
        dp[1][1] = dp[0][2] + dp[1][0];
        dp[1][2] = dp[1][0];

        dp[2][1] = dp[1][0] + dp[2][0];
        dp[2][2] = dp[0][0] + dp[2][0];

        for (int i = 3; i < n; i++) {
            dp[i][1] = dp[i][0] + dp[i - 1][2];
            dp[i][2] = dp[i][0] +
                    Math.max(
                            Math.max(dp[i - 2][1], dp[i - 2][2]),
                            Math.max(dp[i - 3][1], dp[i - 3][2]));
        }
        final int max = Math.max(Math.max(dp[n - 1][1], dp[n - 1][2]), Math.max(dp[n - 2][1], dp[n - 2][2]));
        System.out.println(max);
    }
}

