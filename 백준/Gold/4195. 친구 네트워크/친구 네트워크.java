import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = parseInt(br.readLine());
        for (int k = 0; k < t; k++) {
            n = parseInt(br.readLine());
            final DisjointSet disjointSet = new DisjointSet(n * 2);
            final AtomicInteger index = new AtomicInteger(1);
            final HashMap<String, Integer> nameMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                final String[] s = br.readLine().split(" ");
                final Integer index1 = nameMap.computeIfAbsent(
                        s[0],
                        ignored -> index.getAndIncrement()
                );
                final Integer index2 = nameMap.computeIfAbsent(
                        s[1],
                        ignored -> index.getAndIncrement()
                );

                final int set = disjointSet.union(index1, index2);
                sb.append(disjointSet.getSize(set)).append(System.lineSeparator());
            }
        }
        System.out.print(sb);
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


