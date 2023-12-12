import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int q, n, m;
    static int[] arr = new int[1000001];

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
        m = parseInt(s[1]);

        for (int i = 0; i < n; i++) {
            final var split = br.readLine().split(" ");
            arr[parseInt(split[1])] = parseInt(split[0]);
        }

        int start = m;
        long curr = 0;

        if (m >= 500000) {
            for (final int i : arr) {
                curr +=i;
            }
            System.out.println(curr);
            return;
        }

        for (int i = 0; i <= start + m; i++) {
            curr += arr[i];
        }
        long max = curr;

        for (int i = start + 1; i <= 1_000_000 - m; i++) {
            curr -= arr[i - m - 1];
            curr += arr[i + m];
            max = Math.max(max, curr);
        }
        System.out.println(max);
    }
}

