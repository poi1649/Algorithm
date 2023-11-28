import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    static int q, n, m;
    static int[][] dp = new int[10001][101];

    static class Node {

        int m, c;

        public Node(final int m, final int c) {
            this.m = m;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = parseInt(br.readLine());
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var n = parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }
        for (final String number : set) {
            var sb = new StringBuilder();
            for (int i = 0; i < number.length() - 1; i++) {
                sb.append(number.charAt(i));
                final var string = sb.toString();
                if (set.contains(string)) {
                    System.out.println("NO");
                    return;
                };
            }
        }
        System.out.println("YES");
    }
}
