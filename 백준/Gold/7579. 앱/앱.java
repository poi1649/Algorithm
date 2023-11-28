import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        List<Node> nodes = new ArrayList<>();
        final var ms = br.readLine().split(" ");
        final var cs = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(parseInt(ms[i - 1]), parseInt(cs[i - 1])));
        }
        nodes.sort(Comparator.comparingInt(o -> o.c));
        for (int i = 0; i <= 10000; i++) {
            for (int j = 1; j <= nodes.size(); j++) {
                final var node = nodes.get(j - 1);
                if (i - node.c >= 0) {
                    dp[i][j] = node.m;
                    for (int k = 1; k < j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - node.c][k] + node.m);
                    }
                }
                if (dp[i][j] >= m) {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}
