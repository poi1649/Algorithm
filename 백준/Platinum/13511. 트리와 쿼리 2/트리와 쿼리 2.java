import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int n, m;
    static ArrayList<Node>[] graph;
    static int[][] dp;
    static long[][] cost;
    static int[] depth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][18];
        cost = new long[n + 1][18];
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
        }

        m = parseInt(reader.readLine());
        Arrays.fill(dp[1], 1);
        depth[1] = -1;

        makeParents(1, 1);
        makeDp();
        for (int i = 0; i < m; i++) {
            String[] split = reader.readLine().split(" ");
            final int in = parseInt(split[0]);
            final int u = parseInt(split[1]);
            final int v = parseInt(split[2]);
            if (in == 1) {
                findCost(u, v);
                continue;
            }
            final int k = parseInt(split[3]) - 1;
            final int lcaDepth = findLcaDepth(u, v);
            final int middlePoint = findKthMiddlePoint(u, v, lcaDepth, k);
            sb.append(middlePoint).append("\n");
        }
        System.out.print(sb);
    }

    static int findKthMiddlePoint(int u, int v, int lcaDepth, int k) {
        int tempDepth = depth[u] - lcaDepth;
        int left = k - tempDepth;

        if (left <= 0) {
            for (int j = 17; j >= 0; j--) {
                if (k >= (1 << j)) {
                    k -= (1 << j);
                    u = dp[u][j];
                }
            }
            return u;
        }

        tempDepth = depth[v] - lcaDepth - left;
        for (int j = 17; j >= 0; j--) {
            if (tempDepth >= (1 << j)) {
                tempDepth -= (1 << j);
                v = dp[v][j];
            }
        }
        return v;
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

    static void findCost(int u, int v) {
        long tempCost = 0;
        if (depth[u] != depth[v]) {
            if (depth[u] > depth[v]) {
                int temp = u;
                u = v;
                v = temp;
            }
            for (int i = 17; i >= 0; i--) {
                if (depth[v] - depth[u] >= (1 << i)) {
                    tempCost += cost[v][i];
                    v = dp[v][i];
                }
            }
        }

        if (u == v) {
            sb.append(tempCost).append("\n");
            return;
        }

        for (int i = 17; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                tempCost += cost[u][i];
                tempCost += cost[v][i];
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        tempCost += cost[u][0];
        tempCost += cost[v][0];
        sb.append(tempCost).append("\n");
    }

    static void makeParents(int node, int parent) {
        depth[node] = depth[parent] + 1;
        dp[node][0] = parent;
        final ArrayList<Node> integers = graph[node];
        for (Node child : integers) {
            if (child.number != parent) {
                cost[child.number][0] = child.weight;
                makeParents(child.number, node);
            }
        }
    }

    static void makeDp() {
        for (int j = 1; j <= 17; j++) {
            for (int i = 1; i <= n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
                cost[i][j] = cost[i][j - 1] + cost[dp[i][j - 1]][j - 1];
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


