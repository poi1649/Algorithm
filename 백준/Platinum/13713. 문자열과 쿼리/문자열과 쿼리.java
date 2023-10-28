import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] z = new int[1000001];
    static int r = 0;
    static int l = 0;
    static int t;
    static StringBuilder sb = new StringBuilder();

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
        final var s = new StringBuffer(br.readLine()).reverse().toString().toCharArray();
        z[0] = s.length;

        for (int i = 1; i < s.length; i++) {
            if (r > i) {
                z[i] = Math.min(z[i - l], r - i);
            }
            while (i + z[i] < s.length && (s[i + z[i]] == s[z[i]])) {
                z[i]++;
            }
            if (r <= i + z[i]) {
                r = i + z[i];
                l = i;
            }
        }
        final var n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            final var i1 = parseInt(br.readLine());
            sb.append(z[s.length - i1]).append('\n');
        }
        System.out.print(sb);
    }
}


