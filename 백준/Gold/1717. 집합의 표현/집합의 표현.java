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
        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int i1 = parseInt(s1[0]);
            final int i2 = parseInt(s1[1]);
            final int i3 = parseInt(s1[2]);
            if (i1 == 0) {
                disjointSet.union(i2, i3);
                continue;
            }
            if (disjointSet.isSameSet(i2, i3)) {
                sb.append("YES").append(System.lineSeparator());
                continue;
            }
            sb.append("NO").append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}

class DisjointSet {

    private final int[] parents;
    private final int[] rank;

    public DisjointSet(int size) {
        parents = new int[size + 1];
        rank = new int[size + 1];

        for (int i = 0; i <= size; i++) {
            parents[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int x) {
        if (x != parents[x]) {
            parents[x] = findSet(parents[x]);
        }
        return parents[x];
    }

    public boolean isSameSet(int x, int y) {
        return findSet(y) == findSet(x);
    }

    public void union(int x, int y) {
        final int xRoot = findSet(x);
        final int yRoot = findSet(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] > rank[yRoot]) {
            parents[yRoot] = xRoot;
            return;
        }
        if (rank[xRoot] == rank[yRoot]) {
            rank[yRoot]++;
        }
        parents[xRoot] = yRoot;
    }
}


