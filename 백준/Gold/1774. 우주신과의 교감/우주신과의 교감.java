import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    static int n, m, x;
    static double weight = 0;
    static Position[] positions;
    static ArrayList<Node>[] map;
    static ArrayList<Node>[] reverseMap;
    static PriorityQueue<Node> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        positions = new Position[n + 1];
        final DisjointSet set = new DisjointSet(n + 1);
        for (int i = 1; i <= n; i++) {
            final String[] s1 = br.readLine().split(" ");
            int x = parseInt(s1[0]);
            int y = parseInt(s1[1]);
            positions[i] = new Position(x, y);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                queue.add(new Node(
                        i,
                        j,
                        calculateDistance(positions[i].x, positions[i].y, positions[j].x, positions[j].y)
                ));
            }
        }
        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int src = parseInt(s1[0]);
            final int dest = parseInt(s1[1]);
            set.union(src, dest);
        }
        while(!queue.isEmpty()) {
            final Node target = queue.poll();
            if (set.findSet(target.src) == set.findSet(target.dest)) {
                continue;
            }
            set.union(target.src, target.dest);
            weight += target.weight;
        }
        System.out.printf("%.2f", weight);
    }

    static double calculateDistance(int x, int y, int xx, int yy) {
        return Math.sqrt(Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2));
    }
}

class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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
        return Double.compare(this.weight, o.weight);
    }
}
