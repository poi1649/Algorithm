import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int t;
    static int[] arr = new int[51];
    static long ans = 0;

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
        final var s = br.readLine().split(" ");
        final var n = parseInt(s[0]);
        final var c = parseInt(s[1]);
        final var w = parseInt(s[2]);

        for (int i = 1; i <= n; i++) {
            arr[i] = parseInt(br.readLine());
        }
        for (int l = 1; l <= 10000; l++) {
            long sum = 0;
            for (int j = 1; j <= n; j++) {
                if (arr[j] < l) {
                    continue;
                }
                if (arr[j] == l) {
                    sum += ((long) w * l);
                    continue;
                }
                long temp = 0;
                final var i1 = arr[j] / l;
                final var i2 = arr[j] % l;
                temp += ((long) w * i1 * l);
                temp -= (long) c * (i1 - 1);
                if (i2 > 0) {
                    temp -= c;
                }
                if (temp > 0) {
                    sum += temp;
                }
            }
            ans = Math.max(sum, ans);
        }
        System.out.println(ans);
    }
}


