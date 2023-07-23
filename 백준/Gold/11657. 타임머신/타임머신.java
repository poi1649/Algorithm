import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static int checked = 0;
    static boolean finished = false;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static long[] shortest;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static Deque<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        graph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        shortest = new long[n + 1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int a = Integer.parseInt(s1[0]);
            final int b = Integer.parseInt(s1[1]);
            final int c = Integer.parseInt(s1[2]);

            graph[a].add(new Node(b, c));
        }

        shortest[1] = 0;

        for (int i = 0; i < n; i++) {
            checked++;
            Arrays.fill(visited, false);
            visited[1] = true;
            queue.add(new Node(1, 0));
            while (!queue.isEmpty()) {
                check(queue.pollFirst());
            }
        }
        if (finished) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i < shortest.length; i++) {
            if (shortest[i] == Integer.MAX_VALUE) {
                sb.append(-1).append(System.lineSeparator());
                continue;
            }
            sb.append(shortest[i]).append(System.lineSeparator());
        }

        System.out.print(sb);
    }

    static void check(Node target) {
        graph[target.number].forEach(next -> {
            if (shortest[next.number] > shortest[target.number] + next.weight) {
                if (checked == n) {
                    queue.clear();
                    finished = true;
                    return;
                }
                shortest[next.number] = shortest[target.number] + next.weight;
            }
            if (!visited[next.number]) {
                visited[next.number] = true;
                queue.addLast(next);
            }
        });
    }
}

class Node implements Comparable<Node> {
    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

