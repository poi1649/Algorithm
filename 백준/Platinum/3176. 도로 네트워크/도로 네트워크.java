import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int n, m;
    static ArrayList<Node>[] graph;
    static int[][] dp;
    static int[][] max;
    static int[][] min;
    static int[] depth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][18];
        max = new int[n + 1][18];
        min = new int[n + 1][18];
        depth = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n - 1; i++) {
            String[] split = reader.readLine().split(" ");
            final int a = parseInt(split[0]);
            final int b = parseInt(split[1]);
            final int w = parseInt(split[2]);

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
            Arrays.fill(min[i], Integer.MAX_VALUE);
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
            int lcaDepth = findLcaDepth(u, v);
            appendSbMaxAndMin(lcaDepth, u, v);
        }
        System.out.print(sb);
    }

    static void appendSbMaxAndMin(int lcaDepth, int u, int v) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        int depthOne = depth[u] - lcaDepth;
        for (int i = 17; i >= 0; i--) {
            if (depthOne >= (1 << i)) {
                max = Math.max(max, Main.max[u][i]);
                min = Math.min(min, Main.min[u][i]);
                u = dp[u][i];
                depthOne -= (1 << i);
            }
        }
        int depthTwo = depth[v] - lcaDepth;
        for (int i = 17; i >= 0; i--) {
            if (depthTwo>= (1 << i)) {
                max = Math.max(max, Main.max[v][i]);
                min = Math.min(min, Main.min[v][i]);
                v = dp[v][i];
                depthTwo -= (1 << i);
            }
        }
        sb.append(min).append(" ").append(max).append("\n");
    }

    static int findLcaDepth(int u, int v) {
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
            return depth[u];
        }

        for (int i = 17; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return depth[dp[u][0]];
    }

    static void makeParents(int node, int parent) {
        depth[node] = depth[parent] + 1;
        dp[node][0] = parent;
        final ArrayList<Node> integers = graph[node];
        for (Node child : integers) {
            if (child.number != parent) {
                max[child.number][0] = child.weight;
                min[child.number][0] = child.weight;
                makeParents(child.number, node);
            }
        }
    }

    static void makeDp() {
        for (int j = 1; j <= 17; j++) {
            for (int i = 1; i <= n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
                max[i][j] = Math.max(max[i][j - 1], max[dp[i][j - 1]][j - 1]);
                min[i][j] = Math.min(min[i][j - 1], min[dp[i][j - 1]][j - 1]);
            }
        }

    }
}

class Node {

    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}


