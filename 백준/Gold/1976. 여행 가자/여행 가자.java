import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        m = parseInt(br.readLine());
        final DisjointSet disjointSet = new DisjointSet(n);

        for (int i = 1; i <= n; i++) {
            final int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= n; j++) {
                if (inputs[j - 1] == 1) {
                    disjointSet.union(i, j);
                }
            }
        }

        final int[] routes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i < routes.length; i++) {
            if (disjointSet.isSameSet(routes[0], routes[i])) {
                continue;
            }
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
}

class DisjointSet {

    private final int[] parents;
    private final int[] rank;

    public DisjointSet(int size) {
        parents = new int[size + 1];
        rank = new int[size + 1];

        for (int i = 1; i <= size; i++) {
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

    public boolean isSameSet(int x, int l) {
        return findSet(l) == findSet(x);
    }

    public void union(int x, int l) {
        final int xRoot = findSet(x);
        final int yRoot = findSet(l);

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


