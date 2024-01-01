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
        final var arr = br.readLine().toCharArray();
        LinkedList<Character> chars = new LinkedList<>();
        for (final char c1 : arr) {
            chars.addLast(c1);
        }

        int gap = 0;
        int ans = 0;
        while (chars.size() > 1) {
            char first = chars.getFirst();
            char second = chars.get(1);

            if (Math.abs(gap) < n) {
                chars.removeFirst();
                ans++;
                if (first == 'M') {
                    gap++;
                    continue;
                }
                gap--;
                continue;
            }

            if (gap > 0) {
                if (first == 'W') {
                    gap--;
                    chars.removeFirst();
                    ans++;
                    continue;
                }
                if (second == 'W') {
                    gap--;
                    chars.remove(1);
                    ans++;
                    continue;
                }
                break;
            }

            if (first == 'M') {
                gap++;
                chars.removeFirst();
                ans++;
                continue;
            }
            if (second == 'M') {
                gap++;
                chars.remove(1);
                ans++;
                continue;
            }
            break;
        }


        if (Math.abs(gap) < n) {
            ans++;
        }
        else {
            char first = chars.getFirst();
            if (gap > 0) {
                if (first == 'W') {
                    ans++;
                }
            } else {
                if (first == 'M') {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}

