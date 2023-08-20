import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    private static final int NOT = 1000;
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
         String s;
        while ((s = reader.readLine()) != null) {
            String[] split = s.split(" ");
            unusedId = 1;
            sccCount = 0;
            n = parseInt(split[0]);
            m = parseInt(split[1]);
            graph = new ArrayList[2001];
            finished = new boolean[2001];
            id = new int[2001];
            sccSet = new int[2001];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
                graph[not(i)] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                split = reader.readLine().split(" ");
                int a = parseInt(split[0]);
                int b = parseInt(split[1]);
                graph[not(a)].add(valueOf(b));
                graph[not(b)].add(valueOf(a));
            }

            for (int i = 1; i <= n; i++) {
                if (id[i] == 0) {
                    dfs(i);
                }
                if (id[not(i)] == 0) {
                    dfs(not(i));
                }
            }
            boolean flag = true;
            for (int i = 1; i <= n; i++) {
                if (sccSet[i] == sccSet[not(i)]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (sccSet[1] < sccSet[not(1)]) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }

            } else {
                System.out.println("no");
            }
        }
    }

    static int valueOf(int x) {
        if (x < 0) return NOT - x;
        return x;
    }

    static int not(int x) {
        if (x < 0) return Math.abs(x);
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


