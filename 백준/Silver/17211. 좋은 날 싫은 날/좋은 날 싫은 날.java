import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    static int q, n, m, result;
    static Set<Integer> visited = new HashSet<>();
    static Deque<Integer> deque = new LinkedList<>();
    static PriorityQueue<StateDepth> queue = new PriorityQueue<>((o1, o2) -> o1.depth - o2.depth);
    static Deque<Integer> dequet = new LinkedList<>();

    static class StateDepth {

        int state, depth, prev;

        public StateDepth(final int state, final int depth, final int prev) {
            this.state = state;
            this.depth = depth;
            this.prev = prev;
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
        var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        var arr = br.readLine().split(" ");
        long a = (long)(Double.parseDouble(arr[0]) * 1000000);
        long b = (long)(Double.parseDouble(arr[1]) * 1000000);
        long c = (long)(Double.parseDouble(arr[2]) * 1000000);
        long d = (long)(Double.parseDouble(arr[3]) * 1000000);

        long pac = m == 0 ? 1000000 : 0;
        long pbd = m == 1 ? 1000000 : 0;

        for (int i = 0; i < n; i++) {
            long tpac = pac;
            pac = (tpac * a) / 1000000 + (pbd * c) / 1000000;
            pbd = (tpac * b) / 1000000 + (pbd * d) / 1000000;
        }
        for (int i = 0; i < 3; i++) {
            if (pac % 10 >= 5) {
                pac += 10;
            }
            pac /= 10;


            if (pbd % 10 >= 5) {
                pbd += 10;
            }
            pbd /= 10;
        }
        System.out.println(pac);
        System.out.println(pbd);
    }
}

