import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static class Node {

        int p;
        int w;

        public Node(final int p, final int w) {
            this.p = p;
            this.w = w;
        }
    }


    static int[] chk = new int[101];
    static Deque<Node> q = new LinkedList<>();
    static Deque<Node> done = new LinkedList<>();
    static Deque<Node> tmp = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static int t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        System.out.print(sb);
        br.close();
    }

    public static void process(BufferedReader br, int t) throws IOException {
        final var s = br.readLine().split(" ");
        final var n = parseInt(s[0]);
        final var m = parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            final var s1 = br.readLine().split(" ");
            final var p = parseInt(s1[0]);
            chk[p]++;
            q.addLast(new Node(p, parseInt(s1[1])));
        }
        int ans = 0;
        int turn = 100;
        while (!q.isEmpty()) {
            final var cur = q.pollFirst();
            while (chk[turn] == 0) {
                turn--;
            }

            if (turn != cur.p) {
                q.addLast(cur);
                ans += cur.w;
                continue;
            }

            chk[cur.p]--;
            if (done.isEmpty() || done.peekLast().w >= cur.w) {
                done.addLast(cur);
                ans += cur.w;
                continue;
            }
            while (!done.isEmpty() && done.peekLast().p == cur.p && done.peekLast().w < cur.w) {
                tmp.addLast(done.pollLast());
                ans += tmp.peekLast().w;
            }
            done.addLast(cur);
            ans += cur.w;
            while (!tmp.isEmpty()) {
                done.addLast(tmp.pollLast());
                ans += done.peekLast().w;
            }
        }
        System.out.println(ans);
    }
}


