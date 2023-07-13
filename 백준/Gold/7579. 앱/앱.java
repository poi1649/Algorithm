import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] memories = new int[n + 1];
        System.arraycopy(temp, 0, memories, 1, n);
        temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] costs = new int[n + 1];
        System.arraycopy(temp, 0, costs, 1, n);
        final int[][] dp = new int[n + 1][10001];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 10000; j++) {
                if (j < costs[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i - 1][j - costs[i]] + memories[i]
                );
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}


