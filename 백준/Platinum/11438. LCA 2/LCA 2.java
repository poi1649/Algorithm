import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static int[] depth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][18];
        depth = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n - 1; i++) {
            String[] split = reader.readLine().split(" ");
            final int a = parseInt(split[0]);
            final int b = parseInt(split[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        m = parseInt(reader.readLine());
        Arrays.fill(dp[1], 1);
        depth[1] = -1;

        makeParents(1, 1);
        makeDp();
        for (int i = 0; i < m; i++) {
            String[] split = reader.readLine().split(" ");
            final int u = parseInt(split[0]);
            final int v = parseInt(split[1]);
            sb.append(findLca(u, v)).append("\n");
        }
        System.out.print(sb);
    }

    static int findLca(int u, int v) {
        if (depth[u] != depth[v]) {
            if (depth[u] > depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }
            for (int i = 17; i >= 0; i--) {
                if (depth[v] - depth[u] >= (1 << i)) {
                    v = dp[v][i];
                }
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = 17; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];
    }

    static void makeParents(int node, int parent) {
        depth[node] = depth[parent] + 1;
        dp[node][0] = parent;
        final ArrayList<Integer> integers = graph[node];
        for (Integer integer : integers) {
            if (integer != parent) {
                makeParents(integer, node);
            }
        }
    }

    static void makeDp() {
        for (int j = 1; j <= 17; j++) {
            for (int i = 1; i <= n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }

    }
}


