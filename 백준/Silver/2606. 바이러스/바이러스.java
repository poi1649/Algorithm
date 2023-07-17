import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<Integer, List<Integer>> connections = new HashMap<>();
    static boolean[] visited;
    static Deque<Integer> deque = new LinkedList<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int m = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

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

        deque.add(1);
        visited[1] = true;

        while (!deque.isEmpty()) {
            check(deque.pollFirst());
        }

        System.out.print(count - 1);
    }

    static void check(int target) {
        count++;

        if (connections.containsKey(target)) {
            final List<Integer> nodes = connections.get(target);
            nodes.sort((o1, o2) -> o2 - o1);
            for (Integer node : nodes) {
                if (visited[node]) {
                    continue;
                }
                visited[node] = true;
                deque.add(node);
            }
        }
    }
}

