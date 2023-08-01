import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] dp;
    static double weight = 0;
    static Star[] stars;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int v = parseInt(br.readLine());
        stars = new Star[v + 1];
        for (int i = 1; i <= v; i++) {
            final String[] s = br.readLine().split(" ");
            stars[i] = new Star((int) (Double.parseDouble(s[0]) * 100), (int) (Double.parseDouble(s[1]) * 100));
        }
        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                queue.add(new Node(
                        i,
                        j,
                        calculateDistance(stars[i].x, stars[i].y, stars[j].x, stars[j].y)
                ));
            }
        }
        final DisjointSet2 set = new DisjointSet2(v + 1);
        while (!queue.isEmpty()) {
            final Node target = queue.poll();
            if (set.find(target.src) == set.find(target.dest)) {
                continue;
            }
            set.union(target.src, target.dest);
            weight += target.weight;
        }
        System.out.printf("%.2f", weight/100);
    }

    static double calculateDistance(int x, int y, int xx, int yy) {
        return Math.sqrt(Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2));
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

class Star {

    int x;
    int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node implements Comparable<Node> {

    int src;
    int dest;
    double weight;

    public Node(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return (int) (this.weight - o.weight);
    }
}
