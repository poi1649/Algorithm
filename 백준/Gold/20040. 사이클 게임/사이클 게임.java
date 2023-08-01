import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        final DisjointSet disjointSet = new DisjointSet(n);
        for (int i = 1; i <= m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int i1 = parseInt(s1[0]);
            final int i2 = parseInt(s1[1]);
            if (disjointSet.isSameSet(i1, i2)) {
                System.out.println(i);
                return;
            }
            disjointSet.union(i1, i2);
        }
        System.out.println(0);
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


