import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int n, m, x;
    static long result = 0;
    static boolean[] cleared;
    static int[] targets;
    static boolean[] visited;
    static final PriorityQueue<Node> deque = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = br.readLine()).equals("0 0")) {
            final String[] s = input.split(" ");
            result = 0;
            n = parseInt(s[0]);
            m = parseInt(s[1]);
            final DisjointSet set = new DisjointSet(n);

            for (int i = 0; i < m; i++) {
                final String[] s1 = br.readLine().split(" ");
                final int x = parseInt(s1[0]);
                final int y = parseInt(s1[1]);
                final int weight = parseInt(s1[2]);
                deque.add(new Node(x, y, weight));
            }

            while (!deque.isEmpty()) {
                final Node node = deque.poll();
                if (!set.isSameSet(node.x, node.y)) {
                    set.union(node.x, node.y);
                    continue;
                }
                result += node.weight;
            }

            System.out.println(result);
        }
    }
}

class Node implements Comparable<Node> {

    int x;
    int y;
    int weight;

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

class DisjointSet {

    private final int[] parents;
    private final int[] rank;
    private final int[] size;

    public DisjointSet(int n) {
        parents = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int findSet(int x) {
        if (x != parents[x]) {
            parents[x] = findSet(parents[x]);
        }
        return parents[x];
    }

    public boolean isSameSet(int x, int l) {
        return findSet(l) == findSet(x);
    }

    public int getSize(int x) {
        return size[x];
    }

    public int union(int x, int l) {
        final int xRoot = findSet(x);
        final int yRoot = findSet(l);

        if (xRoot == yRoot) {
            return xRoot;
        }

        if (rank[xRoot] > rank[yRoot]) {
            parents[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
            return xRoot;
        }
        if (rank[xRoot] == rank[yRoot]) {
            rank[yRoot]++;
        }
        parents[xRoot] = yRoot;
        size[yRoot] += size[xRoot];
        return yRoot;
    }
}

