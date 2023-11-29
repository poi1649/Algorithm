import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;
        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        String s = br.readLine();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans = Math.max(ans, failure(s.substring(i).toCharArray()));
        }
        System.out.println(ans);
    }

    public static int failure(char[] s) {

        int[] fail = new int[s.length];
        int max = 0;
        for (int i = 1; i < s.length; i++) {
            int j = fail[i - 1];
            while (j > 0 && s[i] != s[j]) {
                j = fail[j - 1];
            }
            if (s[i] == s[j]) {
                fail[i] = ++j;
                max = Math.max(max, fail[i]);
            }
        }
        return max;
    }
}
