import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static class Node {

        String name;
        int number;

        public Node(final String name, final int number) {
            this.name = name;
            this.number = number;
        }
    }

    static Deque<Node> inputs = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            final var s = br.readLine().split(" ");
            inputs.add(new Node(s[0], parseInt(s[1])));
        }
        while (inputs.size() > 1) {
            final var node = inputs.pollFirst();
            for (int i = 0; i < node.number - 1; i++) {
                final var node1 = inputs.pollFirst();
                inputs.addLast(node1);
            }
            inputs.pollFirst();
        }
        System.out.println(inputs.pollFirst().name);
    }
}
