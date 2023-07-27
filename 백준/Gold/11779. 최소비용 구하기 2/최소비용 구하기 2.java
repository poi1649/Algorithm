import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static long[] shortest;
    static int[] prev;
    static int n;
    static int m;
    static int start;
    static int count = 0;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> deque = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            final String[] s = br.readLine().split(" ");
            graph[Integer.parseInt(s[0])].add(new Node(
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2])
            ));
        }
        final String[] s = br.readLine().split(" ");
        start = Integer.parseInt(s[0]);
        final int end = Integer.parseInt(s[1]);
        shortest = new long[n + 1];

        Arrays.fill(shortest, Long.MAX_VALUE);
        prev = new int[n + 1];
        shortest[start] = 0;
        deque.add(new Node(start, 0));

        while (!deque.isEmpty()) {
            check(deque.poll());
        }

        System.out.println(shortest[end]);
        findPath(end);
        count++;
        System.out.println(count);
        System.out.println(sb.append(end));
    }

    static void findPath(int target) {
        if (target == start) {
            return;
        }
        sb.insert(0, prev[target] + " ");
        count++;
        findPath(prev[target]);
    }

    static void check(Node target) {
        if (target.weight > shortest[target.number]) {
            return;
        }
        graph[target.number].forEach(next -> {
            if (shortest[next.number] > shortest[target.number] + next.weight) {
                shortest[next.number] = shortest[target.number] + next.weight;
                prev[next.number] = target.number;
                deque.add(new Node(next.number, shortest[next.number]));
            }
        });
    }

}

class Node implements Comparable<Node> {
    int number;
    long weight;

    public Node(int number, long weight) {
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.weight, o.weight);
    }
}
