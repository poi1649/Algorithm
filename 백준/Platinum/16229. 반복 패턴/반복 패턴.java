import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int t;
    static int r = 0;
    static int l = 0;
    static int[] z = new int[100001];

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

    public static void process(BufferedReader br, int t) throws IOException {
        final var s1 = br.readLine().split(" ");
        final var n = parseInt(s1[0]);
        final var k = parseInt(s1[1]);
        final var s = br.readLine().toCharArray();
        z[0] = s.length;
        for (int i = 1; i < s.length; i++) {
            if (r >= i) {
                z[i] = Math.min(r - i, z[i - l]);
            }
            while (i + z[i] < s.length && s[i + z[i]] == s[z[i]]) {
                z[i]++;
            }
            if (r <= i + z[i]) {
                r = i + z[i];
                l = i;
            }
        }

        for (int i = n; i > 0; i--) {
            int cur = 0;
            boolean flag = true;
            while (cur < s.length) {
                if (cur + i > s.length - 1) {
                    if (z[cur] != s.length - cur) {
                        flag = false;
                    }
                    break;
                }
                if (z[cur] < i) {
                    flag = false;
                }
                cur += i;
            }

            int gap = i - z[cur];
            if (cur == 0 && i - z[cur] == 0) {
                gap = i;
            }

            if (flag && gap <= k) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}


