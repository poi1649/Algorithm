import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, k, result;
    static int[] mods = new int[100001];
    static long MOD = 1_000_000_007;

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
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        k = parseInt(split[1]);
        final var split1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            mods[parseInt(split1[i]) % k]++;
        }

        long ans = 1;
        for (int i = 1; i < (k + 1) / 2; i++) {
            int j = k - i;
            long one = 1;
            long two = 1;
            for (int l = 0; l < mods[i]; l++) {
                one = (2 * one) % MOD;
            }
            for (int l = 0; l < mods[j]; l++) {
                two = (2 * two) % MOD;
            }
            ans = (ans * (one + two - 1)) % MOD;
        }

        ans = (ans * (mods[0] + 1)) % MOD;
        if (k % 2 == 0) {
            ans = (ans * (mods[k / 2] + 1)) % MOD;
        }
        ans -= (n + 1);
        ans += MOD;
        ans %= MOD;

        System.out.println(ans);
    }
}

