import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        Arrays.fill(dp, 0);
        dp[1] = dp[2] = dp[3] = 1;
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            final int input = Integer.parseInt(br.readLine());
            if (dp[input] == 0) {
                calculate(dp, input);
            }
            stringBuilder.append(dp[input]).append(" ").append(System.lineSeparator());
        }
        System.out.print(stringBuilder);
    }

    private static void calculate(final long[] dp, final int input) {
        for (int i = 4; i <= input; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
    }
}
