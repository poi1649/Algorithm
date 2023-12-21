import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int q, n, k, m, result;

    static Deque<Node> deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static List<Node> my = new ArrayList<>();

    static class Node {

        int order, value;

        public Node(final int order) {
            this.order = order;
            this.value = 0;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
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
        n = parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            deque.addLast(new Node(i));
        }
        int i = 1;
        while (!deque.isEmpty()) {
            for (int j = 0; j < i; j++) {
                deque.addLast(deque.pollFirst());
            }
            final var e = deque.pollFirst();
            e.value = i;
            my.add(e);
            i++;
        }
        my.sort(Comparator.comparingInt(o -> o.order));
        System.out.println(my.stream()
                             .map(Node::toString)
                             .collect(Collectors.joining(" ")));
    }
}

