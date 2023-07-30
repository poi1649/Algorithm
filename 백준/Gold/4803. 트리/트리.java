import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static Deque<Integer> deque = new LinkedList<>();
    static Deque<Node> inner = new LinkedList<>();
    static boolean notTree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int count = 0;
        while ((input = br.readLine()) != null && !input.equals("0 0")) {
            final String[] s = input.split(" ");
            count++;
            int trees = 0;
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            graph = new ArrayList[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                deque.add(i);
            }

            for (int i = 0; i < m; i++) {
                final String[] s1 = br.readLine().split(" ");
                final int a = Integer.parseInt(s1[0]);
                final int b = Integer.parseInt(s1[1]);
                graph[a].add(b);
                graph[b].add(a);
            }
            Arrays.fill(visited, false);
            while (!deque.isEmpty()) {
                final int target = deque.pollFirst();
                if (!visited[target]) {
                    notTree = false;
                    inner.add(new Node(target, -1));
                    while (!inner.isEmpty()) {
                        check(inner.pollFirst());
                    }
                    if (!notTree) {
                        trees++;
                    }
                }
            }

            System.out.print("Case " + count + ": ");
            switch (trees) {
                case 0:
                    System.out.println("No trees.");
                    continue;
                case 1:
                    System.out.println("There is one tree.");
                    continue;
                default:
                    System.out.println("A forest of " + trees + " trees.");
            }
        }
    }

    static void check(Node target) {
        graph[target.number].forEach(next -> {
            if (next == target.previous) {
                return;
            }
            if (visited[next]) {
                notTree = true;
                return;
            }
            inner.add(new Node(next, target.number));
            visited[next] = true;

        });
    }
}

class Node {

    int number;

    public Node(int number, int previous) {
        this.number = number;
        this.previous = previous;
    }

    int previous;
}


