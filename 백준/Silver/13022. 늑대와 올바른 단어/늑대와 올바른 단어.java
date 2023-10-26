import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    enum Node {
        W(1),
        O(2),
        L(3),
        F(4),
        ;

        int order;

        Node(final int order) {
            this.order = order;
        }

        public Node next() {
            if (this == Node.F) {
                return Node.W;
            }
            return Node.values()[this.order];
        }
    }

    static int[] count = new int[5];
    static Deque<Node> q = new LinkedList<>();
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
        final var charArray = br.readLine().toUpperCase().toCharArray();
        for (char c : charArray) {
            q.add(Node.valueOf(String.valueOf(c)));
        }
        Node prev = q.pollFirst();
        count[prev.order]++;
        if (prev != Node.W) {
            System.out.println(0);
            return;
        }
        boolean ans = true;
        while (!q.isEmpty()) {
            final var cur = q.pollFirst();
            if (cur == prev) {
                count[cur.order]++;
                continue;
            }
            final var next = prev.next();
            if (next != cur) {
                ans = false;
                break;
            }

            if (count[prev.order] != count[Node.W.order]) {
                ans = false;
                break;
            }

            prev = cur;
            count[cur.order]++;
        }

        for (int i = 2; i < 5; i++) {
            if (count[i] != count[Node.W.order]) {
                ans = false;
                break;
            }
        }

        if (ans) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}


