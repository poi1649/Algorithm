import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] dp;
    static long weight = 0;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int v = parseInt(s[0]);
        final int e = parseInt(s[1]);
        final DisjointSet2 disjointSet2 = new DisjointSet2(v);
        for (int j = 0; j < e; j++) {
            final String[] s1 = br.readLine().split(" ");
            final int a = parseInt(s1[0]);
            final int b = parseInt(s1[1]);
            final int w = parseInt(s1[2]);
            queue.add(new Node(a, b, w));
        }
        while (!queue.isEmpty()) {
            final Node target = queue.poll();
            if (disjointSet2.find(target.src) == disjointSet2.find(target.dest)) {
                continue;
            }
            disjointSet2.union(target.src, target.dest);
            weight += target.weight;
        }
        System.out.println(weight);
    }
}

class DisjointSet2 {

    private final int[] parents;
    private final int[] ranks;

    public DisjointSet2(int n) {

        this.parents = new int[n + 1];
        this.ranks = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            ranks[i] = 0;
        }
    }

    public int find(int target) {
        if (target != parents[target]) {
            parents[target] = find(parents[target]);
        }
        return parents[target];
    }

    public int union(int x, int y) {
        final int rootX = find(x);
        final int rootY = find(y);

        if (rootX == rootY) {
            return rootX;
        }
        if (ranks[rootX] > ranks[rootY]) {
            parents[rootY] = rootX;
            return rootX;
        }

        if (ranks[rootX] == ranks[rootY]) {
            ranks[rootY]++;
        }
        parents[rootX] = rootY;
        return rootY;
    }
}

class Node implements Comparable<Node> {

    int src;
    int dest;
    int weight;

    public Node(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
