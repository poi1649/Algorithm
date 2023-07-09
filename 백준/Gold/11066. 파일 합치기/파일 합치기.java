import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            final int t = Integer.parseInt(br.readLine());
            int[] inputs = new int[t + 1];
            final int[] inputTemp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(inputTemp, 0, inputs, 1, inputTemp.length);
            final int[] sums = new int[t + 1];
            final int[][] dp = new int[t + 1][t + 1];

            for (int j = 1; j <= t; j++) {
                sums[j] = sums[j - 1] + inputs[j];
            }

            for (int period = 1; period < t; period++) {
                for (int start = 1; start + period <= t; start++) {
                    int end = start + period;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + sums[end] - sums[start - 1]);
                    }
                }
            }
            sb.append(dp[1][t]).append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}


