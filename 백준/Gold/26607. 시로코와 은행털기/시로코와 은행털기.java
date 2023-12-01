import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int q, n, k, x;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }



    public static void process(BufferedReader br, int cur) throws IOException {
        var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        k = parseInt(s[1]);
        x = parseInt(s[2]);
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            final var split = br.readLine().split(" ");
            as[i] = (parseInt(split[0]));
        }
        Arrays.sort(as);
        int[][] dp = new int[81][16001];
        int ans = 0;
        for (int i = 0; i < 81; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            if (k == 1) {
                final var a = as[i] * (k * x - as[i]);
                ans = Math.max(a, ans);
            }
            dp[0][as[i]] = Math.min(i, dp[0][as[i]]);
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < 16001; j++) {
                for (int l = i; l < n; l++) {
                    final var curr = as[l];
                    if (j - curr >= 0) {
                        if (dp[i - 1][j - curr] < l) {
                            dp[i][j] = Math.min(dp[i][j], l);

                            if (i == k - 1) {
                                final var a = j * (k * x - j);
                                ans = Math.max(a, ans);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
