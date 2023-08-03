import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static int n, r, q;
    static ArrayList<Integer>[] graph;
    static int[] dp;
    static DisjointSet disjointSet;

    static StringBuilder sb = new StringBuilder();

    static List<int[]> vectors = List.of(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1});


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        n = parseInt(s[0]);
        r = parseInt(s[1]);
        q = parseInt(s[2]);
        graph = new ArrayList[n + 1];
        dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int a = parseInt(s1[0]);
            final int b = parseInt(s1[1]);
            graph[a].add(b);
            graph[b].add(a);
        }
        findNodes(r, 0);
        for (int i = 0; i < q; i++) {
            final int i1 = parseInt(br.readLine());
            sb.append(dp[i1]).append("\n");
        }
        System.out.print(sb);
    }

    static int findNodes(int target, int prev) {
        if (dp[target] != 0) {
            return dp[target];
        }
        AtomicInteger result = new AtomicInteger(1);
        graph[target].forEach(integer -> {
            if (integer != prev) {
                result.addAndGet(findNodes(integer, target));
            }
        });
        dp[target] = result.get();
        return dp[target];
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

