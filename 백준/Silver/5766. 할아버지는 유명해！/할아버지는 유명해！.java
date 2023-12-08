import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (process(br, cur++)) {
        }
        br.close();
    }

    public static boolean process(BufferedReader br, int cur) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        if (n == 0 && m == 0) {
            return false;
        }
        int[] scores = new int[10001];
        int max = 0;
        for (int i = 0; i < n; i++) {
            var s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                final var curr = parseInt(s[j]);
                scores[curr]++;
                if (scores[curr] > max) {
                    max = scores[curr];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == max) {
                scores[i] = 0;
            }
        }
        max = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == max) {
                sb.append(i).append(' ');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
        return true;
    }
}

