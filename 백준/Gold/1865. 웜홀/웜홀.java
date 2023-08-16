import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static final int INF = 987654321;
    static int t, n, m, w;
    static int loop;
    static ArrayList<Node3>[] graph;
    static int[] cost;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        t = parseInt(reader.readLine());
        for (int k = 0; k < t; k++) {
            final String[] st = reader.readLine().split(" ");
            n = parseInt(st[0]);
            m = parseInt(st[1]);
            w = parseInt(st[2]);

            graph = new ArrayList[n + 1];
            cost = new int[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                final String[] s1 = reader.readLine().split(" ");
                int s = parseInt(s1[0]);
                int e = parseInt(s1[1]);
                int c = parseInt(s1[2]);
                graph[s].add(new Node3(e, c));
                graph[e].add(new Node3(s, c));
            }
            for (int i = 0; i < w; i++) {
                final String[] s1 = reader.readLine().split(" ");
                int s = parseInt(s1[0]);
                int e = parseInt(s1[1]);
                int c = parseInt(s1[2]);
                graph[s].add(new Node3(e, -c));
            }
            boolean isCycle = false;
            Arrays.fill(cost, INF);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    for (Node3 node3 : graph[j]) {
                        if (cost[node3.index] > cost[j] + node3.cost) {
                            cost[node3.index] = cost[j] + node3.cost;
                            if (i == n) {
                                isCycle = true;
                            }
                        }
                    }
                }
            }
            if (isCycle) {
                System.out.println("YES");
            }
            if (!isCycle) {
                System.out.println("NO");
            }
        }
    }

}

class Node3 {

    int index;
    int cost;

    public Node3(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

