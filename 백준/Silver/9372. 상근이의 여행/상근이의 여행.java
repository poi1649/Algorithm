import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] dp;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            count = 0;
            final String[] s = br.readLine().split(" ");
            final int n = parseInt(s[0]);
            final int m = parseInt(s[1]);
            final DisjointSet2 disjointSet2 = new DisjointSet2(n);
            for (int j = 0; j < m; j++) {
                final String[] s1 = br.readLine().split(" ");
                final int a = parseInt(s1[0]);
                final int b = parseInt(s1[1]);
                if (disjointSet2.find(a) == disjointSet2.find(b)) {
                    continue;
                }
                disjointSet2.union(a, b);
                count++;
            }
            System.out.println(count);
        }
    }
}

class DisjointSet2{
    private final int[] parents;
    private final int[] ranks;

    public DisjointSet2(int n) {

        this.parents = new int[n+1];
        this.ranks = new int[n+1];

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
        parents[rootX]= rootY;
        return rootY;
    }
}
