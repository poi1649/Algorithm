import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int v, e, s, p;
    static ArrayList<Integer>[] graph;
    static boolean[] finished;
    static int unusedId = 1;
    static int[] id;
    static int sccCount = 0;
    static int[] sccSet;
    static int[] costs;
    static boolean[] canFinish;
    static boolean[] canFinishScc;
    static boolean[] isIncludedInScc;
    static int[] costsForScc;
    static int[] indegree;
    static int startScc;
    static Deque<Integer> stack = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        v = parseInt(split[0]);
        e = parseInt(split[1]);

        graph = new ArrayList[v + 1];
        finished = new boolean[v + 1];
        id = new int[v + 1];
        sccSet = new int[v + 1];
        costs = new int[v + 1];
        costsForScc = new int[v + 1];
        canFinish = new boolean[v + 1];
        canFinishScc = new boolean[v + 1];
        isIncludedInScc = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            split = reader.readLine().split(" ");
            int a = parseInt(split[0]);
            int b = parseInt(split[1]);
            graph[a].add(b);
        }
        for (int i = 1; i <= v; i++) {
            costs[i] = parseInt(reader.readLine());
        }
        final String[] s1 = reader.readLine().split(" ");
        s = parseInt(s1[0]);
        p = parseInt(s1[1]);
        final String[] s2 = reader.readLine().split(" ");
        for (int i = 0; i < p; i++) {
            canFinish[parseInt(s2[i])] = true;
        }
        dfs(s);

        final ArrayList<Integer>[] sccGraph = new ArrayList[sccCount];
        final int[] dp = new int[sccCount];

        for (int i = 0; i < sccCount; i++) {
            dp[i] = costsForScc[i];
            sccGraph[i] = new ArrayList<>();
        }

        indegree = new int[sccCount];

        for (int i = 1; i <= v; i++) {
            if (!isIncludedInScc[i]) {
                continue;
            }
            for (int j : graph[i]) {
                if (sccSet[i] != sccSet[j]) {
                    sccGraph[sccSet[i]].add(sccSet[j]);
                    indegree[sccSet[j]]++;
                }
            }
        }

        for (int i = 0; i < sccCount; i++) {
            if (indegree[i] == 0) {
                stack.addLast(i);
            }
        }
        int max = costsForScc[startScc];
        while (!stack.isEmpty()) {
            int x = stack.removeLast();
            for (int y : sccGraph[x]) {
                dp[y] = Math.max(dp[y], dp[x] + costsForScc[y]);
                if (canFinishScc[y]) {
                    max = Math.max(max, dp[y]);
                }
                if (--indegree[y] == 0) {
                    stack.addLast(y);
                }
            }
        }
        System.out.println(max);
    }

    static int dfs(int x) {
        id[x] = unusedId++;
        int parent = id[x];
        stack.addLast(x);

        for (int y : graph[x]) {
            if (id[y] == 0) {
                parent = Math.min(parent, dfs(y));
            } else if (!finished[y]) {
                parent = Math.min(parent, id[y]);
            }
        }

        if (parent == id[x]) {
            while (true) {
                int t = stack.removeLast();
                sccSet[t] = sccCount;
                finished[t] = true;
                if (canFinish[t]) {
                    canFinishScc[sccCount] = true;
                }
                isIncludedInScc[t] = true;
                costsForScc[sccCount] += costs[t];
                if (t == s) {
                    startScc = sccCount;
                }
                if (t == x) {
                    break;
                }
            }
            sccCount++;
        }
        return parent;
    }
}


