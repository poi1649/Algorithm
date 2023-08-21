import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    private static final int NOT = 5000;
    private static final int MAX = 10001;
    static int n, m;
    static ArrayList<Integer>[] graph;
    static boolean[] finished;
    static int unusedId = 1;
    static int[] id;
    static int sccCount = 0;
    static int[] sccSet;
    static Deque<Integer> stack = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        unusedId = 1;
        sccCount = 0;
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        graph = new ArrayList[MAX];
        finished = new boolean[MAX];
        id = new int[MAX];
        sccSet = new int[MAX];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph[not(i)] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            split = reader.readLine().split(" ");
            int a = parseInt(split[0]);
            if (split[1].equals("R")) {
                a = -a;
            }
            int b = parseInt(split[2]);
            if (split[3].equals("R")) {
                b = -b;
            }
            int c = parseInt(split[4]);
            if (split[5].equals("R")) {
                c = -c;
            }
            graph[not(a)].add(valueOf(b));
            graph[not(a)].add(valueOf(c));
            graph[not(b)].add(valueOf(a));
            graph[not(b)].add(valueOf(c));
            graph[not(c)].add(valueOf(a));
            graph[not(c)].add(valueOf(b));
        }

        for (int i = 1; i <= n; i++) {
            if (id[i] == 0) {
                dfs(i);
            }
            if (id[not(i)] == 0) {
                dfs(not(i));
            }
        }
        for (int i = 1; i <= n; i++) {
            if (sccSet[i] == sccSet[not(i)]) {
                System.out.println(-1);
                return;
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (sccSet[i] < sccSet[not(i)]) {
                sb.append("B");
            } else {
                sb.append("R");
            }
        }
        System.out.println(sb);
    }

    static int valueOf(int x) {
        if (x < 0) {
            return NOT - x;
        }
        return x;
    }

    static int not(int x) {
        if (x < 0) {
            return Math.abs(x);
        }
        return x + NOT;
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
                if (t == x) {
                    break;
                }
            }
            sccCount++;
        }
        return parent;
    }
}


