import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static Deque<Integer> deque = new LinkedList<>();
    static int t;

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
        final var k = parseInt(s[1]);
        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }
        while (deque.size() > 1) {
            final var i1 = deque.pollFirst();
            deque.addLast(i1);
            int c = k - 1;
            while (deque.size() > 1 && c-- > 0) {
                deque.pollFirst();
            }
        }
        System.out.println(deque.pollFirst());
    }
}


