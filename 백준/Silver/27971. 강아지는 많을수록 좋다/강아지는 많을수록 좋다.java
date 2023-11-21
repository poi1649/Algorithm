import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int t;
    static int m;
    static int n;
    static int a;
    static int b;
    static int[] dp = new int[100001];
    static boolean[] cant = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        a = parseInt(s[2]);
        b = parseInt(s[3]);
        for (int i = 0; i < m; i++) {
            final var s1 = br.readLine().split(" ");
            final var i1 = parseInt(s1[0]);
            final var i2 = parseInt(s1[1]);
            for (int j = i1; j <= i2; j++) {
                cant[j] = true;
            }
        }
        Arrays.fill(dp, 1000000);

        if (!cant[a]) {
            dp[a] = 1;
        }
        if (!cant[b]) {
            dp[b] = 1;
        }

        for (int i = 1; i <= 100000; i++) {
            if (cant[i]) {
                continue;
            }

            if (i - a >= 0 && !cant[i - a]) {
                dp[i] = Math.min(dp[i], dp[i - a] + 1);
            }

            if (i - b >= 0 && !cant[i - b]) {
                dp[i] = Math.min(dp[i], dp[i - b] + 1);
            }
        }
        if (dp[n] == 1000000) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[n]);
    }
}


