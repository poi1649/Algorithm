import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[][] arrays = new int[n + 1][2];
        final int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            final String[] s = br.readLine().split(" ");
            arrays[i][0] = Integer.parseInt(s[0]);
            arrays[i][1] = Integer.parseInt(s[1]);
        }

        for (int gap = 1; gap < n; gap++) {
            for (int start = 1; start + gap <= n; start++) {
                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(
                            dp[start][end],
                            dp[start][mid] + dp[mid + 1][end] + arrays[start][0]*arrays[mid][1]*arrays[end][1]
                    );
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}


