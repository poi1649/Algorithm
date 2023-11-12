import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int t;
    static Deque<Integer> q = new LinkedList<>();

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

    public static void process(BufferedReader br, int cur) throws IOException {
        final var n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            final var i1 = parseInt(br.readLine());
            if (i1 == 0) {
                q.pollLast();
                continue;
            }
            q.addLast(i1);
        }
        int i = 0;
        while (!q.isEmpty()) {
            i += q.pollFirst();
        }
        System.out.println(i);
    }
}


