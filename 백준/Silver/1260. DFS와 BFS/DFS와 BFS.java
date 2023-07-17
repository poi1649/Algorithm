import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer, List<Integer>> connections = new HashMap<>();
    static boolean[] bfsVisited;
    static boolean[] dfsVisited;
    static Deque<Integer> deque = new LinkedList<>();
    static List<Integer> bfsResult = new LinkedList<>();
    static List<Integer> dfsResult = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        final int r = Integer.parseInt(s[2]);
        bfsVisited = new boolean[n + 1];
        dfsVisited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int i1 = Integer.parseInt(s1[0]);
            final int i2 = Integer.parseInt(s1[1]);

            if (connections.containsKey(i1)) {
                connections.get(i1).add(i2);
            } else {
                final LinkedList<Integer> temp = new LinkedList<>();
                temp.add(i2);
                connections.put(i1, temp);
            }
            if (connections.containsKey(i2)) {
                connections.get(i2).add(i1);
            } else {
                final LinkedList<Integer> temp = new LinkedList<>();
                temp.add(i1);
                connections.put(i2, temp);
            }
        }

        deque.add(r);
        bfsVisited[r] = true;

        while (!deque.isEmpty()) {
            bfs(deque.pollFirst());
        }

        dfsVisited[r] = true;
        dfs(r);

        final String sb =
                dfsResult.stream().map(String::valueOf).collect(Collectors.joining(" "))
                        + System.lineSeparator()
                        + bfsResult.stream().map(String::valueOf).collect(Collectors.joining(" "))
                        + System.lineSeparator();
        System.out.print(sb);
    }

    static void dfs(int target) {
        dfsResult.add(target);
        if (connections.containsKey(target)) {
            final List<Integer> nodes = connections.get(target);
            nodes.sort(Integer::compareTo);
            for (Integer node : nodes) {
                if (!dfsVisited[node]) {
                    dfsVisited[node] = true;
                    dfs(node);
                }
            }
        }
    }


    static void bfs(int target) {
        bfsResult.add(target);

        if (connections.containsKey(target)) {
            final List<Integer> nodes = connections.get(target);
            nodes.sort(Integer::compareTo);
            for (Integer node : nodes) {
                if (bfsVisited[node]) {
                    continue;
                }
                bfsVisited[node] = true;
                deque.add(node);
            }
        }
    }
}

