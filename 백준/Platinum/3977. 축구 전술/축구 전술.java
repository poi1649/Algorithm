import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int v, e, w;
    static ArrayList<Integer>[] graph;
    static boolean[] finished;
    static int unusedId = 1;
    static int[] id;
    static int sccCount = 0;
    static int[] parents;
    static Deque<Integer> stack = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        int l = parseInt(reader.readLine());
        for (int t = 0; t < l; t++) {
            if (t != 0) {
                reader.readLine();
            }
            stack.clear();
            SCC.clear();
            unusedId = 1;
            sccCount = 0;

            final String[] s = reader.readLine().split(" ");
            v = parseInt(s[0]);
            e = parseInt(s[1]);
            graph = new ArrayList[v + 1];
            parents = new int[v + 1];
            finished = new boolean[v + 1];
            id = new int[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                final String[] s1 = reader.readLine().split(" ");
                int a = parseInt(s1[0]) + 1;
                int b = parseInt(s1[1]) + 1;
                graph[a].add(b);
            }
            for (int i = 1; i <= v; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }

            final int[] indegree = new int[v + 1];

            for (int i = 1; i <= v; i++) {
                final int size = graph[i].size();
                for (int j = 0; j < size; j++) {
                    int y = graph[i].get(j);
                    if (parents[i] != parents[y]) {
                        indegree[parents[y]]++;
                    }
                }
            }
            int zeroIndegreeCount = 0;
            int resultIndex = 0;

            for (int i = 0; i < sccCount; i++) {
                if (indegree[i] == 0) {
                    zeroIndegreeCount++;
                    resultIndex = i;
                }
            }

            if (zeroIndegreeCount > 1) {
                System.out.println("Confused");
            } else {
                for (int i = 1; i <= v; i++) {
                    if (parents[i] == resultIndex) {
                        System.out.println(i - 1);
                    }
                }
            }
            System.out.println();
        }
        reader.close();
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
                parents[t] = sccCount;
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


