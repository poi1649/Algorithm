import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int q, n, k, m, result;
    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> deque = new LinkedList<>();

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

    public static void process(BufferedReader br, int c) throws IOException {
        n = parseInt(br.readLine());
        int ans = 1;
        while (n > 0) {
            if (ans >= 10_000_000) {
                n-= 8;
            }
            else if (ans >= 1_000_000) {
                n -= 7;
            } else if (ans >= 100_000) {
                n -= 6;
            } else if (ans >= 10_000) {
                n -= 5;
            } else if (ans >= 1_000) {
                n -= 4;
            } else if (ans >= 100) {
                n -= 3;
            } else if (ans >= 10) {
                n -= 2;
            } else {
                n -= 1;
            }
            ans++;
        }
        ans--;
        final var s1 = String.valueOf(ans);
        n += s1.length();
        System.out.println(s1.charAt(n - 1));
    }
}


