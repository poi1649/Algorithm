import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    static int t, v, e, w;
    static ArrayList<Integer>[] graph;
    static boolean[] finished;
    static int unusedId = 1;
    static int[] id;
    static Deque<Integer> stack = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> SCC = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        final String[] s = reader.readLine().split(" ");
        v = parseInt(s[0]);
        e = parseInt(s[1]);
        graph = new ArrayList[v + 1];
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
        System.out.println(SCC.size());
        SCC.stream().sorted(Comparator.comparingInt(o -> o.stream().min(Comparator.naturalOrder()).get()))
           .forEach(integers -> {
               integers.sort(Comparator.naturalOrder());
               for (int i : integers) {
                   sb.append(i).append(" ");
               }
               sb.append(-1).append("\n");
           });
        System.out.print(sb);
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
            ArrayList<Integer> scc = new ArrayList<>();
            while (true) {
                int t = stack.removeLast();
                scc.add(t);
                finished[t] = true;
                if (t == x) {
                    break;
                }
            }
            SCC.add(scc);
        }
        return parent;
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

