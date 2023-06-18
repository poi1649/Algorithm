import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] % 1_000_000_000;
                    continue;
                }
                if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1] % 1_000_000_000;
                    continue;
                }
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
            }
        }
        final long sum = Arrays.stream(dp[n]).sum();
        System.out.println(sum % 1_000_000_000);
    }
}
