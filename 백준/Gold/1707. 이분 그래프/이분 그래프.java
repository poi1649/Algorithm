import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static boolean[] visited;
    static boolean finished;
    static int[] colors;

    public static void main(String[] args) throws IOException {

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            final String[] s = br.readLine().split(" ");
            final int v = Integer.parseInt(s[0]);
            final int e = Integer.parseInt(s[1]);
            finished = false;
            graph.clear();
            visited = new boolean[v + 1];
            colors = new int[v + 1];
            for (int j = 0; j < e; j++) {
                final String[] s1 = br.readLine().split(" ");
                final int key = Integer.parseInt(s1[0]);
                final int value = Integer.parseInt(s1[1]);

                if (graph.containsKey(key)) {
                    graph.get(key).add(value);
                } else {
                    final LinkedList<Integer> list = new LinkedList<>();
                    list.add(value);
                    graph.put(key, list);
                }

                if (graph.containsKey(value)) {
                    graph.get(value).add(key);
                    continue;
                }
                final LinkedList<Integer> list = new LinkedList<>();
                list.add(key);
                graph.put(value, list);
            }

            for (int j = 1; j <= v; j++) {
                if (!visited[j]) {
                    visited[j] = true;
                    colors[j] = 1;
                    check(j);
                }
            }

            if (finished) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
    }

    static void check(int target) {
        
        if (finished) {
            return;
        }
        if (graph.containsKey(target)) {
            final List<Integer> nexts = graph.get(target);
            for (Integer next : nexts) {
                if (!visited[next]) {
                    visited[next] = true;
                    if (colors[target] == 1) {
                        colors[next] = 2;
                    } else {
                        colors[next] = 1;
                    }
                    check(next);
                } else {
                    if (colors[next] == colors[target]) {
                        finished = true;
                    }
                }
            }
        }
    }
}

