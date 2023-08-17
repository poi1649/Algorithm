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
    static int[] parent;
    static Deque<Integer> stack = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        int l = parseInt(reader.readLine());
        for (int t = 0; t < l; t++) {
            SCC.clear();
            unusedId = 1;
            final String[] s = reader.readLine().split(" ");
            v = parseInt(s[0]);
            e = parseInt(s[1]);
            graph = new ArrayList[v + 1];
            parent = new int[v + 1];
            finished = new boolean[v + 1];
            id = new int[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < e; i++) {
                final String[] s1 = reader.readLine().split(" ");
                int a = parseInt(s1[0]);
                int b = parseInt(s1[1]);
                graph[a].add(b);
            }
            for (int i = 1; i <= v; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }
            final int[] indegree = new int[v + 1];
            for (ArrayList<Integer> scc : SCC) {
                for (Integer element : scc) {
                    for (Integer target : graph[element]) {
                        if (parent[element] != parent[target]) {
                            indegree[parent[target]]++;
                        }
                    }
                }
            }
            sb.append(SCC.size() - Arrays.stream(indegree).filter(i -> i != 0).count()).append("\n");
        }
        System.out.print(sb);
    }

    static int dfs(int x) {
        id[x] = unusedId++;
        parent[x] = id[x];
        stack.addLast(x);

        for (int y : graph[x]) {
            if (id[y] == 0) {
                parent[x] = Math.min(parent[x], dfs(y));
            } else if (!finished[y]) {
                parent[x] = Math.min(parent[x], id[y]);
            }
        }

        if (parent[x] == id[x]) {
            ArrayList<Integer> scc = new ArrayList<>();
            while (true) {
                int t = stack.removeLast();
                scc.add(t);
                parent[t] = id[x];
                finished[t] = true;
                if (t == x) {
                    break;
                }
            }
            SCC.add(scc);
        }
        return parent[x];
    }
}


