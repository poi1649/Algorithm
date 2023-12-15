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
        int start = 0;
        result = 0;
        for (int i = 0; i < arr.length; i++) {
            result *= 10;
            result += i + 1;
            start *= 10;
            start += parseInt(arr[i]);
        }
        queue.add(new StateDepth(start, 0, -1));

        while (!queue.isEmpty()) {
            final var stateDepth = queue.poll();
            if (visited.contains(stateDepth.state)) {
                continue;
            }
            if (check(stateDepth.state, stateDepth.depth, stateDepth.prev)) {
                System.out.println(stateDepth.depth);
                return;
            }
        }

        System.out.println(-1);
    }

    public static boolean check(int state, int depth, int prev) {
        visited.add(state);
        if (state == result) {
            return true;
        }

        for (int i = 0; i < n - m + 1; i++) {
            int newState = swapFrom(i, state);
            if (i == prev || visited.contains(newState)) {
                continue;
            }
            queue.add(new StateDepth(newState, depth + 1, i));
        }
        return false;
    }

    public static int swapFrom(int start, int state) {
        final var back = n - start - m;
        for (int i = 0; i < back; i++) {
            deque.addLast(state % 10);
            state /= 10;
        }
        for (int i = 0; i < m; i++) {
            dequet.addLast(state % 10);
            state /= 10;
        }
        while (!dequet.isEmpty()) {
            deque.addLast(dequet.pollLast());
        }
        while (state != 0) {
            deque.addLast(state % 10);
            state /= 10;
        }
        while (!deque.isEmpty()) {
            state *= 10;
            state += deque.pollLast();
        }
        return state;
    }
}

