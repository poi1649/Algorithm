import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static Map<Integer, List<Integer>> connections = new HashMap<>();
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final int m = Integer.parseInt(s[1]);
        final int r = Integer.parseInt(s[2]);
        visited = new boolean[n + 1];
        result = new int[n + 1];

        for (int i = 0; i < m; i++) {
            final String[] s1 = br.readLine().split(" ");
            final int i1 = Integer.parseInt(s1[0]);
            final int i2 = Integer.parseInt(s1[1]);
            if (connections.containsKey(i1)) {
                connections.get(i1).add(i2);
            } else {
                final LinkedList<Integer> list = new LinkedList<>();
                list.add(i2);
                connections.put(i1, list);
            }

            if (connections.containsKey(i2)) {
                connections.get(i2).add(i1);
                continue;
            }
            final LinkedList<Integer> list = new LinkedList<>();
            list.add(i1);
            connections.put(i2, list);
        }

        check(r, new AtomicInteger(1));

        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < result.length; i++) {
            sb.append(result[i]).append(System.lineSeparator());
        }
        System.out.print(sb);
    }

    static void check(int target, AtomicInteger a) {
        visited[target] = true;
        result[target] = a.getAndIncrement();

        if (connections.containsKey(target)) {
            final List<Integer> nodes = connections.get(target);
            nodes.sort((o1, o2) -> o2 - o1);
            for (Integer node : nodes) {
                if (!visited[node]) {
                    check(node, a);
                }
            }
        }
    }
}

